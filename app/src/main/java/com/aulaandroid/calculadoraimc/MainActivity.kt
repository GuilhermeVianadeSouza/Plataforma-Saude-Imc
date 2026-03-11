package com.aulaandroid.calculadoraimc

import android.os.Bundle
import android.util.Log
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    var imc by remember {
        mutableStateOf(0.0)
    }

    var classificacao = when {
        imc == 0.0 -> "Faça o calculo imc"
        imc < 18.5 -> "Abaixo do peso."
        imc < 25.0 -> "Peso Ideal."
        imc < 30.0 -> "Levemente acima do peso"
        imc < 35.0 -> "Obesidade grau I"
        imc < 40.0 -> "Obesidade grau II"
        else -> "Obesidade grau III"
    }
    var corFundo = when {
        imc == 0.0 || imc >= 18.5 && imc < 25.0 -> Color(76, 175, 80, 255)
        imc >= 25.0 && imc < 30 -> Color(243, 135, 39, 255)
        else -> Color.Red
    }

    val imcFormatado = "%.2f".format(imc)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
                .padding(horizontal = 24.dp),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .offset(y = (-30).dp)
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xfff9f6f6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Seus dados",
                        color = colorResource(R.color.cor_app),
                        fontSize = 24.sp
                    )
                    OutlinedTextField(
                        value = altura,
                        onValueChange = {novoValor ->
                            Log.i("Altura em cm", novoValor)
                            altura = novoValor
                        },
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        placeholder = {
                            Text(text = "Digite sua altura",
                                fontSize = 10.sp)
                        },
                        label = {
                            Text(text = "Digite sua altura em cm",
                                fontSize = 10.sp)
                        }
                    )
                    OutlinedTextField(
                        value = peso,
                        onValueChange = {novoValorPeso ->
                            Log.i("Peso em kilograma", novoValorPeso)
                            peso = novoValorPeso
                        },
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Digite seu peso",
                                fontSize = 10.sp)
                        },
                        label = {
                            Text(text = "digite seu peso em kilograma",
                                fontSize = 10.sp)
                        }
                    )
                    Button(
                        onClick = {
                            var alturaConvertida = altura.replace(",", ".").toDoubleOrNull() ?: 0.0
                            var pesoConvertido = peso.replace(",", ".").toDoubleOrNull() ?:0.0
                            imc = pesoConvertido / (alturaConvertida * alturaConvertida)

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.cor_app)
                        )
                    ) {
                        Text(text = "Calcular",
                            fontSize = 24.sp,
                            color = Color.White)
                    }
                    OutlinedButton(
                        onClick = {
                            altura = ""
                            peso = ""
                            imc = 0.0
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.cor_app)
                        )
                    ) {
                        Text(text = "Limpar campos",
                            fontSize = 24.sp,
                            color = Color.White)
                    }
                }
            }
        }
        Card(
            modifier = modifier
                .width(350.dp)
                .height(200.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = corFundo
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = imcFormatado,
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = classificacao,
                    color = Color.White,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
