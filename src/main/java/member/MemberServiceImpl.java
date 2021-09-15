package member;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.HashMap;


@Service
public class MemberServiceImpl implements MemberService {
   @Autowired private MemberDAO dao;
   @Autowired private JavaMailSender mailSender;
   
   @Override
   public boolean id_check(String email) {
      return dao.id_check(email);
   }

   @Override
   public boolean join(MemberVO vo) {
      return dao.join(vo);
   }

   @Override
   public boolean update(MemberVO vo) {
      return false;
   }

   //회원탈퇴
   @Override
   public boolean delete(String email) {
      return dao.delete(email);
   }

   //3. 웹 로그인
      @Override
      public MemberVO weblogin(HashMap map) {
         return dao.weblogin(map);
      }

    //안드로이드 회원가입
      @Override
      public boolean appinsert(Model model) {
         return dao.appinsert(model);
      }
      
      
      //안드로이드 아이디 중복확인
      @Override
      public boolean appIdCheck(String email) {
         return dao.appIdCheck(email);
      }

      //1. 앱 로그인
         @Override
         public MemberVO applogin(HashMap map) {
            return dao.applogin(map);
         }

         //2. 앱 로그인 체크
         @Override
         public MemberVO apploginck(HashMap map) {
            return dao.applogin(map);
         }
         
       //app회원정보 수정
      @Override
      public boolean appmemberupdate(Model model) {
         return dao.appmemberupdate(model);
      }
      
      //인증 메일 전송
      @Transactional
      public void create(MemberVO vo) {
         String authkey = new TempKey().getKey(50, false);
         vo.setAuthkey(authkey);
         dao.updateAuthkey(vo);
         HtmlEmail mail=new HtmlEmail();
         mail.setHostName("smtp.naver.com");
         mail.setCharset("utf-8");
         
         mail.setAuthentication("this2sid@naver.com","tkfkdgo1222");
         mail.setSSLOnConnect(true);
         try {
            mail.setFrom("this2sid@naver.com","ToDayCafe 관리자");
            //받는 이의 정보
            mail.addTo(vo.getEmail());
            //메일제목, 내용 작성
            mail.setSubject("ToDay Cafe 메일 인증");
            mail.setMsg("가입해주셔서 감사합니다 아래 링크를 눌러 메일 인증을 해주세요");
            StringBuffer content = new StringBuffer("<html>");
            content.append("<body>");
            content.append("<h1>[이메일 인증 링크]</h1>");
            content.append("<a href='http://localhost/cafe/joinConfirm?email=");
            content.append(vo.getEmail());
            content.append("&authkey=");
            content.append(authkey);
            content.append("' target='_blenk'>이메일 인증 확인</a>");
            content.append("</body>");
            content.append("</html>");
            mail.setHtmlMsg(content.toString());
            //메일보내기
            mail.send();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      @Override
      public int updateAuthstatus(MemberVO vo) {
         return dao.updateAuthstatus(vo);
      }
      
    //4. 웹 네이버 로그인
  	@Override
  	public MemberVO webnaverlogin(HashMap map) {
  		return dao.webnaverlogin(map);
  	}
  	
  	//5. appResponse
  	@Override
  	public MemberVO androidResponse(String email) {
  		return dao.androidResponse(email);
  	}

  		
  	@Override
  	public MemberVO selectMember(String email) {
  		return dao.selectMember(email);
  	}

  	
  	@Override
  	public boolean insertNaver(HashMap map) {
  		return dao.insertNaver(map);
  	}
  	
  	//DB != NAVER = NAVER LOGIN
  	@Override
  	public boolean insertNaverweb(String email) {
  		return false;
  	}

  	@Override
  	public MemberVO webResponse(String email) {
  		return dao.webResponse(email);
  	}

  	@Override
  	public MemberVO insertNaverweb(HashMap map) {
  		return dao.insertNaverweb(map);
  	}
      
   
}