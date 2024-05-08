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

    @FXML
    private lateinit var idadeProximoPaciente: Label

    @FXML
    private lateinit var nomeProximoPaciente: Label

    @FXML
    private lateinit var prioridadeDoPaciente: Label

    @FXML
    private lateinit var pacientesNaFila: Label


    private var fila = FilaPrioridade(10)
    private var qtdPacientesEnfileirados: Int = 0

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent


    @FXML
    private fun carregarTelaCadastro(event: ActionEvent) {
        println(fila)
        println(qtdPacientesEnfileirados)

        val loader = FXMLLoader(javaClass.getResource("cadastro-view.fxml"))
        root = loader.load()

        atualizarDadosTelaCadastro(loader)

        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    fun setDadosHome(nome: String, idade: String, prioridade: String, qtdPacientesEnfileirados: Int, fila: FilaPrioridade) {
        nomeProximoPaciente.text = nome
        idadeProximoPaciente.text = idade
        prioridadeDoPaciente.text = prioridade
        pacientesNaFila.text = qtdPacientesEnfileirados.toString()
        this.fila = fila
        this.qtdPacientesEnfileirados = qtdPacientesEnfileirados
    }

    private fun atualizarDadosTelaCadastro(loader: FXMLLoader) {
        val cadastroController: CadastroController = loader.getController()
        cadastroController.setDadosCadastro(fila, qtdPacientesEnfileirados)
    }
}