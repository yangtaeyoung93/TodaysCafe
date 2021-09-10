package data;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDAO {
   @Autowired private SqlSession sql;
   
   
   public void insertlist(DataDTO data) {
      sql.insert("data.mapper.insertlist", data);
   }

   public List<JsonDTO> searchList(String searchText) {
      return sql.selectList("data.mapper.searchList",searchText);
   }

   public List<JsonDTO> storelist() {
      return sql.selectList("data.mapper.storeList");
   }
   public List<JsonDTO> andsearchList(String searchText) {
         return sql.selectList("data.mapper.andsearchList",searchText);
      }
      
   public List<JsonDTO> andstorelist() {
         return sql.selectList("data.mapper.andstoreList");
      }

      public List<JsonDTO> filepath() {
            return sql.selectList("data.mapper.filepath");
         }
         
         public List<ImgVO> imgList(int id){
               return sql.selectList("data.mapper.imgList",id);
            }

         public List<JsonDTO> optionList(HashMap<String, String> option) {
            
            return sql.selectList("data.mapper.optionList",option);
         }

   public List<ReviewVO> reviewList(int id){
      return sql.selectList("data.mapper.reviewList",id);
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
   public boolean ImgInsert(FileDTO vo) {
	   return sql.insert("data.mapper.ImgInsert",vo) > 0 ? true : false;
   }
   public List<ReviewVO> profileimg(ReviewVO vo){
	return sql.selectList("data.mapper.profileimg",vo);
	   
   }
}