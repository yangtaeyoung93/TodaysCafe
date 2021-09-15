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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberVO;
import mypage.BookmarkVO;
import mypage.MypageServiceImpl;
import mypage.MypageVO;

@Controller @SessionAttributes("category")
@RequestMapping("mypage")
public class MypageController {

	@Autowired private MypageServiceImpl service;

		//안드로이드에서 마이페이지 요청
	   @ResponseBody
	   @RequestMapping("/bookmark")
	   public Map<String, List<BookmarkVO>> anbookmark(HttpServletRequest httpServletRequest, String email) {
	      List<BookmarkVO> bookmarkVO = service.list(email);
	      
	      //for문= 디비에 저장된 이미지 파일들의 저장경로 방식을 불러들이는 순간에만 바꿔준다
	      //리눅스일때와 윈도우즈 일때 \,/로 다르다
	      for (int i = 0; i < bookmarkVO.size(); i++) {
	          String filepath = bookmarkVO.get(i).getFilepath().replace("\\", "/");
	          
	          bookmarkVO.get(i).setFilepath(filepath);
	       }
	      
	      Map<String, List<BookmarkVO>> BoardVal = new HashMap<String, List<BookmarkVO>>();
	      BoardVal.put("contacts", bookmarkVO);       
	      return BoardVal;
	      
	   }
	   
	   /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   
	   
	 //회원탈퇴
		@RequestMapping(value = "/delete" ,method = RequestMethod.DELETE)
		public String delete(String email, HttpSession session) {
			File f = new File(session.getServletContext().getRealPath("resources"+service.detail(email).getDbimgpath()));
			if(f.exists()) f.delete();
			service.delete(email);
			session.removeAttribute("login_info");
			return "home";
		}
			
	   
		//news insert
		@RequestMapping(value = "/insert", method = RequestMethod.POST)
		public String insert(MypageVO vo) {
			service.insert(vo);
			return "redirect:list.ne";
		}
		
		//news insert view
		@RequestMapping(value = "/new",method = RequestMethod.GET)
		public String news() {
			return "mypage/new";
		}
	   
	   
		
	 //마이페이지화면요청
		@RequestMapping(value = "/info",method = RequestMethod.GET)
		public String list(Model model , HttpSession session, MemberVO vo1, String email) {
			session.setAttribute("login_info",service.selectMember(vo1.getEmail()));//비교를 위해 로그인한 email값 넣기
			model.addAttribute("mypagelist",service.list(email));//북마크
			return "mypage/mypage";
		}
		
		
		
		//프로필디테일페이지요청 db리스트 뿌리기
		@RequestMapping(value = "/detail",method = RequestMethod.GET)
		public String detail(Model model, String email) {
			// 선택한 공지글의 정보를 DB에서 조회해온 후
			// 상세화면에 출력할 수 있도록 Model 에 담는다.
			model.addAttribute("vo", service.detail(email));
	
			return "mypage/detail";
		}
		
		// 마이페이지수정화면 요청
		@RequestMapping(value = "/modify",method = RequestMethod.GET)
		public String modify(String email, Model model) {
			// DB에서 해당 공지글 정보를 조회해온 후
			// 수정화면에 출력하도록 Model에 담는다.
			model.addAttribute("vo", service.detail(email));
			return "mypage/modify";
		}
		
		
		@Autowired private CommonService common;
		
		//회원정보수정  
		@RequestMapping(value = "/update",method = RequestMethod.POST)
		public String update(MemberVO vo1,MypageVO vo, MultipartFile file, HttpSession session, String attach) {
			MypageVO mypage=service.detail(vo.getEmail());
			String uuid=mypage.getDbimgpath();
			uuid=session.getServletContext().getRealPath("resources")+uuid;
			if(file.getSize()>0) {
				vo.setFilename(file.getOriginalFilename());
				vo.setDbimgpath(common.profileFile(file, vo1, session));
				File f=new File(uuid);
				if(f.exists()) f.delete();
			}else {
				if(attach.equals("y")) {
					File f=new File(uuid);
					if(f.exists()) f.delete();
				}else {
					vo.setFilename(mypage.getFilename());
					vo.setDbimgpath(mypage.getDbimgpath());
				}
				
			}
			service.update(vo);
			session.setAttribute("login_info",service.selectMember(vo1.getEmail()));
			return"redirect:home?email="+vo.getEmail();//수정완료후 디테일페이지로
		}
}
