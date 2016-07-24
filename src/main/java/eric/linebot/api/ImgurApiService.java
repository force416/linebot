package eric.linebot.api;

import java.util.List;

import eric.linebot.api.model.ImgurImageModel;
import eric.linebot.api.model.ImgurSearchImageModel;

public interface ImgurApiService {
	/**
	 * 查詢imgur圖片
	 * @param q 查詢條件model
	 * @return 圖片列表
	 */
	public List<ImgurImageModel> gallerySearch(ImgurSearchImageModel q) throws Exception;
}
