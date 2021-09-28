package cafeInfo;

public class BookmarkVO extends CafeInfoVO{

	private boolean bookmark;

	public BookmarkVO(int id, String email) {
		super(id, email);
	}

	public boolean isBookmark() {
		return bookmark;
	}
}
