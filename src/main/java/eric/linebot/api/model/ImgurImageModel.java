package eric.linebot.api.model;

public class ImgurImageModel {
	/**
	 * 圖片id
	 */
	private String id;
	/**
	 * 圖片標題
	 */
	private String title;
	/**
	 * 圖片格式
	 */
	private String type;
	/**
	 * 圖片連結
	 */
	private String link;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
