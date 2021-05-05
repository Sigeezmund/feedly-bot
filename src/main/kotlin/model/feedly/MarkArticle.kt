package model.feedly

data class MarkArticle(
    val entryIds: List<String>,
    val action: String = Action.MARK_READ.actionName,
    val type: String = "entries"
)

enum class Action(val actionName: String) {
    MARK_READ("markAsRead"),
    MARK_UNREAD("keepUnread");
}
