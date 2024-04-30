package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage


class HomeController {
    private var fila: HeapingPaciente = HeapMaximoPaciente(10)
    private var pacientes: Int = 0

    @FXML
    private lateinit var welcomeText: Label
    @FXML
    private lateinit var pacientesNaFila: Label
    @FXML
    private lateinit var nomeProximoPaciente: Label
    @FXML
    private lateinit var idadeProximoPaciente: Label
    @FXML
    private lateinit var prioridadePaciente: Label

    enum class Prioridade { BAIXA, MEDIA, ALTA }

    private var contadores = arrayOf(10000000000000L, 10000000000000L, 10000000000000L) //todos os contadores

    private fun zerosEsquerda(valor: String): String {
        val zeros = "0".repeat(13 - valor.length)
        return zeros + valor
    }

    private fun novaPrioridade(prioridade: Prioridade) : Long {
        val posicao = prioridade.ordinal //0, 1 ou 2
        contadores[posicao] = contadores[posicao] - 1
        var contadorString = contadores[posicao].toString()
        val novaPrioridade = posicao.toString() + zerosEsquerda(contadorString)
        return novaPrioridade.toLong()
    }



    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }

    @FXML
    private fun inserir(event: ActionEvent) {
        carregarTelaCadastro()
    }
    @FXML
    private fun extrair(event: ActionEvent) {
        fila.extrair()
        if (pacientes == 0) {

        } else {
            nomeProximoPaciente.text = fila.obter()?.nome
            idadeProximoPaciente.text = fila.obter()?.idade.toString()
            prioridadePaciente.text = fila.obter()?.prioridade.toString()
        }
        pacientes--
        pacientesNaFila.text = pacientes.toString()
    }

    fun carregarTelaCadastro() {
        val root: Parent = FXMLLoader.load(javaClass.getResource("cadastro-view.fxml"))
        val scene = Scene(root)

        val stage = Stage()
        stage.title = "Cadastro de paciente"
        stage.scene = scene
        stage.show()
    }
}