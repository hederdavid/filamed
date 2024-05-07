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
    private lateinit var cpf: String
    private lateinit var sexo: String
    private lateinit var relato: String

    private var prioridade: String = "Prioridade não selecionada"
    private var pacientes = 0
    private lateinit var fila: HeapMaximoPaciente
    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent

    fun setFilaEPacientes(fila: HeapMaximoPaciente, pacientes: Int) {
        this.fila = fila
        this.pacientes = pacientes
    }

    fun prioridadeEmergencia(event: ActionEvent) {
        this.prioridade = "Emergência"
        selecionarTelaHome(event)
    }

    fun prioridadeMuitaUrgencia(event: ActionEvent) {
        this.prioridade = "Muita Urgência"
        selecionarTelaHome(event)
    }

    fun prioridadeUrgencia(event: ActionEvent) {
        this.prioridade = "Urgência"
        selecionarTelaHome(event)
    }

    fun prioridadePoucaUrgencia(event: ActionEvent) {
        this.prioridade = "Pouca Urgência"
        selecionarTelaHome(event)
    }
    fun prioridadeNaoUrgencia(event: ActionEvent) {
        this.prioridade = "Não Urgência"
        selecionarTelaHome(event)
    }

    private fun selecionarTelaHome(event: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("home-view.fxml"))
        root = loader.load()

        val paciente = Paciente(nome, cpf, sexo, idade, relato, prioridade)

        val homecontroller: HomeController = loader.getController()
        fila.inserir(paciente)
        pacientes++
        println(fila).toString()
        println(pacientes)

        nome = fila.obter()?.nome ?: "Erro ao obter nome"
        idade = fila.obter()?.dataNascimento ?: "Erro ao obter idade"
        prioridade = fila.obter()?.prioridade ?: "Erro ao obter prioridade"

        homecontroller.inserirDados(nome, idade, prioridade, pacientes, fila)

        //root = FXMLLoader.load(javaClass.getResource("home-view.fxml"))
        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    fun setDados(nome: String, idade: String, cpf: String, sexo: String, relato: String, pacientes: Int, fila: HeapMaximoPaciente) {
        this.nome = nome
        this.cpf = cpf
        this.sexo = sexo
        this.idade = idade
        this.relato = relato
        this.pacientes = pacientes
        this.fila = fila
    }
}