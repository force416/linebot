package eric.linebot.api.model;

public class BingImageSearchModel {
	/**
	 * 查詢關鍵字
	 */
	private String q;
	
	/**
	 * 查詢筆數
	 */
	private int count;
	
	public BingImageSearchModel() {
		super();
	}
	
	public BingImageSearchModel(String q, int count) {
		this.setQ(q);
		this.setCount(count);
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
