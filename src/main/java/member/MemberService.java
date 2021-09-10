package member;

import org.springframework.ui.Model;

import java.util.HashMap;


public interface MemberService {
   boolean id_check(String email); //아이디 중복 확인
   boolean join(MemberVO vo); // 회원 가입
   boolean update(MemberVO vo); // 회원 정보 수정
   boolean delete(String email); //회원 탈퇴
   MemberVO weblogin(HashMap map);				//3. 웹) 로그인
   MemberVO webnaverlogin(HashMap map);		//4. 웹) 네이버 로그인
   boolean insertNaverweb(String email);
   MemberVO webResponse(String email);
	MemberVO insertNaverweb(HashMap map);
   
   
   //안드로이드 서비스 
   boolean appinsert(Model model);// app 회원가입
   boolean appmemberupdate(Model model);// app 회원정보 수정
   boolean appIdCheck(String email); //app 아이디 중복 확인
   MemberVO applogin(HashMap map);      //앱 로그인
   MemberVO apploginck(HashMap map);   //앱 로그인 체크
   MemberVO androidResponse(String email);		//5. 앱) 안드로이드에서 네이버 정보 요청
   MemberVO selectMember(String email);		//6. 뭘까요
	boolean insertNaver(HashMap map);//7. 이거도 뭘까요
   
   //메일 인증
   int updateAuthstatus(MemberVO vo);
   
}