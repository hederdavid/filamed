package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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


    private lateinit var fila: FilaPrioridade
    private var qtdCacientesEnfileirados = 0

    private lateinit var stage: Stage
    private lateinit var scene: Scene
    private lateinit var root: Parent


    fun carregarTelaPrioridade(event: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("prioridade-view.fxml"))
        root = loader.load()

        atualizarDadosTelaPrioridade(loader)

        stage = (event.source as Node).scene.window as Stage
        scene = Scene(root)
        stage.scene = scene
        stage.show()
    }

    fun setDadosCadastro(fila: FilaPrioridade, qtdCacientesEnfileirados: Int) {
        this.fila = fila
        this.qtdCacientesEnfileirados = qtdCacientesEnfileirados
    }

    private fun atualizarDadosTelaPrioridade(loader: FXMLLoader) {
        val nomeCompleto: String = nomeField.text
        val cpf: String = cpfField.text
        val sexo: Char = sexoField.text[0]

        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dataNascimentoString: String = dataNascimentoField.text
        val dataNascimento: LocalDate = LocalDate.parse(dataNascimentoString, formato)

        val relatoQueixasSintomas: String = relatoQueixasSintomasField.text
        val prioridadeController: PrioridadeController = loader.getController()

        prioridadeController.setDados(nomeCompleto, cpf, sexo, dataNascimento, relatoQueixasSintomas,
                qtdCacientesEnfileirados, fila)
    }
}