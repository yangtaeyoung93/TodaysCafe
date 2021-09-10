package data;

public class ImgVO {
   private int id;
   private String filepath;
   private String filename;
   private String fileid;
   
   
   public ImgVO() {}


   public ImgVO(int id, String filepath, String filename, String fileid) {
      super();
      this.id = id;
      this.filepath = filepath;
      this.filename = filename;
      this.fileid = fileid;
   }

   public int getId() {
      return id;
   }


   public void setId(int id) {
      this.id = id;
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


   public String getFileid() {
      return fileid;
   }


   public void setFileid(String fileid) {
      this.fileid = fileid;
   }
   
   
   
}