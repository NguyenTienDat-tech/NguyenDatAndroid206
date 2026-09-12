package com.example.openningscreencompose.ui.navigation

import kotlinx.serialization.Serializable

class RoutesAndEvent {
    // Auth
    @Serializable
    data object WelcomeRouter
    @Serializable
    data object LoginRouter
    @Serializable
    data object RegisterRouter


    //User
    @Serializable
    data object BookRouter
    @Serializable
    data object ChatBotRouter
    @Serializable
    data object LocketRouter
    @Serializable
    data object ProfileRouter
}