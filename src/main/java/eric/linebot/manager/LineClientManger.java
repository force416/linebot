package eric.linebot.manager;

import java.util.List;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Event;

public interface LineClientManger {
	/**
	 * Line bot callback function
	 * @param events
	 * @return
	 * @throws LineBotAPIException
	 * @throws Exception 
	 */
	public boolean callback(List<Event> events) throws LineBotAPIException, Exception;
}
