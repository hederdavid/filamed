package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class PrioridadeController {

    private lateinit var nome: String
    private lateinit var idade: String
    private lateinit var pacientes: String

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent

    fun selecionarTelaHome(event: ActionEvent) {
        val prioridade: String = "Urgente"

        val loader = FXMLLoader(javaClass.getResource("home-view.fxml"))
        root = loader.load()

        val homecontroller: HomeController = loader.getController()
        homecontroller.inserirDados(nome, idade, prioridade)


        //root = FXMLLoader.load(javaClass.getResource("home-view.fxml"))
        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    fun setDados(nome: String, idade: String) {
        this.nome = nome
        this.idade = idade
    }





}