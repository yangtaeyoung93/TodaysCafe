package data;

public class JsonDTO {
   private String storename, address, tel, lat,lng ,close, smoke, parking, pet, seat;
   private int id;

   
   public JsonDTO() {}
   public JsonDTO(int id, String storename, String address, String tel, String lat, String lng, String close,String filepath, String filename) {
      super();
      this.storename = storename;
      this.address = address;
      this.tel = tel;
      this.lat = lat;
      this.lng = lng;
      this.close = close;
      this.id = id;
   }
   

   public JsonDTO(String storename, String address, String tel, String lat, String lng, String close, String filename,
         String filepath, String smoke, String parking, String pet, String seat, int id) {
      super();
      this.storename = storename;
      this.address = address;
      this.tel = tel;
      this.lat = lat;
      this.lng = lng;
      this.close = close;
      this.smoke = smoke;
      this.parking = parking;
      this.pet = pet;
      this.seat = seat;
      this.id = id;
   }
   public String getSmoke() {
      return smoke;
   }
   public void setSmoke(String smoke) {
      this.smoke = smoke;
   }
   public String getParking() {
      return parking;
   }
   public void setParking(String parking) {
      this.parking = parking;
   }
   public String getPet() {
      return pet;
   }
   public void setPet(String pet) {
      this.pet = pet;
   }
   public String getSeat() {
      return seat;
   }
   public void setSeat(String seat) {
      this.seat = seat;
   }

   public String getAddress() {
      return address;
   }


   public void setAddress(String address) {
      this.address = address;
   }


   public String getTel() {
      return tel;
   }


   public void setTel(String tel) {
      this.tel = tel;
   }


   public String getClose() {
      return close;
   }


   public void setClose(String close) {
      this.close = close;
   }


   public int getId() {
      return id;
   }


   public void setId(int id) {
      this.id = id;
   }

   public String getStorename() {
      return storename;
   }
   public void setStorename(String storename) {
      this.storename = storename;
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