package data;

import org.json.simple.JSONObject;

public class DataDTO {
   private String name, vicinity,lat,lng;
   
   
   public DataDTO() {}
   public DataDTO(String lat, String lng, String name, String vicinity) {
      super();
   
      this.lat = lat;
      this.lng = lng;
      this.name = name;
      this.vicinity = vicinity;
   }




   public String getName() {
      return name;
   }



   public void setName(String name) {
      this.name = name;
   }



   public String getVicinity() {
      return vicinity;
   }



   public void setVicinity(String vicinity) {
      this.vicinity = vicinity;
   }



   public String getLat() {
      return lat;
   }



   public void setLat(String lat) {
      this.lat = lat;
   }



   public String getLng() {
      return lng;
   }



   public void setLng(String lng) {
      this.lng = lng;
   }


   
}