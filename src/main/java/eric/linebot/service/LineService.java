package eric.linebot.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Event;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

import eric.linebot.manager.LineClientManger;

@RestController()
@RequestMapping("/line-service")
public class LineService {
	
	@Resource
	private LineClientManger lineClientManger;
	
	@RequestMapping("/callback")
	public void callback(@LineBotMessages List<Event> events) throws LineBotAPIException {
		lineClientManger.callback(events);
	}
	
}
