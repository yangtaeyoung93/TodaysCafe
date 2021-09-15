package cafeInfo;

public class BookmarkVO {
	private int id;
	private String email;
	private String dbimgpath;
	private String storename;
	private String address;
	private String tel;
	private int count;
	private boolean bookmark;

	public BookmarkVO(String email,int id) {
		super();
		this.email = email;
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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

	public boolean isBookmark() {
		return bookmark;
	}

	public void setBookmark(boolean bookmark) {
		this.bookmark = bookmark;
	}


}
