package eric.linebot.model;

import java.util.List;

/**
 * AV 資訊模型
 */
public class AVModel {
	/**
	 * 番號
	 */
	private String id;
	/**
	 * 發行日期
	 */
	private String  date;
	/**
	 * 名稱
	 */
	private String name;
	
	/**
	 * 封面url
	 */
	private String coverUrl;
	
	/**
	 * 劇照url列表
	 */
	private List<String> filmStillList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public List<String> getFilmStillList() {
		return filmStillList;
	}

	public void setFilmStillList(List<String> filmStillList) {
		this.filmStillList = filmStillList;
	}
}
