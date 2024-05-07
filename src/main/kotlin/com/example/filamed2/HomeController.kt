package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage


class HomeController {
    private var fila: HeapingPaciente = HeapMaximoPaciente(10)
    private var pacientes: Int = 0

    @FXML
    private lateinit var prioridadeDoPaciente: Label

    @FXML
    private lateinit var idadeProximoPaciente: Label

    @FXML
    private lateinit var nomeProximoPaciente: Label

    @FXML
    private lateinit var pacientesNaFila: Label

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent


    @FXML
    private fun inserir(event: ActionEvent) {
        root = FXMLLoader.load(javaClass.getResource("cadastro-view.fxml"))
        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    fun inserirDados(nome: String, idade: String, prioridade: String) {
        nomeProximoPaciente.text = nome
        idadeProximoPaciente.text = idade
        prioridadeDoPaciente.text = prioridade
    }
}