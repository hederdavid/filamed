package com.example.filamed2

data class Paciente(val nome: String, val cpf: String, val sexo: String, val dataNascimento: String, val relato: String,
                    val prioridade: String) {
    override fun toString(): String {
        return "Paciente(nome='$nome', cpf='$cpf', sexo='$sexo', dataNascimento='$dataNascimento', relato='$relato', prioridade='$prioridade')"
    }
}