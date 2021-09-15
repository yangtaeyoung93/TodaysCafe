package cafeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CafeInfoServiceImpl implements CafeInfoService {
   @Autowired private CafeInfoDAO dao;

   @Override
   public List<ImgVO> imgList(int id) {
      return dao.imgList(id);
   }

   @Override
   public List<ReviewVO> reviewList(int id) {
      return dao.reviewList(id);
   }

   @Override
   public boolean bookmarkinsert(BookmarkVO vo) {
      return dao.bookmarkInsert(vo);
   }

   @Override
   public boolean bookmarkdelete(BookmarkVO vo) {
      return dao.bookmarkDelete(vo);
   }

   @Override
   public boolean reviewInsert(ReviewVO vo) {
      return dao.reviewInsert(vo);
   }

   @Override
   public boolean reviewDelete(int reviewid) {
      return dao.reviewDelete(reviewid);
   }

   @Override
   public boolean reviewModify(ReviewVO vo) {
      return dao.reviewModify(vo);
   }

   @Override
   public int bookmarkList(BookmarkVO vo) {
      return dao.bookmarkList(vo);
   }

   @Override
   public List<ReviewVO> profileimg(ReviewVO vo) {
		return dao.profileimg(vo);
	}
   
   
}