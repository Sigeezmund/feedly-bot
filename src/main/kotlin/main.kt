import telegram.handler.BotHandler
import telegram.handler.FeedlyBotHandlerImpl


private val botHandler: BotHandler = FeedlyBotHandlerImpl()

fun main() {
    botHandler.startBot()
    print("Bot Start!")
}
