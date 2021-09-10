package com.today.cafe;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberVO;
import mypage.BookmarkVO;
import mypage.MypageServiceImpl;
import mypage.MypageVO;

@Controller @SessionAttributes("category")
public class MypageController {
	
	//@Resource(name = "service")private MypageService service;
	@Autowired private MypageServiceImpl service;
	
	
	
	
		//안드로이드에서 마이페이지 요청
	   @ResponseBody
	   @RequestMapping("/anbookmark") 
	   public Map<String, List<BookmarkVO>> anbookmark(HttpServletRequest httpServletRequest, String email) {
	      List<BookmarkVO> bookmarkVO = service.list(email);
	      
	      //for문= 디비에 저장된 이미지 파일들의 저장경로 방식을 불러들이는 순간에만 바꿔준다
	      //리눅스일때와 윈도우즈 일때 \,/로 다르다
	      for (int i = 0; i < bookmarkVO.size(); i++) {
	          String filepath = bookmarkVO.get(i).getFilepath().replace("\\", "/");
	          
	          bookmarkVO.get(i).setFilepath(filepath);
	          System.out.println("=====================" + filepath);
	       }
	      
	      //System.out.println(bookmarkVO);
	      Map<String, List<BookmarkVO>> BoardVal = new HashMap<String, List<BookmarkVO>>();
	      BoardVal.put("contacts", bookmarkVO);       
	      return BoardVal;
	      
	   }
	   
	   /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   
	   
	 //회원탈퇴
		@RequestMapping("/delete.my")
		public String delete(String email, HttpSession session) {
			File f = new File(session.getServletContext().getRealPath("resources"+service.detail(email).getDbimgpath()));
			if(f.exists()) f.delete();
			service.delete(email);
			session.removeAttribute("login_info");
			return "home";
		}
			
	   
		//news insert
		@RequestMapping("/insert.ne")
		public String insert(MypageVO vo) {
			service.insert(vo);
			return "redirect:list.ne";
		}
		
		//news insert view
		@RequestMapping("/new")
		public String news() {
			return "mypage/new";
		}
	   
	   
		
	 //마이페이지화면요청
		@RequestMapping("/mypage")
		public String list(Model model , HttpSession session, MemberVO vo1, String email) {
			//String userid = "daihyun";
			//System.out.println(userid);
			//model.addAttribute("vo", service.profill(userid));//프로필
			/* 			MypageVO vo=service.profill(userid);//프로필
			 */
			//System.out.println(model + " - " + userid);
			//System.out.println(" - "+vo.getDbimgpath());
			session.setAttribute("login_info",service.selectMember(vo1.getEmail()));//비교를 위해 로그인한 email값 넣기
			System.out.println("마이페이지"+email);
			model.addAttribute("mypagelist",service.list(email));//북마크
			//return "mypage/mypage";
			return "mypage/mypage";
		}
		
		
		
		//프로필디테일페이지요청 db리스트 뿌리기
		@RequestMapping("/detail.my")
		public String detail(Model model, String email) {
			//userid = "daihyun"; 
//				@RequestParam(required=false) Integer read) {
			// 선택한 공지글의 정보를 DB에서 조회해온 후
			// 상세화면에 출력할 수 있도록 Model 에 담는다.
			System.out.println("디테일페이지요청"+email);
			model.addAttribute("vo", service.detail(email));
	
			return "mypage/detail";
		}
		
		// 마이페이지수정화면 요청
		@RequestMapping("/modify.my")
		public String modify(String email, Model model) {
			// DB에서 해당 공지글 정보를 조회해온 후
			// 수정화면에 출력하도록 Model에 담는다.
			model.addAttribute("vo", service.detail(email));
			return "mypage/modify";
		}
		
		
		@Autowired private CommonService common;
		
		//회원정보수정  
		@RequestMapping("/update.my")
		public String update(MemberVO vo1,MypageVO vo, MultipartFile file, HttpSession session, String attach) {
			//�솕硫댁뿉�꽌 蹂�寃쎌엯�젰�븳 �젙蹂대�� DB�뿉 ���옣�븳 �썑
			//�긽�꽭�솕硫댁쑝濡� �뿰寃�
			//�썝�옒 泥⑤��맂 �뙆�씪�씠 �뾾�뒗�뜲 蹂�寃쏀븯硫댁꽌 泥⑤��븯�뒗 寃쎌슦
			//�썝�옒 DB�뿉 ���옣�릺�뼱 �엳�뜕 泥⑤��뙆�씪�젙蹂대�� 議고쉶�빐 �삩�떎.
			MypageVO mypage=service.detail(vo.getEmail());
			String uuid=mypage.getDbimgpath();
			//upload/notice/2019/07/03/asd-4654-e4545-adc.txt
			uuid=session.getServletContext().getRealPath("resources")+uuid;
			//d:/study_web/.../upload/notice/2019/07/03/asd-4654-e4545-adc.txt
			if(file.getSize()>0) {
				vo.setFilename(file.getOriginalFilename());
				vo.setDbimgpath(common.profileFile(file, vo1, session));
				//�썝�옒 泥⑤��맂 �뙆�씪�씠 �엳�뿀怨� 洹� �뙆�씪�쓣 �떎瑜� �뙆�씪濡� 蹂�寃쏀빐 泥⑤��븯�뒗 寃쎌슦
				//�썝�옒 泥⑤��맂 �뙆�씪�� �궘�젣
				File f=new File(uuid);
				if(f.exists()) f.delete();
			}else {
				//�썝�옒 泥⑤��맂 �뙆�씪�씠 �엳�뿀�뒗�뜲 泥⑤��맂 �뙆�씪�쓣 �궘�젣�븯�뒗 寃쎌슦
				if(attach.equals("y")) {
					File f=new File(uuid);
					if(f.exists()) f.delete();
				}else {
					//�썝�옒 泥⑤��맂 �뙆�씪�씠 �엳怨� 洹몃�濡� �궗�슜�븯�뒗 寃쎌슦
					vo.setFilename(mypage.getFilename());
					vo.setDbimgpath(mypage.getDbimgpath());
				}
				
			}
			service.update(vo);
			session.setAttribute("login_info",service.selectMember(vo1.getEmail()));
			return"redirect:home?email="+vo.getEmail();//수정완료후 디테일페이지로
		}
	
	
		
		
	/*@RequestMapping("/delete.my")
		public String delete(String userid, HttpSession session) {
			//泥⑤��맂 �뙆�씪�씠 �엳�떎硫� 臾쇰━�쟻 怨듦컙�뿉�꽌 �빐�떦 �뙆�씪�쓣 �궘�젣
			File f = new File(session.getServletContext().getRealPath("resources"+service.detail(userid).getDbimgpath()));
			if(f.exists()) f.delete();
			//�빐�떦 怨듭�湲��쓣 DB�뿉�꽌 �궘�젣�븳 �썑
			//紐⑸줉�솕硫댁쑝濡� �뿰寃�
			service.delete(userid);
			return "redirect:mypage";
		}*/
		
		
	


}
