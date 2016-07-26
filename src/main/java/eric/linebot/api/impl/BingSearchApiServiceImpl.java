package eric.linebot.api.impl;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import eric.linebot.api.BingSearchApiService;
import eric.linebot.api.model.BingImageModel;
import eric.linebot.api.model.BingImageSearchModel;

@Service("bingSearchApiService")
public class BingSearchApiServiceImpl implements BingSearchApiService {

	@Value("${imgur.api}")
	private String url;
	
	@Value("${bing.sub.key}")
	private String subscriptionKey;
	
	private Gson gson = new Gson();
	
	@Override
	public List<BingImageModel> imageSearch(BingImageSearchModel q) throws Exception {
		String apiUrl = url + "?q=" + q.getQ() + "&count=" + q.getCount();
		String result = this.excuteHttpClient(apiUrl);
		JsonObject o = new JsonParser().parse(result).getAsJsonObject();
		return gson.fromJson(o.get("value"), new TypeToken<List<BingImageModel>>(){}.getType());
	}
	
	/**
	 * 執行httpclient
	 * @param apiUrl
	 * @return
	 * @throws Exception
	 */
	private String excuteHttpClient(String apiUrl) throws Exception {
		CloseableHttpClient httpClient = HttpClients.custom().build();
		HttpGet httpget = new HttpGet(apiUrl);
		httpget.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
		CloseableHttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "utf-8");
	}
}
