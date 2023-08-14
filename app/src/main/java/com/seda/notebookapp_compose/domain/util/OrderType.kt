package com.seda.notebookapp_compose.domain.util

sealed class OrderType {
    object Ascending:OrderType()

    object Descending:OrderType()

}