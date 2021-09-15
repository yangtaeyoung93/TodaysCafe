package cafeInfo;

public class ReviewVO {
	private int id;
	private String storename;
	private String address;
	private String tel;
	private String close;
	private String userid;
	private String rcomment;
	private String writedate;
	private String score;
	private String dbimgpath;
	
	private int reviewid;
	
	
	public ReviewVO () {}




	public ReviewVO(int id, String storename, String address, String tel, String close, String userid, String rcomment,
			String writedate, String score, int reviewid,String dbimgpath) {
		super();
		this.id = id;
		this.storename = storename;
		this.address = address;
		this.tel = tel;
		this.close = close;
		this.userid = userid;
		this.rcomment = rcomment;
		this.writedate = writedate;
		this.score = score;
		this.reviewid = reviewid;
		this.dbimgpath = dbimgpath;
	}




	/*public ReviewVO(int id, String userid, String dbimgpath, int reviewid) {
		super();
		this.id = id;
		this.userid = userid;
		this.dbimgpath = dbimgpath;
		this.reviewid = reviewid;
	}
*/



	public String getDbimgpath() {
		return dbimgpath;
	}




	public void setDbimgpath(String dbimgpath) {
		this.dbimgpath = dbimgpath;
	}




	public String getStorename() {
		return storename;
	}




	public void setStorename(String storename) {
		this.storename = storename;
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


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getRcomment() {
		return rcomment;
	}


	public void setRcomment(String rcomment) {
		this.rcomment = rcomment;
	}


	public String getWritedate() {
		return writedate;
	}


	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public int getReviewid() {
		return reviewid;
	}


	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	
	
}
