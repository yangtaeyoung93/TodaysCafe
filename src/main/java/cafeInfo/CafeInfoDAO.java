package cafeInfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CafeInfoDAO {
   @Autowired private SqlSession sql;
   
   public List<ReviewVO> reviewList(int id){
      return sql.selectList("data.mapper.reviewList",id);
   }
   public List<ImgVO> imgList(int id){
      return sql.selectList("data.mapper.imgList",id);
   }
   public boolean bookmarkInsert(BookmarkVO vo) {
      
      return sql.insert("data.mapper.bookmarkInsert",vo) >0 ? true : false;
   }
   public boolean bookmarkDelete(BookmarkVO vo) {
      
      return sql.delete("data.mapper.bookmarkDelete",vo) >0 ? true : false;
   }
   public boolean reviewInsert(ReviewVO vo) {
      return sql.insert("data.mapper.reviewInsert",vo) > 0 ? true : false;
   }
   public boolean reviewDelete(int reviewid) {
      return sql.delete("data.mapper.reviewDelete",reviewid) > 0 ? true : false;
   }
   public boolean reviewModify(ReviewVO vo){
         return sql.update("data.mapper.reviewModify",vo) > 0 ? true : false;
   }

   public int bookmarkList(BookmarkVO vo) {
      return sql.selectOne("data.mapper.bookmarkList",vo);
   }
   public List<ReviewVO> profileimg(ReviewVO vo){
	return sql.selectList("data.mapper.profileimg",vo);
	   
   }
}