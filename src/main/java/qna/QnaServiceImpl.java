package qna;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired private QnaDAO dao;
	
	@Override
	public boolean insert(QnaVO vo) {
		return dao.insert(vo);
	}

	@Override
	public List<QnaVO> list() {
		return dao.list();
	}

	@Override
	public QnaVO detail(int id) {
		return dao.detail(id);
	}

	@Override
	public void read(int id) {
		dao.read(id);
	}

	@Override
	public boolean update(QnaVO vo) {
		return dao.update(vo);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public List<QnaVO> list(HashMap<String, String> map) {
		return dao.list(map);
	}

	@Override
	public QnaPage list(QnaPage vo) {
		return dao.list(vo);
	}

	@Override
	public boolean reply_insert(QnaVO vo) {
		return dao.reply_insert(vo);
	}

}
