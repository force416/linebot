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

import eric.linebot.api.ImgurApiService;
import eric.linebot.api.model.ImgurImageModel;
import eric.linebot.api.model.ImgurSearchImageModel;

@Service("imgurApiService")
public class ImgurApiServiceImpl implements ImgurApiService {

	@Value("${imgur.api}")
	private String url;
	
	@Value("${imgur.clientId}")
	private String clientId;
	
	private Gson gson = new Gson();
	
	@Override
	public List<ImgurImageModel> gallerySearch(ImgurSearchImageModel q) throws Exception {
		String apiUrl = url + "/3/gallery/search/" + q.getSort() + "/1?" + "q_all=" + q.getQ() +"&q_type=" + q.getType() +"&q_size_px=" + q.getSize();
		String result = this.excuteHttpClient(apiUrl);
		JsonObject o = new JsonParser().parse(result).getAsJsonObject();
		return gson.fromJson(o.get("data"), new TypeToken<List<ImgurImageModel>>(){}.getType());
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
		httpget.setHeader("Authorization", "Client-ID " + clientId);
		CloseableHttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "utf-8");
	}
}
