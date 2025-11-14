package com.fatec.atv2mediageral.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fatec.atv2mediageral.models.Aluno

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal() {

    var nome by remember { mutableStateOf("") }
    var tp1 by remember { mutableStateOf("") }
    var tp2 by remember { mutableStateOf("") }
    var tp3 by remember { mutableStateOf("") }

    var aluno by remember { mutableStateOf<Aluno?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Cadastro de Aluno", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome completo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tp1,
            onValueChange = {
                val valor = it.toDoubleOrNull()
                if (valor == null || valor in 0.0..10.0) {
                    tp1 = it
                }
            },
            label = { Text("TP1 (0-10)") }
        )

        OutlinedTextField(
            value = tp2,
            onValueChange = {
                val valor = it.toDoubleOrNull()
                if (valor == null || valor in 0.0..10.0) {
                    tp2 = it
                }
            },
            label = { Text("TP2 (0-10)") }
        )

        OutlinedTextField(
            value = tp3,
            onValueChange = {
                val valor = it.toDoubleOrNull()
                if (valor == null || valor in 0.0..10.0) {
                    tp3 = it
                }
            },
            label = { Text("TP3 (0-10)") }
        )

        Button(
            onClick = {
                val novoAluno = Aluno(nome)

                novoAluno.notas.add(tp1.toDoubleOrNull() ?: 0.0)
                novoAluno.notas.add(tp2.toDoubleOrNull() ?: 0.0)
                novoAluno.notas.add(tp3.toDoubleOrNull() ?: 0.0)

                nome = ""
                tp1 = ""
                tp2 = ""
                tp3 = ""

                aluno = novoAluno
            },
            enabled = nome.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Média")
        }

        aluno?.let { a ->
            Spacer(modifier = Modifier.height(20.dp))

            Text("Média Geral: ${"%.2f".format(a.calcularMedia())}")
            Text("Status Final: ${a.statusFinal()}")
        }
    }
}
