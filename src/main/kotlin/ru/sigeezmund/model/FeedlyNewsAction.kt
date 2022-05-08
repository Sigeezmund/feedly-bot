package ru.sigeezmund.model

enum class Action(val actionName: String) {
    MARK_READ("markAsRead"),
    MARK_UNREAD("keepUnread");
}