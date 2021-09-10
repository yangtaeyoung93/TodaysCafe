package mypage;

import java.util.List;

import member.MemberVO;

public interface MypageService {

	/*MypageVO profill(String userid);//프로필
*/	List<BookmarkVO> list(String email);//즐겨찾기목록
	boolean insert(MypageVO vo);
	void read(String userid);
	MypageVO detail(String email);//회원정보디테일
	boolean update(MypageVO vo);//회원정보수정
	boolean delete(String email);//회원정보삭제
	MemberVO selectMember(String email);//프로필

	

}
