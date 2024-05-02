package com.example.filamed2

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.stage.Stage

class CadastroController {
    lateinit var nomeField: TextField
    lateinit var cpfField: TextField
    lateinit var sexoField: TextField
    lateinit var dataNascimentoField: TextField
    lateinit var relatoQueixasSintomasField: TextField
    fun salvarDadosEChamarProximaTela(event: ActionEvent) {
            val nome: String = nomeField.text
            val cpf: String = cpfField.text
            val sexo: String = sexoField.text
            val dataNascimento: String = dataNascimentoField.text
            val relato: String = relatoQueixasSintomasField.text

            // Adicione aqui a lógica de verificação do nome de usuário e senha
            println("Nome: $nome")
            println("CPF: $cpf")
            println("Sexo: $sexo")
            println("Data de Nascimento: $dataNascimento")
            println("Relato: $relato")
            carregarTelaPrioridade()
    }

    private fun carregarTelaPrioridade() {
        val root: Parent = FXMLLoader.load(javaClass.getResource("prioridade-view.fxml"))
        val scene = Scene(root)

        val stage = Stage()
        stage.title = "Prioridade"
        stage.scene = scene
        stage.show()
    }
}