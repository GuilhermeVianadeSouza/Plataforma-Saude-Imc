package com.aulaandroid.calculadoraimc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulaandroid.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(modifier = Modifier.padding(paddingValues = innerPadding))
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var altura by remember{
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //-- card formulario --
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "Imagem IMC",
                modifier = Modifier.size(80.dp).padding(16.dp)
            )
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 24.dp )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .offset(y = (-30).dp)
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xfff9f6f6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                TextField(
                    value = altura,
                    onValueChange = {novoValor ->
                        Log.i("Altura em cm", novoValor)
                        altura = novoValor
                    },
                    placeholder = {
                        Text(text = "Digite sua altura")
                    },
                    label = {
                        Text(text = "Digite sua altura em cm")
                    }
                )
                TextField(
                    value = peso,
                    onValueChange = {novoValorPeso ->
                        Log.i("Peso em kilograma", novoValorPeso)
                        peso = novoValorPeso
                    },
                    placeholder = {
                        Text(text = "Digite seu peso")
                    },
                    label = {
                        Text(text = "digite seu peso em kilograma")
                    }
                )
            }
        }
        Row() { }
    }
}
