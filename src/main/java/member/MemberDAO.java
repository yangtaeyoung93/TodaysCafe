package member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.HashMap;


@Repository
public class MemberDAO implements MemberService {
   @Autowired private SqlSession sql;
   
   
   //아이디 중복검사
   @Override
   public boolean id_check(String email) {
      return (Integer)sql.selectOne("member.mapper.id_check", email) >0? false : true;
   }
   
   //회원가입
   @Override
   public boolean join(MemberVO vo) {
      return sql.insert("member.mapper.insert", vo)>0? true: false;
   }

   @Override
   public boolean update(MemberVO vo) {
      return false;
   }

   //회원 탈퇴
   @Override
   public boolean delete(String email) {
      return sql.delete("member.mapper.userdelete",email)>0? true : false;
   }

 //3. 웹 로그인
 	@Override
 	public MemberVO weblogin(HashMap map) {
 		return sql.selectOne("member.mapper.weblogin", map);
 	}
 	
 	//4. 웹 네이버 로그인
 		@Override
 		public MemberVO webnaverlogin(HashMap map) {
 			return sql.selectOne("member.mapper.webnaverlogin", map);
 		}

    //안드로이드 아이디 중복 확인
      @Override
      public boolean appIdCheck(String email) {
         return (Integer)sql.selectOne("member.mapper.id_check", email)>0? false : true;
      }
      
      
      //안드로이드 회원가입
      @Override
      public boolean appinsert(Model model) {
         String email = (String) model.asMap().get("email");
         String userpwd = (String) model.asMap().get("userpwd");
         String dbimgpath =(String) model.asMap().get("dbimgpath");
         String fileName =(String) model.asMap().get("fileName");
         String userid =(String) model.asMap().get("userid");
         MemberVO vo = new MemberVO(email, userpwd, dbimgpath, fileName, userid);
         
         
         return sql.insert("member.mapper.insert", vo) > 0? true: false;
      }

         //1. 앱 로그인
         @Override
         public MemberVO applogin(HashMap map) {
            return sql.selectOne("member.mapper.applogin", map);
         }
         
         //2. 앱 로그인 체크
         @Override
         public MemberVO apploginck(HashMap map) {
            return sql.selectOne("member.mapper.applogin", map);
         }
         
       //안드로이드 회원정보 수정
      @Override
      public boolean appmemberupdate(Model model) {
         String email = (String) model.asMap().get("email");
         String userpwd = (String) model.asMap().get("userpwd");
         String dbimgpath =(String) model.asMap().get("dbImgPath");
         String fileName =(String) model.asMap().get("fileName");
         String userid =(String) model.asMap().get("userid");
         MemberVO vo = new MemberVO(email, userpwd, dbimgpath, fileName, userid);
         return sql.update("member.mapper.memberUpDate", vo)> 0? true: false;
      }

            
      //메일인증 완료시 상태값을 1로 변경
      @Override
      public int updateAuthstatus(MemberVO vo) {
         return sql.update("member.mapper.updateAuthstatus",vo);
      }
      
      public void updateAuthkey(MemberVO vo) {
         sql.update("member.mapper.updateAuthkey", vo);
      }
      
      
    //5. appResponse
  	@Override
  	public MemberVO androidResponse(String email) {
  		return  sql.selectOne("member.mapper.androidResponse", email);
  	}
  	
  	@Override
	public MemberVO selectMember(String email) {
		return sql.selectOne("member.mapper.emailmatching", email);
	}
	
	
	@Override
	public boolean insertNaver(HashMap map) {
		return sql.insert("member.mapper.insertNaver", map) > 0 ? true : false;
	}

	
	@Override
	public boolean insertNaverweb(String email) {
		return false;
	}

	
	@Override
	public MemberVO webResponse(String email) {
		return sql.selectOne("member.mapper.webResponse", email);
	}

	@Override
	public MemberVO insertNaverweb(HashMap map) {
		return sql.selectOne("member.mapper.insertNaverweb", map);
	}
   
}