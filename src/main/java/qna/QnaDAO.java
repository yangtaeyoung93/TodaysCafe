package qna;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDAO implements QnaService {
	@Autowired private SqlSession sql;
	
	@Override
	public boolean insert(QnaVO vo) {
		return sql.insert("qna.mapper.insert",vo)>0? true:false;
	}

	@Override
	public List<QnaVO> list() {
		return sql.selectList("qna.mapper.list");
	}

	@Override
	public QnaVO detail(int id) {
		return sql.selectOne("qna.mapper.detail",id);
	}

	@Override
	public void read(int id) {
		sql.update("qna.mapper.read",id);
	}

	@Override
	public boolean update(QnaVO vo) {
		return sql.update("qna.mapper.update",vo)>0?true:false;
	}

	@Override
	public boolean delete(int id) {
		return sql.delete("qna.mapper.delete",id)>0?true:false;
	}

	@Override
	public List<QnaVO> list(HashMap<String, String> map) {
		return sql.selectList("qna.mapper.list",map);
	}

	@Override
	public QnaPage list(QnaPage vo) {
		vo.setTotalList((Integer)sql.selectOne("qna.mapper.total",vo));
		
		List<QnaVO>list=sql.selectList("qna.mapper.list",vo);
		vo.setList(list);
		return vo;
	}

	@Override
	public boolean reply_insert(QnaVO vo) {
		return sql.insert("qna.mapper.reply_insert",vo)>0?true:false;
	}

}
