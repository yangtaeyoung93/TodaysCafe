package com.today.cafe;

import cafeInfo.CafeInfoServiceImpl;
import com.google.gson.Gson;
import common.CommonService;
import cafeInfo.BookmarkVO;
import data.DataServiceImpl;
import cafeInfo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/cafe/info")
public class CafeInfoController {
    @Autowired
    private CafeInfoServiceImpl service;
    @Autowired
    private CommonService common;


    @ResponseBody
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public String storeimglist(int id) {
        Gson gson = new Gson();
        String json = gson.toJson(service.imgList(id));
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public List<ReviewVO> reviewList(int id) {
        List<ReviewVO> reivewvo = service.reviewList(id);
        return reivewvo;
    }
    //북마크 삽입 / 삭제
    @ResponseBody
    @RequestMapping(value = "/bookmark",method = RequestMethod.POST)
    public String andbookmark(BookmarkVO vo) {
        if(vo.isBookmark()) {
            String insert =String.valueOf(service.bookmarkinsert(vo));
            return insert;
        }else {
            String delete=String.valueOf(service.bookmarkdelete(vo));
            return delete;
        }
    }


    ////안드로이드 북마크 리스트
    @ResponseBody
    @RequestMapping("/andbookmarkList")
    public String andbookmarkList(int id, String userid) {
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
    @RequestMapping(value = "/bookmark",method = RequestMethod.POST)
    public String bookmark(BookmarkVO bookmarkVO) {
        int result =service.bookmarkList(bookmarkVO);
        if(result ==0) {
            service.bookmarkinsert(bookmarkVO);
            return "true";
        }else if(result ==1) {
            service.bookmarkdelete(bookmarkVO);
            return "false";
        }
        return "";
    }
    //웹 리뷰 삽입
    @ResponseBody
    @RequestMapping(value = "/insert-review",method = RequestMethod.POST)
    public boolean reviewInsert(ReviewVO vo , HttpServletRequest req) {
        return service.reviewInsert(vo);
    }
    //웹 리뷰 삭제
    @ResponseBody
    @RequestMapping(value = "/delete-review",method = RequestMethod.DELETE)
    public boolean reviewDelete(int reviewid) {
        return service.reviewDelete(reviewid);
    }
    //웹 리뷰 수정
    @ResponseBody
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public boolean reviewModify(ReviewVO vo, HttpServletRequest req) {
        return service.reviewModify(vo);
    }


    @ResponseBody
    @RequestMapping("/profileimg")
    public List<ReviewVO> profileimg(ReviewVO vo , HttpServletRequest req) {

        return service.profileimg(vo);
    }



}
