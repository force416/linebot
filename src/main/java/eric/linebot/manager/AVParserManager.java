package eric.linebot.manager;

import java.util.List;

import eric.linebot.model.AVModel;

public interface AVParserManager {
	/**
	 * 搜尋av
	 * @param keyword 關鍵字 (女優, 番號, 名稱)
	 * @return av模型列表
	 * @throws Exception
	 */
	public List<AVModel> search(String keyword) throws Exception;
}
