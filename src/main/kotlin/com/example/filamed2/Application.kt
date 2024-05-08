package com.example.filamed2

import javafx.application.Application
import javafx.animation.Timeline
import javafx.animation.KeyFrame
import javafx.util.Duration
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("telainicial-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "FILAMED"
        stage.scene = scene
        stage.show()
        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(1.0),
                javafx.event.EventHandler {
                    carregarTelaHome(stage)
                }
            )
        )
        timeline.play()
    }

    private fun carregarTelaHome(stage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("home-view.fxml"))
        val root = loader.load<Parent>()
        val scene = Scene(root)
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}