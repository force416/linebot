package eric.linebot.manager;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import eric.linebot.BaseTest;

public class AVParserManagerTest extends BaseTest {
	@Resource
	private AVParserManager aVParserManager;
	
	@Test
	public void testSearch() {
		try {
			aVParserManager.search("PGD-891");
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
