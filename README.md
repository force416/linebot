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

將程式佈署完成後將`callback url`註冊回line bot api 的官網 (https://developers.line.me)