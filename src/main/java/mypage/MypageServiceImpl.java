package mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class MypageServiceImpl implements MypageService{
	@Autowired private MypageDAO dao;
	

	//즐겨찾기
	@Override
	public List<BookmarkVO> list(String email) {
		return dao.list(email);
	}


	public boolean insert(MypageVO vo) {
		return dao.insert(vo);
	}


	//프로필디테일페이지요청 db리스트 뿌리기
	@Override
	public MypageVO detail(String email) {
		return dao.detail(email);
	}
	

	@Override
	public boolean update(MypageVO vo) {
		return dao.update(vo);
	}
	
	@Override
	public boolean delete(String email) {
		return dao.delete(email);
	}


	@Override
	public MemberVO selectMember(String email) {
		return dao.selectMember(email);
	}
	
	



}
