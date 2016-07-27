package eric.linebot.manager.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import eric.linebot.manager.AVParserManager;
import eric.linebot.model.AVModel;

/**
 * parser https://avmo.pw/tw 相關資料
 */
@Service("aVParserManager")
public class AVParserManagerImpl implements AVParserManager {

	@Override
	public List<AVModel> search(String keyword) throws Exception {
        String url = "https://avmo.pw/tw/search/" + URLEncoder.encode(keyword, "utf-8");

        Document doc = Jsoup.connect(url).get();
        Elements ws = doc.select("a.movie-box");
        
        List<AVModel> resultList = new ArrayList<AVModel>();
        
        String detailLink = null;
        int cnt = 0;
        for (Element src : ws) {
        	if (cnt == 10) { break; } //最多十筆
        	Elements each = src.select("[src]");
        	String id = src.select("date").get(0).text();
        	String date = src.select("date").get(1).text();
        	String coverUrl = each.attr("src");
        	String name = each.attr("title");
        	detailLink = src.attr("href");
        	AVModel model = new AVModel();
        	model.setId(id);				//番號
        	model.setDate(date);			//發行日期
        	model.setCoverUrl(coverUrl);	//封面url
        	model.setName(name);			//名稱
        	resultList.add(model);
        	cnt++;
        }
        
        //如果只有一筆資料，把劇照的資料塞進model
        if (!StringUtils.isEmpty(detailLink) && !resultList.isEmpty() && resultList.size() == 1) {
        	resultList.get(0).setFilmStillList(this.getFilmStills(detailLink));
        }
        
        return resultList;
    }
	
	/**
	 * parser 劇照
	 * @param avDetailLink av詳細頁link
	 * @return 劇照url列表
	 * @throws Exception 
	 */
	private List<String> getFilmStills(String avDetailLink) throws Exception {
		Document doc = Jsoup.connect(avDetailLink).get();
		List<String> resultList = new ArrayList<String>();
		Elements elements = doc.select("a.sample-box");
		for (Element src : elements) {
			resultList.add(src.attr("href"));
		}
		return resultList;
	}
	
}
