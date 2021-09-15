package com.today.cafe;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;

import member.MemberServiceImpl;
import member.MemberVO;
import member.NaverLoginBO;

@Controller
@RequestMapping("/mobile")
public class AppMemberController {
   @Autowired private MemberServiceImpl service;

   
// 네이버 아이디로 로그인 했는데, DB에 같은 이메일이 있는 경우 DB쪽 회원정보로 로그인 처리
   // 5. 안드로이드에서 요청한 DB Response
   @ResponseBody
   @RequestMapping(value = "/androidResponse", method = { RequestMethod.POST })
   public JSONObject androidResponse(HttpServletRequest req, Model model) {
      String email = (String) req.getParameter("email");
      String dbimgpath = (String) req.getParameter("profile_img");
      
      if(dbimgpath==null) {
    	  dbimgpath = File.separator+"profile"+File.separator+"base.png";
      }
      
      MemberVO vo;
      
      String[] array=email.split("@");
      String userid=array[0];
      
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("email", email);
      map.put("authstatus", "1");
      map.put("userid",userid);
      map.put("dbimgpath",dbimgpath);
      map.put("admin","naver");
      
      

      JSONObject result = new JSONObject();

      // 네이버 메일로 DB에 들어있는 회원정보 조회 하는데

      // DB에 회원정보가 있으면 네이버 로그인이 아닌 DB쪽 회원정보로 로그인 처리
      if (service.selectMember(email) != null) {
         vo = service.androidResponse(email);
         result.put("naverEmail", vo.getEmail());
         result.put("dbimgpath", vo.getDbimgpath());
      // DB에 회원정보가 없으면 네이버 회원 정보로 가입 로그인 처리
      } else {

         /* String admin="D"; */
         boolean success = service.insertNaver(map);

         if (success) {
            result.put("naverEmail", email);
            result.put("dbimgpath", dbimgpath);
         }
      }
      return result;
   }
   
   
   
   //아이디 중복확인 요청
   @ResponseBody @RequestMapping(value="/appIdCheck", method= {RequestMethod.GET, RequestMethod.POST})
   public JSONObject appIdCheck(HttpServletRequest req, Model model) {
      String email = (String)req.getParameter("email");
      boolean check=service.appIdCheck(email);
      JSONObject result = new JSONObject();
      if(check) {
         result.put("check", "y");
      }else {
         result.put("check", "n");
      }
      return result;
      
   }
   
   
   
   
   //회원가입
   @ResponseBody @RequestMapping(value="/appjoin", method= {RequestMethod.GET, RequestMethod.POST})
   public JSONObject appJoin(HttpServletRequest req, Model model) {

      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e1) {
         e1.printStackTrace();
      }
      String fileName = "";
      String email = (String)req.getParameter("email");
      String userpwd = (String)req.getParameter("userpwd");
      String dbImgPath = (String)req.getParameter("dbImgPath");
      String uploadType = (String)req.getParameter("uploadType");
      String userid = (String)req.getParameter("userid");
      
      
      MultipartRequest multi = (MultipartRequest) req;
      MultipartFile file = null;
      
      if(uploadType.equals("image")) {
         file = multi.getFile("image");
      }
      
      if(file != null) {
         dbImgPath =  File.separator+"profile"+File.separator+email+"_"+file.getOriginalFilename();
         fileName=file.getOriginalFilename();

         makeDir(req);
         
         String realImgPath = "";
         model.addAttribute("fileName", fileName);
         if(file.getSize()>0) {            
            realImgPath = req.getSession().getServletContext().getRealPath(File.separator +"resources"+File.separator );

            try {
               file.transferTo(new File(realImgPath, dbImgPath));
            }catch (Exception e) {
               e.getMessage();
            }         
            
         }else {
            fileName = "FileFail.jpg";
            realImgPath= req.getSession().getServletContext().getRealPath("/resources/img/");
         }
      }
      model.addAttribute("email", email);
      model.addAttribute("userid", userid);
      model.addAttribute("userpwd", userpwd);
      model.addAttribute("email", email);
      model.addAttribute("dbimgpath", dbImgPath);
      

