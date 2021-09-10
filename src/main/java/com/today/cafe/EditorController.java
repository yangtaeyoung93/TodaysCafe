package com.today.cafe;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.CommonService;
import editor.EditorServiceImpl;
import editor.EditorVO;
import editor.FileUploadVO;
import notice.NoticeVO;

@Controller
public class EditorController {
   @Autowired
   EditorServiceImpl service;

   
   // 글 목록 화면 요청
   @RequestMapping("/list.er")
   public String list(Model model, String keyword) {
      if (keyword != null) {
         model.addAttribute("vo", service.list(keyword));
         model.addAttribute("keyword", keyword);
      } else {
         model.addAttribute("vo", service.list());

      }
      return "editor/list";
   }

   // 새글 작성 화면 요청
   @RequestMapping("/new.er")
   public String editor() {
      return "editor/new";
   }

   // 새글 저장 처리 요청
   @RequestMapping("/insert.er")
   public String insert(EditorVO vo, FileUploadVO fvo, MultipartHttpServletRequest mtfRequest) {

      List<MultipartFile> fileList = mtfRequest.getFiles("file");

      String path = "D:/Study_Spring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/cafe/resources/upload/editor/";
      service.insert(vo);

      for (MultipartFile mf : fileList) {
         String originFileName = mf.getOriginalFilename();
         String safeFile = path + System.currentTimeMillis() + originFileName;
         String filepath = File.separator + "upload" + File.separator + "editor" + File.separator
               + System.currentTimeMillis() + originFileName;

         try {
            mf.transferTo(new File(safeFile));
         } catch (Exception e) {
            e.printStackTrace();
         }

         if (!originFileName.equals("")) {
            fvo.setFilename(mf.getOriginalFilename());
            fvo.setFilepath(filepath);
            fvo.setTitle(vo.getTitle());

            service.insert_f(fvo);
         } else {
            fvo.setFilename(mf.getOriginalFilename());
            fvo.setTitle(vo.getTitle());
            safeFile = "0";
            fvo.setFilepath(filepath);
            service.insert_f(fvo);
         }
      }
      return "redirect:list.er";
   }

   // 글 상세화면 요청
   @RequestMapping("/detail.er")
   public String detail(Model model, String title, @RequestParam(defaultValue = "0") int read) {
      EditorVO list = service.detail(title);
      List<EditorVO> list_f = service.detail_f(title);
      List<EditorVO> editor_f = service.detail_f(list.getTitle());
      if (read == 1) {
         service.read(title);
      }

      for (int i = 0; i < list_f.size(); i++) {
      }
      list.setFile_cnt(list_f.size());
      
      model.addAttribute("vo", list);
      model.addAttribute("vo_f", list_f);
      model.addAttribute("crlf", "\r\n");
      model.addAttribute("lf", "\n");
      return "editor/detail";
   }

   // 글 삭제 요청 처리
   @RequestMapping("/delete.er")
   public String delete(String title) {
      service.delete(title);
      service.delete_f(title);
      return "redirect:list.er";
   }

   // 글 수정 화면 요청
   @RequestMapping("/modify.er")
   public String modify(String title, Model model) {
      EditorVO list = service.detail(title);
      List<EditorVO> list_f = service.detail_f(title);
      list.setFile_cnt(list_f.size());
      model.addAttribute("vo", list);
      model.addAttribute("vo_f", list_f);

      return "editor/modify";
   }

   // 글 수정 처리 요청
      @RequestMapping("/update.er")
      public String update(EditorVO vo, FileUploadVO fvo, MultipartHttpServletRequest mtfRequest) {
         EditorVO editor = service.detail(vo.getTitle());
         List<EditorVO> editor_f = service.detail_f(vo.getTitle());
         String title = vo.getTitle();
         /* String title = vo.getTitle().substring(0, vo.getTitle().indexOf(",")); */

         if (editor_f.size() != 0) { 
            for (int i = 0; i < editor_f.size(); i++) { 
               String path = "D:/Study_Spring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/cafe/resources/upload/editor/";
               String uuid = editor_f.get(i).getFilepath();
               String fname = editor_f.get(i).getFilename();
               String safefile = path + uuid;

               File f = new File(safefile);

               if (f.getName() != null) {
                  service.delete_f(title);
                  
                  
                  List<MultipartFile> fileList = mtfRequest.getFiles("file");

                  for (MultipartFile mf : fileList) {
                     String originFileName = mf.getOriginalFilename();
                     String safeFile = path +  originFileName;
                     String filepath = File.separator + "upload" + File.separator + "editor" + File.separator
                           +  originFileName;
                     try {
                        mf.transferTo(new File(safeFile));
                     } catch (Exception e) {
                        e.printStackTrace();
                     }

                     if (!originFileName.equals("")) {
                        fvo.setFilename(mf.getOriginalFilename());
                        fvo.setFilepath(filepath);
                        fvo.setTitle(vo.getTitle());

                        service.insert_f(fvo);
                     } else {
                        fvo.setFilename(mf.getOriginalFilename());
                        fvo.setTitle(vo.getTitle());
                        safeFile = "0";
                        fvo.setFilepath(filepath);
                        service.insert_f(fvo);
                     }
                  }

               }

            }
         }
         try {
            title = URLEncoder.encode(title, "UTF-8");
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }

         service.update(vo);
         return "redirect:detail.er?title=" + title;
      }
}