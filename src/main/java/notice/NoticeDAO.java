package notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class NoticeDAO implements NoticeService {
	@Autowired private SqlSession sql;
	@Override
	public boolean insert(NoticeVO vo) {
		return sql.insert("notice.mapper.insert",vo)>0? true:false;
	}

	@Override
	public List<NoticeVO> list() {
		return sql.selectList("notice.mapper.list");
	}

	@Override
	public NoticeVO detail(int id) {
		return sql.selectOne("notice.mapper.detail",id);
	}

	@Override
	public void read(int id) {
		sql.update("notice.mapper.read",id);		
	}

	@Override
	public boolean update(NoticeVO vo) {
		return sql.update("notice.mapper.update",vo)>0?true:false;
	}

	@Override
	public boolean delete(int id) {
		return sql.delete("notice.mapper.delete",id)>0?true:false;
	}

	@Override
	public List<NoticeVO> list(HashMap<String, String> map) {
		return sql.selectList("notice.mapper.list",map);
	}

	@Override
	public NoticePage list(NoticePage vo) {
				vo.setTotalList((Integer)sql.selectOne("notice.mapper.total",vo));
				List<NoticeVO>list=sql.selectList("notice.mapper.list",vo);
				vo.setList(list);
				return vo;
	}

	@Override
	public List<NoticeVO> nolist() {
		return sql.selectList("notice.mapper.nolist");
	}

}
