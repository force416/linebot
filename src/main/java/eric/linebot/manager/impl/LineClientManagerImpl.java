package eric.linebot.manager.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.model.callback.Event;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.model.content.Content;
import com.linecorp.bot.model.content.TextContent;
import com.linecorp.bot.model.profile.UserProfileResponse;

import eric.linebot.api.BingSearchApiService;
import eric.linebot.api.ImgurApiService;
import eric.linebot.api.model.BingImageModel;
import eric.linebot.api.model.BingImageSearchModel;
import eric.linebot.api.model.ImgurImageModel;
import eric.linebot.api.model.ImgurSearchImageModel;
import eric.linebot.manager.AVParserManager;
import eric.linebot.manager.LineClientManger;
import eric.linebot.model.AVModel;

@Service("lineClientManager")
public class LineClientManagerImpl implements LineClientManger {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private LineBotClient lineBotClient;

	@Resource
	private ImgurApiService imgurApiService;

	@Resource
	private BingSearchApiService bingSearchApiService;

	@Resource
	private AVParserManager aVParserManager;

	private Gson gson = new Gson();

	@Override
	public boolean callback(List<Event> events) throws Exception {
		for (Event event : events) {
			Content content = event.getContent();
			String userMid = null;
			// 文字訊息
			if (content instanceof TextContent) {
				TextContent text = (TextContent) content;
				userMid = text.getFrom();
				// av 搜尋
				if (this.isAVsearch(text.getText())) {
					this.queryAVAndSendMessage(text.getText().split(":")[1], userMid);
				} else {// 一般搜尋
					// 關鍵字查詢imgur圖片
					// this.queryImgurImageAndSendMessage(text.getText(),
					// userMid);
					// 關鍵字查詢bing圖片
					this.queryBingImageAndSendMessage(text.getText(), userMid);
				}
			} else {
				// 其它種類訊息
				AbstractContent ct = (AbstractContent) content;
				this.sendDefaultMessage(ct.getFrom());
				userMid = ct.getFrom();
			}

			// 取得傳訊息user資訊
			List<String> userProfileList = new ArrayList<String>();
			userProfileList.add(userMid);
			UserProfileResponse rp = lineBotClient
					.getUserProfile(userProfileList);
			logger.info(gson.toJson(rp.getContacts().get(0)));
		}

		return false;
	}

	private void sendDefaultMessage(String mid) throws Exception {
		lineBotClient.sendText(mid, "sorry~卡丘看不懂唷~啾咪^.<");
	}

	/**
	 * 發送imgur圖片查詢結果
	 * 
	 * @param keyword
	 *            查詢圖片關鍵字
	 * @param mid
	 *            發送對象mid
	 * @throws Exception
	 */
	private void queryImgurImageAndSendMessage(String keyword, String mid)
			throws Exception {
		// 關鍵字查詢imgur圖片
		List<ImgurImageModel> imgurImageModelList = imgurApiService
				.gallerySearch(new ImgurSearchImageModel(URLEncoder.encode(
						keyword, "UTF-8"), "top", "", ""));
		if (imgurImageModelList == null || imgurImageModelList.isEmpty()) {
			this.sendDefaultMessage(mid);
		}
		int cnt = 0;
		for (ImgurImageModel model : imgurImageModelList) {
			if (cnt == 10) {
				break;
			}
			// 圖片的link才發送訊息
			if (model.getLink().indexOf(".jpg") != -1
					|| model.getLink().indexOf(".png") != -1) {
				cnt++;
				lineBotClient.sendImage(mid, model.getLink(), model.getLink());
			}
		}
	}

	/**
	 * 發送Bing圖片查詢結果
	 * 
	 * @param keyword
	 *            查詢圖片關鍵字
	 * @param mid
	 *            發送對象mid
	 * @throws Exception
	 */
	private void queryBingImageAndSendMessage(String keyword, String mid)
			throws Exception {
		// 關鍵字查詢圖片
		List<BingImageModel> bingImageModelList = bingSearchApiService
				.imageSearch(new BingImageSearchModel(URLEncoder.encode(
						keyword, "UTF-8"), 10));
		if (bingImageModelList == null || bingImageModelList.isEmpty()) {
			this.sendDefaultMessage(mid);
		}
		for (BingImageModel model : bingImageModelList) {
			lineBotClient.sendImage(mid, model.getContentUrl(),
					model.getContentUrl());
		}
	}

	/**
	 * 查詢av並發送訊息
	 * 
	 * @param keyword
	 * @throws Exception
	 */
	private void queryAVAndSendMessage(String keyword, String mid) throws Exception {
		List<AVModel> modelList = aVParserManager.search(keyword);
		if (modelList != null && !modelList.isEmpty()) {
			//只有一筆資料
			if (modelList.size() == 1) {
				AVModel model = modelList.get(0);
				lineBotClient.sendText(mid,
								"番號 : " + model.getId() + "\n" + 
								"片名 : " + model.getName() + "\n" + 
								"發行日期 : "+ model.getDate());
				lineBotClient.sendImage(mid, model.getCoverUrl(), model.getCoverUrl());
				for (String filmStill : model.getFilmStillList()) {
					lineBotClient.sendImage(mid, filmStill, filmStill);
				}
			} else {
				//多筆資料
				for (AVModel model : modelList) {
					lineBotClient.sendText(mid,
									"番號 : " + model.getId() + "\n" + 
									"片名 : " + model.getName() + "\n" + 
									"發行日期 : "+ model.getDate());
					lineBotClient.sendImage(mid, model.getCoverUrl(), model.getCoverUrl());
				}
			}
		} else {
			this.sendDefaultMessage(mid);
		}
	}

	/**
	 * 判斷是否是搜尋AV相關 av:波多野結衣
	 * 
	 * @param keyword
	 * @return
	 */
	private boolean isAVsearch(String keyword) {
		return StringUtils.startsWithIgnoreCase(keyword, "av:") && keyword.length() > 3;
	}
}
