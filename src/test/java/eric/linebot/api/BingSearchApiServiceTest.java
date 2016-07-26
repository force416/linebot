package eric.linebot.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import eric.linebot.SpringBootConfiguration;
import eric.linebot.api.model.BingImageModel;
import eric.linebot.api.model.BingImageSearchModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootConfiguration.class)
@WebAppConfiguration
public class BingSearchApiServiceTest {
	@Resource
	private BingSearchApiService bingSearchApiService;
	
	@Test
	public void testImageSearch() throws Exception {
		List<BingImageModel> result = new ArrayList<BingImageModel>();
		result = bingSearchApiService.imageSearch(new BingImageSearchModel("japan", 10));
		Assert.assertTrue(result != null && !result.isEmpty());
	}
}
