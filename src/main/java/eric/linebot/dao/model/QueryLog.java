package eric.linebot.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "query_log")
public class QueryLog {
	
	@Id
	private long serialNo;
	
	private String lineId;
	
	private String name;
	
	private String keyword;
	
	private Date createDate;

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
