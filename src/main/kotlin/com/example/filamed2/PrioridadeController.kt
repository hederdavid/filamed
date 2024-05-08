package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.time.LocalDate
import java.time.Period


class PrioridadeController {

    private lateinit var nomeCompleto: String
    private lateinit var cpf: String
    private var sexo: Char = '\u0000'
    private lateinit var dataNascimento: LocalDate
    private lateinit var relatoQueixasSintomas: String
    private var prioridade: Int = 0
    private var prioridadeString: String = ""

    private var qtdPacientesEnfileirados = 0
    private lateinit var fila: FilaPrioridade

    private var qtdCriancas: Int = 0
    private var qtdAdolescentes: Int = 0
    private var qtdAdultos: Int = 0
    private var qtdIdosos: Int = 0

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent

    private fun chamarTelaHome(event: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("home-view.fxml"))
        root = loader.load()

        inserirPacienteCadastrado()
        println(fila).toString()
        println(qtdPacientesEnfileirados)

        atualizarDadosTelaHome(loader)

        //root = FXMLLoader.load(javaClass.getResource("home-view.fxml"))
        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    private fun inserirPacienteCadastrado() {
        val paciente = Paciente(nomeCompleto, cpf, sexo, dataNascimento, relatoQueixasSintomas, prioridade)
        fila.enfileirar(paciente)
        qtdPacientesEnfileirados++
    }

    private fun atualizarDadosTelaHome(loader: FXMLLoader) {
        val homecontroller: HomeController = loader.getController()

        val dataNascimentoAtual = dataNascimento

        nomeCompleto = fila.espiar()?.nomeCompleto ?: "Erro ao obter nome"
        dataNascimento = (fila.espiar()?.dataNascimento ?: "Erro ao obter idade") as LocalDate

        if (fila.espiar()?.prioridade == 5) {
            prioridadeString = "Emergência"
        } else if (fila.espiar()?.prioridade == 4) {
            prioridadeString = "Muita Urgência"
        } else if (fila.espiar()?.prioridade == 3) {
            prioridadeString = "Urgência"
        } else if (fila.espiar()?.prioridade == 2) {
        prioridadeString = "Pouca Urgência"
        } else if (fila.espiar()?.prioridade == 1) {
            prioridadeString = "Não Urgência"
        }

        var dataAtual: LocalDate = LocalDate.now()
        var periodo: Period = Period.between(dataNascimentoAtual, dataAtual)
        var idade: Int = periodo.years

        if (idade in 0..11) {
            qtdCriancas++
        } else if (idade in 12..17) {
            qtdAdolescentes++
        } else if (idade in 18..59) {
            qtdAdultos++
        } else if (idade >= 60) {
            qtdIdosos++
        }

        periodo = Period.between(dataNascimento, dataAtual)
        idade = periodo.years


        homecontroller.setDadosHome(nomeCompleto, idade.toString(), prioridadeString, qtdPacientesEnfileirados, fila,
            qtdCriancas, qtdAdolescentes, qtdAdultos, qtdIdosos)
    }

    fun setDados(nomeCompleto: String, cpf: String, sexo: Char, dataNascimento: LocalDate, relatoQueixasSintomas: String,
                 qtdPacientesEnfileirados: Int, fila: FilaPrioridade, qtdCriancas: Int, qtdAdolescentes: Int,
                 qtdAdultos: Int, qtdIdosos: Int) {
        this.nomeCompleto = nomeCompleto
        this.cpf = cpf
        this.sexo = sexo
        this.dataNascimento = dataNascimento
        this.relatoQueixasSintomas = relatoQueixasSintomas
        this.qtdPacientesEnfileirados = qtdPacientesEnfileirados
        this.fila = fila
        this.qtdCriancas = qtdCriancas
        this.qtdAdolescentes = qtdAdolescentes
        this.qtdAdultos = qtdAdultos
        this.qtdIdosos = qtdIdosos
    }


    fun prioridadeEmergencia(event: ActionEvent) {
        this.prioridade = 5
        chamarTelaHome(event)
    }

    fun prioridadeMuitaUrgencia(event: ActionEvent) {
        this.prioridade = 4
        chamarTelaHome(event)
    }

    fun prioridadeUrgencia(event: ActionEvent) {
        this.prioridade = 3
        chamarTelaHome(event)
    }

    fun prioridadePoucaUrgencia(event: ActionEvent) {
        this.prioridade = 2
        chamarTelaHome(event)
    }
    fun prioridadeNaoUrgencia(event: ActionEvent) {
        this.prioridade = 1
        chamarTelaHome(event)
    }
}