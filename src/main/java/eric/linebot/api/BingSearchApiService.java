package eric.linebot.api;

import java.util.List;

import eric.linebot.api.model.BingImageModel;
import eric.linebot.api.model.BingImageSearchModel;

public interface BingSearchApiService {
	
	/**
	 * 圖片查詢
	 * @param q 查詢model
	 * @return
	 * @throws Exception
	 */
	public List<BingImageModel> imageSearch(BingImageSearchModel q) throws Exception;
}
