package eric.linebot.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.client.exception.LineBotServerErrorStatusException;
import com.linecorp.bot.model.callback.Event;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

import eric.linebot.manager.LineClientManger;

@RestController()
@RequestMapping("/line-service")
public class LineService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private LineClientManger lineClientManger;
	
	/**
	 * Line收到訊息時會呼叫的callback function
	 * @param events
	 * @throws LineBotAPIException
	 */
	@RequestMapping("/callback")
	public void callback(@LineBotMessages List<Event> events) throws LineBotAPIException {
		try {
			lineClientManger.callback(events);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
