package data;

public class FileDTO {
   private int fileid,id;
   private String filename,filepath;
   
   
   public FileDTO() {}


   public FileDTO(int id, String filename, String filepath) {
      super();
      this.id = id;
      this.filename = filename;
      this.filepath = filepath;
   }




   public FileDTO(int fileid, int id, String filename, String filepath) {
	super();
	this.fileid = fileid;
	this.id = id;
	this.filename = filename;
	this.filepath = filepath;
}


public int getFileid() {
	return fileid;
}


public void setFileid(int fileid) {
	this.fileid = fileid;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
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
   
   
   
}