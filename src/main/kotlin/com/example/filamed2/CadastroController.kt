package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage

class CadastroController {
    @FXML
    lateinit var nomeField: TextField
    @FXML
    lateinit var cpfField: TextField
    @FXML
    lateinit var sexoField: TextField
    @FXML
    lateinit var dataNascimentoField: TextField
    @FXML
    lateinit var relatoQueixasSintomasField: TextField

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent


    fun selecionarTelaPrioridade(event: ActionEvent) {
        val nome: String = nomeField.text
        val idade: String = dataNascimentoField.text

        val loader = FXMLLoader(javaClass.getResource("prioridade-view.fxml"))
        root = loader.load()

        val prioridadeController: PrioridadeController = loader.getController()
        prioridadeController.setDados(nome, idade)

        stage = Stage()
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }
}