package com.today.cafe;


import java.io.File;
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

import common.CommonService;
import notice.NoticePage;
import notice.NoticeServiceImpl;
import notice.NoticeVO;


@Controller
public class NoticeController {
	@Autowired NoticeServiceImpl service;
	@Autowired private CommonService common;
	@Autowired NoticePage page;
	
	//�ȵ���̵�-���� ����
	@ResponseBody
	@RequestMapping("/nonono") 
	public Map<String, List<NoticeVO>> apapap(HttpServletRequest httpServletRequest) {
		List<NoticeVO> NoticeVO = service.nolist();

		Map<String, List<NoticeVO>> NoticeVal = new HashMap<String, List<NoticeVO>>();
		NoticeVal.put("contacts", NoticeVO); //�����͸� ������ �� key,value�� �����;� �ϱ� ������ Map���� ����� "contacts"->�ȵ���̵忡�� �迭�� ã���ִ� key
		
		return NoticeVal;
	}
	
	//�۸�� ȭ�� ��û
	@RequestMapping("/list.no")
	public String list(Model model,@RequestParam(defaultValue="1") int curPage,String search, String keyword) {
		
		page.setCurPage(curPage); 
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page",service.list(page));
		
	
		return "notice/list";
	}
	
	//���� �ۼ� ȭ�� ��û
		@RequestMapping("/new.no")
		public String notice() {
			return "notice/new";
		}
		
	//���� ���� ó�� ��û
		@RequestMapping("/insert.no")
		public String insert(NoticeVO vo,MultipartFile file,HttpSession session) {
			if(file.getSize()>0) {
				vo.setFilename(file.getOriginalFilename());
				vo.setFilepath(common.upload("notice",file, session));
			}
			service.insert(vo);
			return "redirect:list.no";
		}
		
	//�� ��ȭ�� ��û
		@RequestMapping("/detail.no")
		public String detail(Model model, int id,@RequestParam(defaultValue="0") int read ) {
			
			if(read==1) {
					service.read(id);
				}
			model.addAttribute("vo",service.detail(id));
			model.addAttribute("crlf","\r\n"); //����1
			model.addAttribute("lf","\n");	//����2
			model.addAttribute("page",page);
			return "notice/detail";
		}
		
	//�� ���� ȭ�� ��û
		@RequestMapping("/modify.no")
		public String modify(int id,Model model) {
			model.addAttribute("vo",service.detail(id));
			return "notice/modify";
		}
		
	//�� ���� ó�� ��û
		@RequestMapping("/update.no")
		public String update(NoticeVO vo,MultipartFile file,HttpSession session,String attach) {

			NoticeVO notice=service.detail(vo.getId());
			String uuid=notice.getFilepath();
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
					vo.setFilename(notice.getFilename());
					vo.setFilepath(notice.getFilepath());
				}
			}
			service.update(vo);
			return "redirect:detail.no?id="+vo.getId();
		}
		
	//�� ���� ó�� ��û
		@RequestMapping("/delete.no")
		public String delete(int id,HttpSession session) {
		File f= new File(
			session.getServletContext().getRealPath("resources"+service.detail(id).getFilepath()) );
		if (f.exists()) f.delete();
			service.delete(id);
			return "redirect:list.no";
		}
		
}
