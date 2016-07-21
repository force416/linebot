package eric.linebot.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Event;
import com.linecorp.bot.model.content.Content;
import com.linecorp.bot.model.content.TextContent;

import eric.linebot.manager.LineClientManger;

@Service("lineClientManager")
public class LineClientManagerImpl implements LineClientManger {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private LineBotClient lineBotClient;
	
	@Override
	public boolean callback(List<Event> events) throws LineBotAPIException {
		for (Event event : events) {
			Content content = event.getContent();
			if (content instanceof TextContent) {
				TextContent text = (TextContent) content;
				lineBotClient.sendText(text.getFrom(), text.getText());
			} else {
				
				lineBotClient.sendText(System.getProperty("line.bot.channelMid"), "pi~ka~chu~~~");
				lineBotClient.sendImage(System.getProperty("line.bot.channelMid"), "http://i.imgur.com/n5IwKuu.png", "http://i.imgur.com/n5IwKuu.png");
			}
		}
		return false;
	}
	
}
