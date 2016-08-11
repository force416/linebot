# linebot

line聊天機器人

使用line-bot-sdk-java (https://github.com/line/line-bot-sdk-java)

將四個module包成jar引用
- line-bot-api-client: API client library for LINE Bot API
- line-bot-model: Model classes for LINE Bot API
- line-bot-servlet: Java servlet utilities for Bot servers
- line-bot-spring-boot: spring-boot's auto configuration library for Bot servers

註冊line bot api (https://developers.line.me/bot-api/overview)取得
- Channel ID
- Channel Secret
- Channel MID

包成 srping boot jar 後，執行時需代入系統參數
``` bash
$ java -Dline.bot.channelId=$CHANNELID -Dline.bot.channelMid=$CHANNELMID -Dline.bot.channelSecret=$CHANNELSECRET -jar linebot.jar
```

將程式佈署完成後將`callback url`註冊回line bot api 的官網 (https://developers.line.me)

### Line 機器人的QR Code
(https://qr-official.line.me/sid/L/nhs4801n.png)

### 使用方式
* 輸入關鍵字查詢bing search api 圖片
* 輸入av:${女優名} 可查相關女優的av
* 輸入av:${番號} 可查該部番號的相關劇照
