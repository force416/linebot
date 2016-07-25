package eric.linebot.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Event;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.model.content.Content;
import com.linecorp.bot.model.content.TextContent;
import com.linecorp.bot.model.profile.UserProfileResponse;

import eric.linebot.api.ImgurApiService;
import eric.linebot.api.model.ImgurImageModel;
import eric.linebot.api.model.ImgurSearchImageModel;
import eric.linebot.manager.LineClientManger;

@Service("lineClientManager")
public class LineClientManagerImpl implements LineClientManger {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private LineBotClient lineBotClient;
	
	@Resource
	private ImgurApiService imgurApiService;
	
	private Gson gson = new Gson();

	@Override
	public boolean callback(List<Event> events) throws Exception {
		for (Event event : events) {
			Content content = event.getContent();
			String userMid = null;
			//文字訊息
			if (content instanceof TextContent) {
				TextContent text = (TextContent) content;
				//關鍵字查詢imgur圖片
				List<ImgurImageModel> imgurImageModelList = imgurApiService.gallerySearch(new ImgurSearchImageModel(text.getText(), "top", "jpg", "small"));
				if (imgurImageModelList == null || imgurImageModelList.isEmpty()) {
					this.sendDefaultMessage(text.getFrom());
				}
				for (ImgurImageModel model : imgurImageModelList) {
					lineBotClient.sendImage(text.getFrom(), model.getLink(), model.getLink());
				}
				userMid = text.getFrom();
			} else {
			//其它種類訊息
				AbstractContent ct = (AbstractContent) content;
				this.sendDefaultMessage(ct.getFrom());
				userMid = ct.getFrom();
			}
			
			//取得傳訊息user資訊
			List<String> userProfileList = new ArrayList<String>();
			userProfileList.add(userMid);
			UserProfileResponse rp = lineBotClient.getUserProfile(userProfileList);
			logger.info(gson.toJson(rp.getContacts().get(0)));
		}
		
		return false;
	}
	
	private void sendDefaultMessage(String mid) throws Exception {
		lineBotClient.sendText(mid, "sorry~卡丘看不懂唷~啾咪^.<");
	}
}
