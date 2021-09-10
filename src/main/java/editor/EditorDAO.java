package editor;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EditorDAO implements com.todays.cafe.domain.editor.EditorService {
   @Autowired private SqlSession sql;
   
   @Override
   public List<com.todays.cafe.domain.editor.EditorVO> list() {
      return sql.selectList("editor.mapper.list", "");
   }

   @Override
   public boolean insert(com.todays.cafe.domain.editor.EditorVO vo) {
      return sql.insert("editor.mapper.insert", vo)>0? true:false;
   }

   @Override
   public List<com.todays.cafe.domain.editor.EditorVO> list(String keyword) {
      return sql.selectList("editor.mapper.list", keyword);
   }
   @Override
   public com.todays.cafe.domain.editor.EditorVO detail(String title) {
      return sql.selectOne("editor.mapper.detail", title);
   }

   @Override
   public void read(String title) {
      sql.update("editor.mapper.read", title);   
   }

   @Override
   public boolean update(com.todays.cafe.domain.editor.EditorVO vo) {
      return sql.update("editor.mapper.update",vo)>0?true:false;
   }

   @Override
   public boolean delete(String title) {
      return sql.delete("editor.mapper.delete",title)>0?true:false;
   }

/*   @Override
   public List<EditorVO> aplist() {
      return sql.selectList("editor.mapper.aplist");
   }
*/
   @Override
   public boolean insert_f(FileUploadVO fvo) {
      return sql.insert("editor.mapper.insert_f", fvo) > 0 ? true : false;
   }

   @Override
   public List<com.todays.cafe.domain.editor.EditorVO> detail_f(String title) {
      return sql.selectList("editor.mapper.detail_f", title);
   }

   @Override
   public boolean delete_f(String title) {
      return sql.delete("editor.mapper.delete_f",title)>0?true:false;
   }

   @Override
   public boolean update_f(FileUploadVO fvo) {
      return sql.update("editor.mapper.update_f",fvo)>0?true:false;
   }


}