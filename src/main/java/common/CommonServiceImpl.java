package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import member.MemberVO;

@Service
public class CommonServiceImpl implements CommonService {
	
	
	   @Override
	   public String profileFile(MultipartFile file, MemberVO vo, HttpSession session) {
	      String resources = session.getServletContext().getRealPath("resources");
	      String profile1 = resources + "/" + "profile";
	      String profile = "/" + "profile";
	      String fileName = vo.getEmail().toString()+"_"+file.getOriginalFilename();
	      try {
	         file.transferTo(new File(profile1, fileName));
	      } catch (IllegalStateException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      return profile+"/"+fileName;
	   }

	@Override
	public String upload(String category, MultipartFile file, HttpSession session) {

		// 업로드할 파일을 어디에 업로드 할 것인지(물리적위치)를 지정한다.
		// upload/notice/2019/07/02/abc.txt
		// upload/board/2019/07/02/abc.txt

		// Study_Spring/..../iot/resources
		String resources = session.getServletContext().getRealPath("resources");

		// Study_Spring/..../iot/resources/upload
		String upload = resources + "/" + "upload";

		// Study_Spring/..../iot/resources/upload/notice/2019/07/03
		String folder = makeFolder(category, upload);

		// 동시다발적 사용자의 업로드로 인한 중복을 막기 위한
		// 고유아이디를 파일명에 부여한다.
		// UUID(범용고유식별자:Universally Unique ID) adffd435aafdf_abc.txt
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		// 파일을 저장하는 처리: 파일을 옮겨주는 형태로
		try {
			file.transferTo(new File(folder, uuid));
		} catch (Exception e) {
		}

		return folder.substring(resources.length()) + "/" + uuid;
	}

	private String makeFolder(String category, String upload) {
		// upload/board/2019/07/02/abc.txt
		StringBuilder sb = new StringBuilder(upload); // .../upload
		sb.append("/" + category); // .../upload/notice

		Date now = new Date();
		sb.append("/" + new SimpleDateFormat("yyyy").format(now));
		// .../upload/notice/2019
		sb.append("/" + new SimpleDateFormat("MM").format(now));
		// .../upload/notice/2019/07
		sb.append("/" + new SimpleDateFormat("dd").format(now));
		// .../upload/notice/2019/07/03
		String folder = sb.toString();
		// 해당 폴더가 없으면 폴더를 생성
		File f = new File(folder);
		if (!f.exists())
			f.mkdirs();

		return folder;
	}

	@Override
	public File download(String dbimgpath, String filename, HttpSession session, HttpServletResponse response) {
		// 다운로드할 파일객체 생성
		File file = new File(session.getServletContext().getRealPath("resources") + dbimgpath);
		// 다운로드할 파일의 마임타입결정
		String mime = session.getServletContext().getMimeType(filename);
		if (mime == null)
			mime = "application/octet-stream";

		response.setContentType(mime);

		try {

			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");

			response.setHeader("content-disposition", "attachment; filename=" + filename);

			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
		}

		return file;
	}

	
	
	
	
	

	

	

	
	
	

}
