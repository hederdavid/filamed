package com.example.filamed2

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("home-view.fxml"))
         val scene = Scene(fxmlLoader.load())
        //val iconPath = "../imgs/filamed-logo.png"
        //val iconStream = javaClass.classLoader.getResourceAsStream(iconPath)
        //val icon = Image(iconStream)
        //stage.icons.add(icon)
        stage.title = "FILAMED"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}