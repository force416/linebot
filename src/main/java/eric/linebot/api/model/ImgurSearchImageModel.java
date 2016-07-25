package eric.linebot.api.model;

public class ImgurSearchImageModel {
	
	/**
	 * time | viral | top - defaults to time
	 */
	private String sort;
	/**
	 * Change the date range of the request if the sort is 'top', day | week | month | year | all, defaults to all.
	 */
	private String window;
	/**
	 * 查詢字句
	 */
	private String q;
	/**
	 * 查詢圖片type
	 * Show results for any file type, jpg | png | gif | anigif (animated gif) | album
	 */
	private String type;
	/**
	 * Size ranges, small (500 pixels square or less) | med (500 to 2,000 pixels square) | big (2,000 to 5,000 pixels square) | lrg (5,000 to 10,000 pixels square) | huge (10,000 square pixels and above)
	 */
	private String size;
	
	public ImgurSearchImageModel() {
		super();
	}
	
	public ImgurSearchImageModel(String q, String sort, String type, String size) {
		setQ(q);
		setSort(sort);
		setType(type);
		setSize(size);
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
