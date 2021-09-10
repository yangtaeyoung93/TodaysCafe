package editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorServiceImpl implements com.todays.cafe.domain.editor.EditorService {
   @Autowired private EditorDAO dao;
   
   @Override
   public List<EditorVO> list() {
      return dao.list();
   }

   @Override
   public boolean insert(EditorVO vo) {
      return dao.insert(vo);
   }

   @Override
   public List<EditorVO> list(String keyword) {
      return dao.list(keyword);
   }

   @Override
   public EditorVO detail(String title) {
      return dao.detail(title);
   }

   @Override
   public void read(String title) {
      dao.read(title);      
   }

   @Override
   public boolean update(EditorVO vo) {
      
      return dao.update(vo);
   }

   @Override
   public boolean delete(String title) {
      return dao.delete(title);
   }

/*   @Override
   public List<EditorVO> aplist() {
      return dao.aplist();
   }*/

   @Override
   public boolean insert_f(FileUploadVO fvo) {
      return dao.insert_f(fvo);
   }

   @Override
   public List<EditorVO> detail_f(String title) {
      return dao.detail_f(title);
   }

   @Override
   public boolean delete_f(String title) {
      return dao.delete_f(title);
   }

   @Override
   public boolean update_f(FileUploadVO fvo) {
      return dao.update_f(fvo);
   }


}