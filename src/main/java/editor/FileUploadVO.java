package editor;

public class FileUploadVO {
   
   private int filenum;
   private String title,filename,filepath;
   
   
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public int getFilenum() {
      return filenum;
   }
   public void setFilenum(int filenum) {
      this.filenum = filenum;
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