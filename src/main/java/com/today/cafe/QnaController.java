package com.today.cafe;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import qna.QnaPage;
import qna.QnaServiceImpl;
import qna.QnaVO;

@Controller
public class QnaController {
	@Autowired private QnaServiceImpl service;
	@Autowired private CommonService common;
	@Autowired QnaPage page;
	
	//�۸�� ȭ�� ��û
	@RequestMapping("/list.qa")
	public String list(Model model,@RequestParam(defaultValue="1") int curPage,String search, String keyword) {
		page.setCurPage(curPage); 
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page",service.list(page));
		
		return "qna/list";
	}
	
	//���� �ۼ� ȭ�� ��û
	@RequestMapping("/new.qa")
	public String qna() {
		return "qna/new";
	}
	//���� ���� ó�� ��û
	@RequestMapping("/insert.qa")
	public String insert(QnaVO vo,MultipartFile file,HttpSession session) {
		//�ۼ���(userid) ��� ó�� �ؾ���
		System.out.println(vo.getWriter());
		//÷������
		if(file.getSize()>0) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("qna", file, session));
		}
		
		service.insert(vo);
		return "redirect:list.qa";
	}
	
	//�� ��ȭ�� ��û
	@RequestMapping("/detail.qa")
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
	
	
	//�� ���� ó�� ��û
	@RequestMapping("/delete.qa")
	public String delete(int id,HttpSession session) {
		File f= new File(session.getServletContext().getRealPath("resources"+service.detail(id).getFilepath()) );
			if (f.exists()) f.delete();
		service.delete(id);
		return "redirect:list.qa";
	}
	
	//�� ���� ȭ�� ��û
	@RequestMapping("/modify.qa")
	public String modify(int id,Model model) {
		model.addAttribute("vo",service.detail(id));
		
		return "qna/modify";
	}
	
	//�� ���� ó�� ��û
	@RequestMapping("/update.qa")
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
	
	//��� �ۼ�ȭ�� ��û
	@RequestMapping("/reply.qa")
	public String reply(int id,Model model) {
		model.addAttribute("vo",service.detail(id));
		return "qna/reply";
	}
	
	//��� ���� ó�� ��û
	@RequestMapping("/reply_insert.qa")
	public String reply_insert(QnaVO vo,MultipartFile file,HttpSession session) {
		if (file.getSize()>0) {
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("qna", file, session));
		}
		service.reply_insert(vo);
		return "redirect:list.qa";
	}
	
}
