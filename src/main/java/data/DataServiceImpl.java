package data;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import cafeInfo.BookmarkVO;
import cafeInfo.ImgVO;
import cafeInfo.ReviewVO;
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
   public String insertlist(String url) {
      String result = url.toString();
      try {
         HttpURLConnection conn = (HttpURLConnection)new URL(result).openConnection();
         
         JSONParser jsonParser = new JSONParser();
         JSONObject jsonObj = (JSONObject) JSONValue.parse(new InputStreamReader(conn.getInputStream(),"utf-8"));
         JSONArray jsonArr = (JSONArray) jsonObj.get("results");
         
         for(int i = 0; i < jsonArr.size(); i++) {
            JSONObject object = (JSONObject) jsonArr.get(i);

            JSONObject geometry = (JSONObject) object.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            String lat = location.get("lat").toString();
            String lng = location.get("lng").toString();
            String name  = (String) object.get("name");
            String vicinity = (String) object.get("vicinity");
            
            DataDTO data = new DataDTO(lat, lng, name, vicinity);
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

}