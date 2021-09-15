package com.today.cafe;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import qna.QnaPage;
import qna.QnaServiceImpl;
import qna.QnaVO;

@Controller
@RequestMapping(value = "/qna")
public class QnaController {
	@Autowired private QnaServiceImpl service;
	@Autowired private CommonService common;
	@Autowired QnaPage page;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model,@RequestParam(defaultValue="1") int curPage,String search, String keyword) {
		page.setCurPage(curPage); 
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page",service.list(page));
		
		return "qna/list";
	}
	
	@RequestMapping(value = "/new",method = RequestMethod.GET)
	public String qna() {
		return "qna/new";
	}

	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public String insert(QnaVO vo,MultipartFile file,HttpSession session) {
		if(file.getSize()>0) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("qna", file, session));
		}
		service.insert(vo);
		return "redirect:list.qa";
	}
	
	@RequestMapping(value = "/detail",method = RequestMethod.GET)
	public String detail(Model model, int id, @RequestParam(defaultValue="0") int read) {
		if(read==1) {
				service.read(id);
			}
		model.addAttribute("vo",service.detail(id));
		model.addAttribute("crlf","\r\n"); 
		model.addAttribute("lf","\n");	
		model.addAttribute("page",page); 
		
		return "qna/detail";
	}

	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	public String delete(int id,HttpSession session) {
		File f= new File(session.getServletContext().getRealPath("resources"+service.detail(id).getFilepath()) );
			if (f.exists()) f.delete();
		service.delete(id);
		return "redirect:list.qa";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public String modify(int id,Model model) {
		model.addAttribute("vo",service.detail(id));
		
		return "qna/modify";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(QnaVO vo, MultipartFile file,HttpSession session,String attach ) {
		QnaVO qna=service.detail(vo.getId());
		
		String uuid=qna.getFilepath();
		uuid=session.getServletContext().getRealPath("resources")+uuid;
		if (file.getSize()>0) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("notice", file, session));
			File f=new File(uuid); 
			if (f.exists()) f.delete(); 
		}else {
			if(attach.equals("y")) {
				File f=new File(uuid); 
				if (f.exists()) f.delete(); 
			}else {
				vo.setFilename(qna.getFilename());
				vo.setFilepath(qna.getFilepath());
			}
		}
		service.update(vo);
		return "redirect:detail.qa?id="+vo.getId();
	}
	
	@RequestMapping(value = "/reply",method = RequestMethod.GET)
	public String reply(int id,Model model) {
		model.addAttribute("vo",service.detail(id));
		return "qna/reply";
	}
	
	@RequestMapping(value = "/insert-reply",method = RequestMethod.POST)
	public String reply_insert(QnaVO vo,MultipartFile file,HttpSession session) {
		if (file.getSize()>0) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("qna", file, session));
		}
		service.reply_insert(vo);
		return "redirect:list.qa";
	}
	
}
