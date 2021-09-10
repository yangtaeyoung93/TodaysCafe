package notice;

import java.util.HashMap;
import java.util.List;


public interface NoticeService {
	boolean insert(NoticeVO vo);
	List<NoticeVO> list();
	List<NoticeVO> list(HashMap<String, String>map);
	NoticePage list(NoticePage vo);
	NoticeVO detail(int id);
	void read(int id);
	boolean update(NoticeVO vo);
	boolean delete(int id);
	
	List<NoticeVO> nolist();
}