      JSONObject result = new JSONObject();
      if(service.appinsert(model)) {
         MemberVO vo = new MemberVO();
         vo.setEmail(email);
         service.create(vo);
         result.put("joincheck", email);
      }else {
         result.put("joincheck", "n");
      }
      return result;
   }
   
   //회원 정보 수정
   @ResponseBody @RequestMapping(value="/MemberUpDate", method= {RequestMethod.GET, RequestMethod.POST})
   public JSONObject MemberUpDate(HttpServletRequest req, Model model) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e1) {
         e1.printStackTrace();
      }
      String fileName = "";
      String email = (String)req.getParameter("email");
      String userpwd = (String)req.getParameter("userpwd");
      String dbImgPath = (String)req.getParameter("dbImgPath");
      String uploadType = (String)req.getParameter("uploadType");
      String userid = (String)req.getParameter("userid");

      MultipartRequest multi = (MultipartRequest) req;
      MultipartFile file = null;
      
      if(uploadType.equals("image")) {
         file = multi.getFile("image");
      }
      
      if(file != null) {
         dbImgPath =  File.separator+"profile"+File.separator+email+"_"+file.getOriginalFilename();
         fileName=file.getOriginalFilename();

         makeDir(req);
         
         String realImgPath = "";
         model.addAttribute("fileName", fileName);
         if(file.getSize()>0) {            
            realImgPath = req.getSession().getServletContext().getRealPath(File.separator +"resources"+File.separator );

            try {
               file.transferTo(new File(realImgPath, dbImgPath));
            }catch (Exception e) {
               e.getMessage();
            }         
            
         }else {
            fileName = "FileFail.jpg";
            realImgPath= req.getSession().getServletContext().getRealPath("/resources/img/");
         }
      }
      model.addAttribute("email", email);
      model.addAttribute("userpwd", userpwd);
      model.addAttribute("dbImgPath", dbImgPath);
      model.addAttribute("fileName", fileName);
      model.addAttribute("userid", userid);

      boolean updateOk= service.appmemberupdate(model);
       JSONObject result = new JSONObject();
       
      if(updateOk) {
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("email", email);
         map.put("userpwd", userpwd);
          MemberVO vo = service.apploginck(map);
         
          result.put("dbimgpath", vo.getDbimgpath());
          result.put("updateOk", "y");
      }else {
         result.put("updateOk", "n");
      }
      
         return result;
   }
   
   
   public void makeDir(HttpServletRequest req) {
      File f = new File(req.getSession().getServletContext().getRealPath("/resources"));
      if(!f.isDirectory()) {
         f.mkdir();
      }
   }

   
   // 1. 앱 로그인
      @ResponseBody
      @RequestMapping(value = "/applogin", produces = "application/text; charset=utf-8")
      public String applogin(String email, String userpwd, HttpServletRequest req, Model model) {

         HashMap<String, String> map = new HashMap<String, String>();
         map.put("email", email);
         map.put("userpwd", userpwd);
         String json="";
         MemberVO vo = service.apploginck(map);
         if(vo!=null) {
        	 Gson gson = new Gson();
             json = gson.toJson(vo);

         }else {
        	 json="null";//if문 내용처럼 안해주면 스트링타입 널이 아니라 그냥 null값이 들어와서 안드에서 비교가 안됨(MemberLogin-onPostExecute메서드)
         }
         
         return json;
        
      }
      
      //회원 탈퇴
      @ResponseBody @RequestMapping("/userdelete")
      public JSONObject appuserdelete(HttpServletRequest req) {
            String email=req.getParameter("email");
            boolean deleteOk =service.delete(email);
            JSONObject result = new JSONObject();
         if(deleteOk) {
            result.put("deleteOk", "y");
         }else {
            result.put("deleteOk", "n");
         }
            return result;
      }
      
   // 4. 웹 네이버 로그인
  	/* NaverLoginBO */
  	private NaverLoginBO naverLoginBO;
  	private String apiResult = null;

  	@Autowired
  	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
  		this.naverLoginBO = naverLoginBO;
  	}
  	
  	//웹로그아웃
  	@ResponseBody
	@RequestMapping(value = "/weblogout", method = { RequestMethod.GET, RequestMethod.POST })
	public String weblogout(HttpSession session) throws IOException {
		session.removeAttribute("login_info");
		return "cafe/weblogin";
	}
  	
  	

  	// 로그인 첫 화면 요청 메소드
  	@RequestMapping(value = "/weblogin", method = { RequestMethod.GET, RequestMethod.POST })
  	public String login(Model model, HttpSession session) {
  		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
  		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
  		// 네이버
  		model.addAttribute("url", naverAuthUrl);
  		return "member/login";
  	}

  	// 네이버 로그인 성공시 callback호출 메소드
  	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
  	public String callback(String email, Model model, @RequestParam String code, @RequestParam String state,
  			HttpSession session) throws IOException, ParseException {

  		OAuth2AccessToken oauthToken;
  		oauthToken = naverLoginBO.getAccessToken(session, code, state);

  		// 1. 로그인 사용자 정보를 읽어온다.
  		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
  		/**
  		 * apiResult json 구조 {"resultcode":"00", "message":"success",
  		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
  		 **/

  		// 2. String형식인 apiResult를 json형태로 바꿈
  		JSONParser parser = new JSONParser();
  		Object obj = (org.json.simple.JSONObject) parser.parse(apiResult);
  		org.json.simple.JSONObject jsonObj = (JSONObject) obj;

  		// 3. 데이터 파싱
  		// Top레벨 단계 _response 파싱
  		JSONObject response_obj = (JSONObject) jsonObj.get("response");
  		// response의 nickname값 파싱
  		String authstatus = "1"; 
  		String email2 = (String) response_obj.get("email");
  		String dbimgpath = (String) response_obj.get("profile_image");
  		if(dbimgpath == null) {
  			dbimgpath = File.separator+"profile"+File.separator+"base.png";
  		}
  		
  		
		String[] array=email2.split("@");
        for(int i =0; i<array.length; i++){
            System.out.println("======"+array[i]);
        }
        String userid=array[0];
  		// 4.파싱 닉네임 세션으로 저장

  		HashMap<String, String> map = new HashMap<String, String>();
  		map.put("authstatus", authstatus);
  		map.put("email", email2);
  		map.put("userid", userid);
  		map.put("dbimgpath", dbimgpath);
  		map.put("admin", "naver");
  		MemberVO vo = service.webnaverlogin(map);
  		if (vo != null) {
  			session.setAttribute("login_info", vo);
  		} else {
  			service.insertNaverweb(map);
  			MemberVO memberVO = service.webnaverlogin(map);
  			session.setAttribute("login_info", memberVO);
  		}

  		model.addAttribute("result", apiResult);

  		return "home";
  	}
}
