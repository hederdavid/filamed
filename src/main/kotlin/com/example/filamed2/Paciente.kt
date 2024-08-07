package com.example.filamed2

import java.time.LocalDate
import java.time.LocalDateTime


data class Paciente(var nomeCompleto: String, var cpf: String, var sexo: Char, var dataNascimento: LocalDate,
                    var relatoQueixasSintomas: String, var prioridade: Int) {
    var dataHoraEnfileiramento: LocalDateTime = LocalDateTime.now()
    var dataHoraDesenfileiramento: LocalDateTime = LocalDateTime.now()
    var senha: String = "Senha não definida."
    override fun toString(): String {
        return "Paciente(nomeCompleto='$nomeCompleto', cpf='$cpf', sexo=$sexo, dataNascimento=$dataNascimento," +
                " relatoQueixasSintomas='$relatoQueixasSintomas', prioridade=$prioridade, dataHoraEnfileiramento=$dataHoraEnfileiramento," +
                " dataHoraDesenfileiramento=$dataHoraDesenfileiramento, senha='$senha')"
    }
}