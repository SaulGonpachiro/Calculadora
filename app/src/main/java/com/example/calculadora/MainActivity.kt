package com.example.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Variable para manejar el View Binding, que simplifica el acceso a los elementos de la interfaz
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuración del View Binding para enlazar el diseño con la lógica
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar listeners para cada botón, asignando una operación específica
        binding.Sumar.setOnClickListener { calculate("Sumar") } // Botón para sumar
        binding.Restar.setOnClickListener { calculate("Restar") } // Botón para restar
        binding.Multiplicar.setOnClickListener { calculate("Multiplicar") } // Botón para multiplicar
        binding.Dividir.setOnClickListener { calculate("Dividir") } // Botón para dividir
    }

    // Método que realiza la operación matemática según el botón presionado
    private fun calculate(operation: String) {
        // Obtener los valores ingresados en los campos de texto
        val num1Text = binding.num1.text.toString()
        val num2Text = binding.num2.text.toString()

        // Validar que ambos campos tengan contenido
        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        // Convertir los textos ingresados a valores numéricos de tipo Double
        val num1 = num1Text.toDoubleOrNull()
        val num2 = num2Text.toDoubleOrNull()

        // Validar que los valores ingresados sean números válidos
        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Por favor, ingresa números válidos", Toast.LENGTH_SHORT).show()
            return
        }

        // Calcular el resultado según la operación seleccionada
        val result = when (operation) {
            "Sumar" -> num1 + num2 // Suma de los números
            "Restar" -> num1 - num2 // Resta de los números
            "Multiplicar" -> num1 * num2 // Multiplicación de los números
            "Dividir" -> { // División de los números
                // Validar que el divisor no sea cero
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            else -> 0.0 // Caso por defecto (nunca debería ocurrir)
        }

        // Mostrar el resultado de la operación en el TextView correspondiente
        binding.result.text = "Resultado: $result"
    }
}
