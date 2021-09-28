package cafeInfo;

public class ReviewVO extends CafeInfoVO{
	private String dbimgpath;
	private int score;
	private String rcomment;

	public ReviewVO(int id, String email) {
		super(id, email);
	}

	public String getDbimgpath() {
		return dbimgpath;
	}

	public void setDbimgpath(String dbimgpath) {
		this.dbimgpath = dbimgpath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRcomment() {
		return rcomment;
	}

	public void setRcomment(String rcomment) {
		this.rcomment = rcomment;
	}
}
