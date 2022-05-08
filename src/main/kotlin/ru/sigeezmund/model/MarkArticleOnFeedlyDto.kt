package ru.sigeezmund.model.feedly

import ru.sigeezmund.model.Action.MARK_READ

data class MarkArticleOnFeedlyDto(
    val entryIds: List<String>,
    val action: String = MARK_READ.actionName,
    val type: String = "entries"
)
