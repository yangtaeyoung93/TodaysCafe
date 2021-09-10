package common;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import member.MemberVO;



public interface CommonService {
	
	
	String upload(String category, 
				MultipartFile file, HttpSession session);
	File download(String dbimgpath, String filename, 
				HttpSession session, HttpServletResponse response);
	
	String profileFile(MultipartFile file, MemberVO vo, HttpSession session);
}
