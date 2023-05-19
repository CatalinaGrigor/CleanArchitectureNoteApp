package com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
