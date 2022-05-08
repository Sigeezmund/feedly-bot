import ru.sigeezmund.output.telegram.handler.BotHandler
import ru.sigeezmund.output.telegram.handler.FeedlyBotHandlerImpl


private val botHandler: BotHandler = FeedlyBotHandlerImpl()

fun main() {
    botHandler.startBot()
    print("Bot Start!")
}
