package com.example.filamed2

import java.time.LocalDateTime

class FilaPrioridade(private val tamanho: Int = 10): Enfileiravel{

    private var pacientes: Array<Paciente?> = arrayOfNulls(tamanho)
    private var ponteiroFim = -1
    private var quantidade = 0

    override fun enfileirar(paciente: Paciente) {
        if (!estaCheia()) {
            ponteiroFim = ponteiroFim.inc()
            pacientes[ponteiroFim] = paciente
            paciente.dataHoraEnfileiramento = LocalDateTime.now()
            ajustarAcima(ponteiroFim)
            quantidade++
        } else {
            println("Fila de Prioridades Cheia!")
        }
    }

    override fun desenfileirar(): Paciente? {
        var dadoRaiz: Paciente? = null
        if (!estaVazia()) {
            dadoRaiz = pacientes[0]
            pacientes[0] = pacientes[ponteiroFim]
            ponteiroFim = ponteiroFim.dec()
            dadoRaiz?.dataHoraDesenfileiramento = LocalDateTime.now().toString()
            ajustarAbaixo(0)
            quantidade--
        } else {
            println("Fila de Prioridades Vazia!")
        }
        return dadoRaiz
    }

    override fun atualizar(paciente: Paciente){
        if (!estaVazia()) {
            pacientes[0] = paciente
            ajustarAbaixo(0)
        } else {
            print("Fila de Prioridades Vazia!")
        }
    }

    override fun espiar(): Paciente? {
        var dadoRaiz: Paciente? = null
        if (!estaVazia())
            dadoRaiz = pacientes[0]
        else
            println("Fila de Prioridades Cheia!")

        return dadoRaiz
    }

    override fun estaCheia(): Boolean {
        return ponteiroFim == pacientes.size - 1
    }

    override fun estaVazia(): Boolean {
        return ponteiroFim == -1
    }

    override fun imprimir(): String {
        var resultado = "\n["
        for (i in 0..ponteiroFim) {
            resultado += "${pacientes[i]}"
            if (i != ponteiroFim)
                resultado += ","
        }
        return "$resultado\n]"
    }

    private fun ajustarAcima(indice: Int) {
        var indiceAtual = indice
        while (indiceAtual != 0) {
            val indicePai = indicePai(indiceAtual)
            if (pacientes[indicePai]!!.prioridade < pacientes[indiceAtual]!!.prioridade) {
                trocar(indiceAtual, indicePai)
                indiceAtual = indicePai
            } else if (pacientes[indicePai]!!.prioridade == pacientes[indiceAtual]!!.prioridade) {
                if (pacientes[indicePai]!!.dataHoraEnfileiramento.isAfter(pacientes[indiceAtual]!!.dataHoraEnfileiramento)) {
                    trocar(indiceAtual, indicePai)
                    indiceAtual = indicePai
                } else {
                    break;
                }
            } else {
                break
            }
        }
    }

    private fun ajustarAbaixo(pai: Int) {
        val filhoEsquerdo = indiceFilhoEsquerda(pai)
        val filhoDireito = indiceFilhoDireita(pai)
        var maior = pai;

        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array
            if (pacientes[maior]!!.prioridade < pacientes[filhoEsquerdo]!!.prioridade) {
                maior = filhoEsquerdo
            } else if (pacientes[maior]!!.prioridade == pacientes[filhoEsquerdo]!!.prioridade) {
                if (pacientes[maior]!!.dataHoraEnfileiramento.isAfter(pacientes[filhoEsquerdo]!!.dataHoraEnfileiramento)) {
                    maior = filhoEsquerdo
                }
            }
        }

        if (filhoDireito <= ponteiroFim) { //está dentro dos valores válidos do array
            if (pacientes[maior]!!.prioridade < pacientes[filhoDireito]!!.prioridade) {
                maior = filhoDireito
            } else if (pacientes[maior]!!.prioridade == pacientes[filhoDireito]!!.prioridade) {
                if (pacientes[maior]!!.dataHoraEnfileiramento.isAfter(pacientes[filhoDireito]!!.dataHoraEnfileiramento)) {
                    maior = filhoDireito
                }
            }
        }

        if (maior != pai) {
            trocar(pai, maior)
            ajustarAbaixo(maior)
        }
    }

    private fun trocar(i: Int, j: Int) {
        val temp = pacientes[i]
        pacientes[i] = pacientes[j]
        pacientes[j] = temp
    }

    private fun indicePai(indiceFilho: Int): Int {
        return (indiceFilho-1)/2
    }

    private fun indiceFilhoEsquerda(indicePai: Int): Int {
        return 2 * indicePai + 1
    }

    private fun indiceFilhoDireita(indicePai: Int): Int	{
        return (2 * indicePai + 1) + 1
    }

    override fun toString(): String {
        return "FilaPrioridade(tamanho=$tamanho, pacientes=${pacientes.contentToString()}, ponteiroFim=$ponteiroFim)"
    }

    fun size(): Int {
        return quantidade
    }

    fun get(i: Int): Paciente? {
        return pacientes[i]
    }


}