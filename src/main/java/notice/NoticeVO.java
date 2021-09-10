package notice;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NoticeVO {
   private int id,readcnt,no;
   private String title, content, writer, name;
   //writer: 로그인한 사용자 :userid
   //name: 로그인한 회원명
   @JsonFormat(pattern="yyyy-MM-dd")
   private Date writedate;
   private String filename, filepath;
   private String nexttitle, pretitle;
   private int next_idx, pre_idx;
   
   
   public String getNexttitle() {
      return nexttitle;
   }
   public void setNexttitle(String nexttitle) {
      this.nexttitle = nexttitle;
   }
   public String getPretitle() {
      return pretitle;
   }
   public void setPretitle(String pretitle) {
      this.pretitle = pretitle;
   }
   public int getNext_idx() {
      return next_idx;
   }
   public void setNext_idx(int next_idx) {
      this.next_idx = next_idx;
   }
   public int getPre_idx() {
      return pre_idx;
   }
   public void setPre_idx(int pre_idx) {
      this.pre_idx = pre_idx;
   }
   public String getFilename() {
      return filename;
   }
   public void setFilename(String filename) {
      this.filename = filename;
   }
   public String getFilepath() {
      return filepath;
   }
   public void setFilepath(String filepath) {
      this.filepath = filepath;
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