package qna;

import java.util.HashMap;
import java.util.List;

public interface QnaService {
   boolean insert(QnaVO vo);
   boolean reply_insert(QnaVO vo);
   List<QnaVO> list();
   List<QnaVO> list(HashMap<String,String> map);
   QnaPage list(QnaPage vo);
   QnaVO detail(int id);
   void read(int id);
   boolean update(QnaVO vo);
   boolean delete(int id);
}