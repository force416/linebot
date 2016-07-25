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
import eric.linebot.api.model.ImgurImageModel;
import eric.linebot.api.model.ImgurSearchImageModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootConfiguration.class)
@WebAppConfiguration
public class ImgurApiServiceTest {
	
	@Resource
	private ImgurApiService imgurApiService;
	
	@Test
	public void testGallerySearch() {
		ImgurSearchImageModel q = new ImgurSearchImageModel();
		List<ImgurImageModel> result = new ArrayList<ImgurImageModel>();
		try {
			q.setQ("dog");
			q.setSize("");
			q.setSort("top");
			q.setType("");
			result = imgurApiService.gallerySearch(q);
			Assert.assertTrue(result != null && !result.isEmpty());
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
