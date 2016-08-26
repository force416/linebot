package eric.linebot.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import eric.linebot.BaseTest;
import eric.linebot.dao.model.QueryLog;

public class QueryLogDaoTest extends BaseTest{
	
	@Resource
	private QueryLogDao queryLogDao;
	
	@Test
	public void testSave() {
		QueryLog ql = new QueryLog();
		ql.setName("施仲維");
		ql.setKeyword("三上悠亞");
		ql.setLineId("123321");
		ql.setCreateDate(new Date());
		ql = queryLogDao.save(ql);
		Assert.assertNotNull(ql);
	}
}
