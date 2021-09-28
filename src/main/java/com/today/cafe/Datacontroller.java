package com.today.cafe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import data.DataServiceImpl;
import data.JsonDTO;
import member.MemberServiceImpl;
import member.MemberVO;


@Controller
public class Datacontroller {
   @Autowired   private DataServiceImpl service;
   @Autowired private MemberServiceImpl memberservice;
   @Autowired private CommonService common;
   private final String URL = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=";
    // db에 api 정보 넣기
   @ResponseBody @RequestMapping(value = "/data", produces="application/text; charset=utf-8")
   public void insertlist(String lat, String lng) {
       String location = lat +","+ lng;
       //StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyAebWzOcoc_IIZnEy7tWnxp584h3X3W9o8&location="+location+"&radius=1000&type=cafe");
       StringBuilder url = new StringBuilder();
       url.append(URL+location);

      service.insertlist(url.toString());
   }
   
   // 앱 json으로 db정보 뿌리기
   @ResponseBody
   @RequestMapping("/andStoreList")
   public Map<String, List<JsonDTO>> datalist( HttpServletRequest request ) {
      List<JsonDTO> JsonDTO = service.storelist();
      Map<String, List<JsonDTO>> datalist = new HashMap<String, List<JsonDTO>>();      
      datalist.put("storeList", JsonDTO);      
      return datalist;
   }
   
   
    // 안드로이드 검색
   @ResponseBody
   @RequestMapping("/andSearchlist")
   public Map<String, List<JsonDTO>> list2( HttpServletRequest request,@RequestParam String searchText) {
      List<JsonDTO> JsonDTO = service.searchList(searchText);
      Map<String, List<JsonDTO>> searchlist = new HashMap<String, List<JsonDTO>>();
      searchlist.put("searchList", JsonDTO);
      return searchlist;
   }
   
   

   // 웹json으로 db데이터 전체목록불러오기
   @ResponseBody
   @RequestMapping("/storelist")
   public List<JsonDTO> storelist() {
      return service.storelist();
   }
   
    // 웹json으로 db데이터 검색목록불러오기
   @ResponseBody
   @RequestMapping("/searchlist")
   public List<JsonDTO> searchlist(String searchText) {
      return service.searchList(searchText);
   }
   
   @ResponseBody
   @RequestMapping("/filepath")
   public List<JsonDTO> filepath() {
      return service.filepath();
   }

    @ResponseBody
    @RequestMapping("/optionList")
    public List<JsonDTO> optionList(String smoking, String pet, String parking, String searchText) {
      HashMap<String, String> option = new HashMap<String, String>();
         
        option.put("smoking", smoking);
        option.put("pet", pet);
        option.put("parking", parking);
        option.put("searchText",searchText);
        return service.optionList(option);
    }

   @RequestMapping("/map")
   public String map( Model model, String email) {
	   HashMap<String, String> mmap = new HashMap<String, String>();
	   mmap.put("email", email);
	   MemberVO vo =memberservice.webnaverlogin(mmap);
	   model.addAttribute("login_info", vo);
	    return "map/map3";
   }
   

      
}