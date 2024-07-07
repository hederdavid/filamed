package com.example.filamed2



import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import java.time.LocalDate
import java.time.Period


class HomeController {

    @FXML
    private lateinit var idadeProximoPaciente: Label
    @FXML
    private lateinit var nomeProximoPaciente: Label
    @FXML
    private lateinit var prioridadeDoPaciente: Label
    @FXML
    private lateinit var pacientesNaFila: Label

    @FXML
    lateinit var qtdCriancasLabel: Label
    @FXML
    lateinit var qtdAdolescentesLabel: Label
    @FXML
    lateinit var qtdAdultosLabel: Label
    @FXML
    lateinit var qtdIdososLabel: Label

    @FXML
    lateinit var senhaLabel: Label

    private var fila = FilaPrioridade(10)
    private var qtdPacientesEnfileirados: Int = 0
    private var qtdCriancas: Int = 0
    private var qtdAdolescentes: Int = 0
    private var qtdAdultos: Int = 0
    private var qtdIdosos: Int = 0

    private var qtdPrioridadeEmergencia = 0
    private var qtdPrioridadeMuitaUrgencia = 0
    private var qtdPrioridadeUrgencia = 0
    private var qtdPrioridadePoucaUrgencia = 0
    private var qtdPrioridadeNaoUrgente = 0
    private var senha: String = "Senha não definida"

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

    @FXML
    private fun carregarTelaVerificarPacienteEnfileirado(event: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("verificar-cpf-view.fxml"))
        root = loader.load()

        atualizarDadosTelaVerificarPacienteEnfileirado(loader)

        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    @FXML
    private fun carregarTelaPrioridadesFilas(event: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("prioridades-filas-view.fxml"))
        root = loader.load()

        //atualizarDadosTelaVerificarPacienteEnfileirado(loader)

        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()

    }

    fun desenfileirarPaciente(event: ActionEvent) {
        if (!fila.estaVazia()) {
            val dataAtual: LocalDate = LocalDate.now()
            val periodo: Period = Period.between(fila.espiar()?.dataNascimento, dataAtual)
            val idade: String = periodo.years.toString()
            when {
                idade.toInt() in 0..11 && qtdCriancas != 0 -> qtdCriancas--
                idade.toInt() in 12..17 && qtdAdolescentes != 0 -> qtdAdolescentes--
                idade.toInt() in 18..59 && qtdAdultos != 0 -> qtdAdultos--
                idade.toInt() >= 60 && qtdIdosos != 0 -> qtdIdosos--
            }
        }

        fila.desenfileirar()

        if (qtdPacientesEnfileirados != 0) {
            qtdPacientesEnfileirados--
        }

        pacientesNaFila.text = qtdPacientesEnfileirados.toString()

        if (fila.espiar() == null) {
            nomeProximoPaciente.text = "NENHUM PACIENTE NA FILA"
            idadeProximoPaciente.text = "0"
            prioridadeDoPaciente.text = "NÃO DEFINIDA"
            qtdCriancas = 0
            qtdAdolescentes = 0
            qtdAdultos = 0
            qtdIdosos = 0
            senhaLabel.text = ""

        } else {
            nomeProximoPaciente.text = fila.espiar()?.nomeCompleto

            val dataAtual: LocalDate = LocalDate.now()
            val periodo: Period = Period.between(fila.espiar()?.dataNascimento, dataAtual)
            val idade: String = periodo.years.toString()

            idadeProximoPaciente.text = idade
            this.senhaLabel.text = fila.espiar()?.senha

            when (fila.espiar()?.prioridade) {
                5 -> prioridadeDoPaciente.text = "Emergência"
                4 -> prioridadeDoPaciente.text = "Muita Urgência"
                3 -> prioridadeDoPaciente.text = "Urgência"
                2 -> prioridadeDoPaciente.text = "Pouca Urgência"
                1 -> prioridadeDoPaciente.text = "Não Urgência"
                else -> prioridadeDoPaciente.text = "NÃO DEFINIDA"
            }
        }
        qtdCriancasLabel.text = qtdCriancas.toString()
        qtdAdolescentesLabel.text = qtdAdolescentes.toString()
        qtdAdultosLabel.text = qtdAdultos.toString()
        qtdIdososLabel.text = qtdIdosos.toString()
    }

    fun setDadosHome(nome: String, idade: String, prioridade: String, qtdPacientesEnfileirados: Int, fila: FilaPrioridade,
                     qtdCriancas: Int, qtdAdolescentes: Int, qtdAdultos: Int, qtdIdosos: Int, qtdPrioridadeEmergencia: Int,
                     qtdPrioridadeMuitaUrgencia: Int, qtdPrioridadeUrgencia: Int, qtdPrioridadePoucaUrgencia: Int,
                     qtdPrioridadeNaoUrgente: Int, senha: String) {
        this.nomeProximoPaciente.text = nome
        this.idadeProximoPaciente.text = idade
        this.prioridadeDoPaciente.text = prioridade
        this.pacientesNaFila.text = qtdPacientesEnfileirados.toString()
        this.senhaLabel.text = fila.espiar()?.senha
        this.fila = fila
        this.qtdPacientesEnfileirados = qtdPacientesEnfileirados

        this.qtdCriancasLabel.text = qtdCriancas.toString()
        this.qtdAdolescentesLabel.text = qtdAdolescentes.toString()
        this.qtdAdultosLabel.text = qtdAdultos.toString()
        this.qtdIdososLabel.text = qtdIdosos.toString()

        this.qtdCriancas = qtdCriancas
        this.qtdAdolescentes = qtdAdolescentes
        this.qtdAdultos = qtdAdultos
        this.qtdIdosos = qtdIdosos

        this.qtdPrioridadeEmergencia = qtdPrioridadeEmergencia
        this.qtdPrioridadeMuitaUrgencia = qtdPrioridadeMuitaUrgencia
        this.qtdPrioridadeUrgencia = qtdPrioridadeUrgencia
        this.qtdPrioridadePoucaUrgencia = qtdPrioridadePoucaUrgencia
        this.qtdPrioridadeNaoUrgente = qtdPrioridadeNaoUrgente
        this.senha = senha
    }

    private fun atualizarDadosTelaCadastro(loader: FXMLLoader) {
        val cadastroController: CadastroController = loader.getController()
        cadastroController.setDadosCadastro(fila, qtdPacientesEnfileirados, qtdCriancas, qtdAdolescentes, qtdAdultos,
            qtdIdosos, qtdPrioridadeEmergencia, qtdPrioridadeMuitaUrgencia, qtdPrioridadeUrgencia, qtdPrioridadePoucaUrgencia,
            qtdPrioridadeNaoUrgente, senha)
    }

    private fun atualizarDadosTelaVerificarPacienteEnfileirado(loader: FXMLLoader) {
        val verificarCpfController: VerificarCpfController = loader.getController()
        verificarCpfController.setDadosVerificarCpf(fila, qtdPacientesEnfileirados, qtdCriancas, qtdAdolescentes, qtdAdultos,
            qtdIdosos, qtdPrioridadeEmergencia, qtdPrioridadeMuitaUrgencia, qtdPrioridadeUrgencia, qtdPrioridadePoucaUrgencia,
            qtdPrioridadeNaoUrgente, senha)
    }


}