package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class CadastroController {
    fun carregarTelaPrioridade(event: ActionEvent) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("prioridade-view.fxml"))
        val scene = Scene(root)

        val stage = Stage()
        stage.title = "Prioridade"
        stage.scene = scene
        stage.show()
    }
}