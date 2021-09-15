package cafeInfo;

import java.util.List;


public interface CafeInfoService {

   
   List<ImgVO> imgList(int id);
   List<ReviewVO> reviewList(int id);
   boolean bookmarkinsert(BookmarkVO vo);
   boolean bookmarkdelete(BookmarkVO vo);
   boolean reviewInsert(ReviewVO vo);
   boolean reviewDelete(int reviewid);
   boolean reviewModify(ReviewVO vo);
   int bookmarkList(BookmarkVO vo);
   List<ReviewVO> profileimg(ReviewVO vo);

}