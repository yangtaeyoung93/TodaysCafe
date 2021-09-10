package editor;

import java.sql.Date;

public class EditorVO {
   private int id,readcnt,no,file_cnt;
   private String title, content, writer, name,filepath,filename;
   //writer: 로그인한 사용자 :userid
   //name: 로그인한 회원명
   private Date writedate;
      





public int getFile_cnt() {
      return file_cnt;
   }
   public void setFile_cnt(int file_cnt) {
      this.file_cnt = file_cnt;
   }

   public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public int getReadcnt() {
      return readcnt;
   }
   public void setReadcnt(int readcnt) {
      this.readcnt = readcnt;
   }
   public int getNo() {
      return no;
   }
   public void setNo(int no) {
      this.no = no;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Date getWritedate() {
      return writedate;
   }
   public void setWritedate(Date writedate) {
      this.writedate = writedate;
   }
}