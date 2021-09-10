package com.today.cafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import common.CommonService;
import common.CommonServiceImpl;
import data.BookmarkVO;
import data.DataServiceImpl;
import data.FileDTO;
import data.ImgVO;
import data.JsonDTO;
import data.ReviewVO;
import member.MemberServiceImpl;
import member.MemberVO;


@Controller
public class Datacontroller {
   @Autowired   private DataServiceImpl service;
   @Autowired private MemberServiceImpl memberservice;
   @Autowired private CommonService common;
    // db에 api 정보 넣기
   @ResponseBody @RequestMapping(value = "/data", produces="application/text; charset=utf-8")
   public void insertlist() {
      StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyAebWzOcoc_IIZnEy7tWnxp584h3X3W9o8&location=35.147188,126.918715&radius=1000&type=cafe");
      
      service.insertlist(url);
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
      System.out.println(searchText + "===========================");
      List<JsonDTO> JsonDTO = service.searchList(searchText);
      Map<String, List<JsonDTO>> searchlist = new HashMap<String, List<JsonDTO>>();
      searchlist.put("searchList", JsonDTO);
      return searchlist;
   }
   
   
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
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
	   System.out.println("email====map===="+email);
	   HashMap<String, String> mmap = new HashMap<String, String>();
	   mmap.put("email", email);
	   MemberVO vo =memberservice.webnaverlogin(mmap);
	   model.addAttribute("login_info", vo);
	    return "map/map3";
   }
   
   @ResponseBody
   @RequestMapping("/imgList")
   public String storeimglist(int id) {
       System.out.println("id==" + id);
       Gson gson = new Gson();
       String json = gson.toJson(service.imgList(id));
       System.out.println(json);

         return json;
    }

   //�� ���� ���� ����Ʈ ��ȸ
   
   @ResponseBody
   @RequestMapping("/reviewList")
   public List<ReviewVO> reviewList(int id) {
      List<ReviewVO> reivewvo = service.reviewList(id);
      return reivewvo;
   }
   //북마크 삽입 / 삭제
      @ResponseBody
      @RequestMapping("/bookmark")
      public String andbookmark(String id1, String userid,String storename, String address, String tel, String bookmark) {
         System.out.println(id1+storename+address+tel+bookmark);
         int id = Integer.parseInt(id1);
         if(bookmark.equals("true")) {
            BookmarkVO vo = new BookmarkVO(id,userid, storename, address, tel);
            String insert =String.valueOf(service.bookmarkinsert(vo));
            System.out.println(insert);
            return insert;
         }else if(bookmark.equals("false")) {
            BookmarkVO bookmarkvo = new BookmarkVO(userid, id);
            String delete=String.valueOf(service.bookmarkdelete(bookmarkvo));
            System.out.println(delete);
            return delete;
         }
         return "";
      }
      
      //웹 북마크 리스트
      @ResponseBody
      @RequestMapping("/bookmarkList")
      public int bookmarkList(String id1, String email) {
         System.out.println(id1+"=========="+email);
         int id = Integer.parseInt(id1);
         BookmarkVO vo = new BookmarkVO(email,id);
         return service.bookmarkList(vo);
      }
      
      ////안드로이드 북마크 리스트
      @ResponseBody
      @RequestMapping("/andbookmarkList")
      public String andbookmarkList(String id1, String userid) {
         System.out.println(id1+userid);
         int id = Integer.parseInt(id1);
         BookmarkVO vo = new BookmarkVO(userid,id);
         int result =service.bookmarkList(vo);
            if(result==1) {
               String results = "true";
               return results;
            }else if(result ==0) {
               String results = "false";
               return results;
            }
            return "";
      }
      
      //웹 북마크 리스트
      @ResponseBody
      @RequestMapping("/webbookmark")
      public String bookmark(String id1, String userid,String storename, String address,String tel) {
            int id = Integer.parseInt(id1);
            System.out.println(id+userid+storename+address+tel);
            BookmarkVO vo = new BookmarkVO(userid,id);
            int result =service.bookmarkList(vo);
            if(result ==0) {
               BookmarkVO vo1 = new BookmarkVO(id, userid,storename, address, tel);
               service.bookmarkinsert(vo1);
               return "true";
            }else if(result ==1) {
               BookmarkVO bookmarkVO = new BookmarkVO(userid, id);
               service.bookmarkdelete(bookmarkVO);
               return "false";
            }
         return "";
      }
      //웹 리뷰 삽입
      @ResponseBody
      @RequestMapping("/reviewInsert")
      public boolean reviewInsert(ReviewVO vo , HttpServletRequest req) {
    	  
         return service.reviewInsert(vo);
      }
      //웹 리뷰 삭제
      @ResponseBody
      @RequestMapping("/reviewDelete")
      public boolean reviewDelete(String id,String reviewid1,HttpServletRequest req) {
         System.out.println(reviewid1);
         int reviewid = Integer.parseInt(reviewid1);
         return service.reviewDelete(reviewid);
      }
      //웹 리뷰 수정
      @ResponseBody
      @RequestMapping("/reviewModify")
      public boolean reviewModify(ReviewVO vo, HttpServletRequest req) {
         return service.reviewModify(vo);
      }
      
      @RequestMapping("/new.st")
      public String store() {
         return "map/new";
      }
      @RequestMapping(value="/upload")
      public String upload( FileDTO vo,HttpSession session,MultipartFile file){
         if(file.getSize() >0){
            vo.setFilename(file.getOriginalFilename());
            vo.setFilepath(common.upload("map", file, session));
         }
         service.ImgInsert(vo);
         return "map/new";
      }
      
      @ResponseBody
      @RequestMapping("/profileimg")
      public List<ReviewVO> profileimg(ReviewVO vo , HttpServletRequest req) {
    	  
         return service.profileimg(vo);
      }
      
      
      
      
}