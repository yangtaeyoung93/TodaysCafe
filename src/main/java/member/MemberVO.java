package member;

import java.io.Serializable;

public class MemberVO implements Serializable{
   private String email, userpwd, dbimgpath, fileName, admin, authkey, userid;
   private int authstatus;

   public MemberVO() {}

   

   public MemberVO(String email, String userpwd, String dbimgpath, String fileName, String userid) {
      super();
      this.email = email;
      this.userpwd = userpwd;
      this.dbimgpath = dbimgpath;
      this.fileName = fileName;
      this.userid = userid;
   }


   public String getUserid() {
	return userid;
}

   public void setUserid(String userid) {
	this.userid = userid;
}

   public int getAuthstatus() {
      return authstatus;
   }

   public void setAuthstatus(int authstatus) {
      this.authstatus = authstatus;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getUserpwd() {
      return userpwd;
   }

   public void setUserpwd(String userpwd) {
      this.userpwd = userpwd;
   }

   public String getDbimgpath() {
      return dbimgpath;
   }

   public void setDbimgpath(String dbimgpath) {
      this.dbimgpath = dbimgpath;
   }

   public String getFileName() {
      return fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public String getAdmin() {
      return admin;
   }

   public void setAdmin(String admin) {
      this.admin = admin;
   }

   public String getAuthkey() {
      return authkey;
   }

   public void setAuthkey(String authkey) {
      this.authkey = authkey;
   }

   
   
}