package data;

import java.util.HashMap;
import java.util.List;


public interface DataService {
   String insertlist(StringBuilder url);
   List<JsonDTO> andsearchList(String searchText);
   List<JsonDTO> searchList(String searchText);
   List<JsonDTO> storelist();
    List<JsonDTO> andstorelist();
   List<JsonDTO> filepath();
   List<JsonDTO> optionList(HashMap<String, String> option);

   
   List<ImgVO> imgList(int id);
   List<ReviewVO> reviewList(int id);
   boolean bookmarkinsert(BookmarkVO vo);
   boolean bookmarkdelete(BookmarkVO vo);
   boolean reviewInsert(ReviewVO vo);
   boolean reviewDelete(int reviewid);
   boolean reviewModify(ReviewVO vo);
   int bookmarkList(BookmarkVO vo);
   boolean ImgInsert(FileDTO vo);
   List<ReviewVO> profileimg(ReviewVO vo);

}