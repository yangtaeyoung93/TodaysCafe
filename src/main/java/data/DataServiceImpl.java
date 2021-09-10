package data;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataServiceImpl implements DataService{
   @Autowired private DataDAO dao;

   @Override
   public String insertlist(StringBuilder url) {
      String result = url.toString();
      try {
         HttpURLConnection conn = (HttpURLConnection)new URL(result).openConnection();
         
         
         JSONParser jsonParser = new JSONParser();
   
         JSONObject jsonObj = (JSONObject) JSONValue.parse(new InputStreamReader(conn.getInputStream(),"utf-8"));
         
         
         JSONArray jsonArr = (JSONArray) jsonObj.get("results");
         
         for(int i = 0; i < jsonArr.size(); i++) {
            JSONObject object = (JSONObject) jsonArr.get(i);
            System.out.println("Aaaaaaaa"+object);
            
            JSONObject geometry = (JSONObject) object.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            String lat = location.get("lat").toString();
            String lng = location.get("lng").toString();
            String name  = (String) object.get("name");
            String vicinity = (String) object.get("vicinity");
            
            /*JSONObject opening_hours = (JSONObject) object.get("opening_hours");*/
            
            System.out.println("object" +lat+"+"+lng+"+"+name+"+"+vicinity);
            DataDTO data = new DataDTO(lat, lng, name, vicinity);
//            ArrayList<DataDTO> datalist = new ArrayList<DataDTO>();
//            datalist.add(data);
            
//            System.out.println("object" +lat+"+"+lng+"+"+name+"+"+vicinity);
            dao.insertlist(data);
            
         }

         
      }catch(Exception e) {
         System.out.println(e.getMessage());
      }
   
      return result;
   }
   @Override
   public List<JsonDTO> storelist() {
      return dao.storelist();
   }
   @Override
   public List<JsonDTO> searchList(String searchText) {
      return dao.searchList(searchText);
   }

    @Override
     public List<JsonDTO> andstorelist() {
       return dao.andstorelist();
    }
    @Override
     public List<JsonDTO> andsearchList(String searchText) {
       return dao.andsearchList(searchText);
    }
    @Override
     public List<JsonDTO> optionList(HashMap<String, String> option) {
       return dao.optionList(option);
    }
    @Override
     public List<JsonDTO> filepath() {
       return dao.filepath();
    }
       
/////////////////////////////////////////////////
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
	public boolean ImgInsert(FileDTO vo) {
		return dao.ImgInsert(vo);
	}
	@Override
	public List<ReviewVO> profileimg(ReviewVO vo) {
		return dao.profileimg(vo);
	}
   
   
}