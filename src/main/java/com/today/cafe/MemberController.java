package com.today.cafe;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
@SessionAttributes("category")
public class MemberController {
	@Autowired
	private MemberServiceImpl service;
	@Autowired
	private  CommonService common;

	// 받은 메일에서 인증링크를 눌렀을때 수행
	@RequestMapping(value = "joinConfirm", method = RequestMethod.GET)
	public String emailConfirm(@ModelAttribute("uVO") MemberVO vo, Model model, HttpSession session) throws Exception {
		System.out.println(vo.getEmail() + ": auth confirmed");
		vo.setAuthstatus(1); // authstatus를 1로,, 권한 업데이트
		service.updateAuthstatus(vo);

		model.addAttribute("auth_check", 1);
		session.removeAttribute("login_info");
		return "member/joinPost";
	}

	// 회원가입 처리
	@ResponseBody
	@RequestMapping(value = "/join.mo", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
	public String join(MemberVO vo, HttpSession session, MultipartFile file) {
		/*
		 * String basicsprofile = "192.168.0.66/cafe/resources/img/base.png";
		 * vo.setDbimgpath(basicsprofile); vo.setFileName("base.png");
		 */
		// 가입시에 난수를 만들어서 인증메일로 보냄

		System.out.println("===email==" + vo.getEmail());
		System.out.println("===file==" + file.getOriginalFilename());
		if (file.getSize() > 0) {
			vo.setFileName(file.getOriginalFilename());
			vo.setDbimgpath(common.profileFile(file, vo, session));
		} else {
			String basicsprofile = File.separator+"profile"+File.separator+"base.png";
			vo.setDbimgpath(basicsprofile);
			vo.setFileName("base.png");
		}
		String email= vo.getEmail();
		String[] array=email.split("@");
        for(int i =0; i<array.length; i++){
            System.out.println("======"+array[i]);
        }
        String userid=array[0];
        vo.setUserid(userid);
		System.out.println("=========dbimgpath==" + vo.getDbimgpath());
		StringBuffer sb = new StringBuffer("<script type='text/javascript'>");
		// 회원가입
		if (service.join(vo)) {
			// 인증 메일
			service.create(vo);
			sb.append("alert('가입해주셔서 감사합니다.'); location='/cafe/weblogin'");
		} else {
			sb.append("alert('가입에 실패했습니다. 고객센터에 문의해 주세요'); history.go(-1)");
		}
		sb.append("</script>");
		return sb.toString();
	}

	// 이메일 인증 안했을때 로그인하면 요청되는 화면 호출
	@RequestMapping("/joinNotEmail")
	public String joinNotEmail() {
		return "member/joinNotEmail";
	}

	// 아이디 중복확인 요청
	@ResponseBody
	@RequestMapping("/id_check")
	public String id_check(String email) {
		return String.valueOf(service.id_check(email));
	}

	// 로그인 및 회원가입 화면 이동
	@RequestMapping("/weblogin")
	public String login(Model model) {
		model.addAttribute("category", "");
		return "member/login";
	}

	// 2. 로그인
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String email, String userpwd, HttpSession session) {

		System.out.println("AppMemberController1 email" + email);
		System.out.println("AppMemberController1 userpwd" + userpwd);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("userpwd", userpwd);

		MemberVO vo = service.weblogin(map);
		String authstatus = "n";
		if (vo != null) {
			if (vo.getAuthstatus() == 1) {
				session.setAttribute("login_info", vo);
				authstatus = "y";
			}
		} else {
			authstatus = "d";
		}
		return authstatus;
	}

/*	// 3. 웹 로그아웃AppMembeerController에있는 로그아웃메서드로 대체
	@ResponseBody
	@RequestMapping("/weblogout")
	public String logout(HttpSession session) {

		
		session.removeAttribute("login_info");
		session.invalidate();
		
		return "cafe/weblogin";
	}*/

}
