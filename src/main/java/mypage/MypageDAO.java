package mypage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;

@Repository
public class MypageDAO implements MypageService{
	@Autowired private SqlSession sql;
	
	//즐겨찾기목록
	@Override
	public List<BookmarkVO> list(String email) {
		List<BookmarkVO> list = sql.selectList("mypage.mapper.list", email);//단건이아니라 여러건이니깐 리스트에 먼전 담아주고 담아준 리스트를 리턴
		return list;
	}

	public boolean insert(MypageVO vo) {
		return sql.insert("mypage.mapper.insert",vo)>0?true:false;
	}

	//프로필디테일페이지요청 db리스트 뿌리기
	@Override
	public MypageVO detail(String email) {
		MypageVO vo = new MypageVO();
		vo = sql.selectOne("mypage.mapper.detail",email);
		return vo;
	}

	@Override
	public boolean update(MypageVO vo) {
		return sql.update("mypage.mapper.update", vo)>0?true:false;
	}

	@Override
	public boolean delete(String email) {
		return sql.delete("mypage.mapper.delete", email)>0 ? true:false;
	}

	//프로필
	@Override
	public MemberVO selectMember(String email) {
		return sql.selectOne("mypage.mapper.selectMember", email);
	}
	
	
}
