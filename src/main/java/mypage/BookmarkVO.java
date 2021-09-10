package mypage;

public class BookmarkVO {
	private String email,storename,address,filepath,tel;
	private int id,no;

	
	public BookmarkVO() {}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public BookmarkVO(String email, String storename, String address, String filepath, String tel, int id,
			int no) {
		super();
		this.email = email;
		this.storename = storename;
		this.address = address;
		this.filepath = filepath;
		this.tel = tel;
		this.id = id;
		this.no = no;
	}
	
	
	
	
	
	
	
	
	
	
}
