package data;

import cafeInfo.BookmarkVO;
import cafeInfo.ImgVO;
import cafeInfo.ReviewVO;

import java.util.HashMap;
import java.util.List;


public interface DataService {
   String insertlist(String  url);
   List<JsonDTO> andsearchList(String searchText);
   List<JsonDTO> searchList(String searchText);
   List<JsonDTO> storelist();
    List<JsonDTO> andstorelist();
   List<JsonDTO> filepath();
   List<JsonDTO> optionList(HashMap<String, String> option);


}