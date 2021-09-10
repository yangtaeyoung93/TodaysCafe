package editor;

import java.util.List;


public interface EditorService {
   List<EditorVO> list();
   List<EditorVO> list(String keyword);
   boolean insert(EditorVO vo);
   boolean insert_f(FileUploadVO fvo);
   EditorVO detail(String title);
   List<EditorVO> detail_f(String title);
   void read(String title);
   boolean update(EditorVO vo);
   boolean update_f(FileUploadVO fvo);
   boolean delete(String title);
   boolean delete_f(String title);
   
   //List<EditorVO> aplist();
}