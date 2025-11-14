package com.fatec.atv2mediageral.models

data class Aluno(
    var nome: String,
    val notas: MutableList<Double> = mutableListOf()
) {

    fun calcularMedia(): Double {
        if (notas.size < 3) return 0.0
        return notas.average()
    }

    fun statusFinal(): String {
        val media = calcularMedia()

        return when {
            media < 6.0 -> "Reprovado"
            media <= 9.0 -> "Aprovado"
            media > 9.0 -> "Ótimo Aproveitamento"
            else -> "Indefinido"
        }
    }
}