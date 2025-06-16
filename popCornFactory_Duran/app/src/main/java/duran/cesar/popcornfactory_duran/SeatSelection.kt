package duran.cesar.popcornfactory_duran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)

        val title: TextView = findViewById(R.id.titleSeats)
        val editTextNombre: TextView = findViewById(R.id.editTextNombreCliente)
        var posMovie = -1
        val bundle = intent.extras

        if (bundle != null) {

            title.setText(bundle.getString("name"))
            posMovie = bundle.getInt("id")

        }
        val confirm: Button = findViewById(R.id.confirm)

        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        confirm.setOnClickListener {
            val nombreCliente = editTextNombre.text.toString().trim()
            if (nombreCliente.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val seatSelectedId = when {
                row1.checkedRadioButtonId != -1 -> row1.checkedRadioButtonId
                row2.checkedRadioButtonId != -1 -> row2.checkedRadioButtonId
                row3.checkedRadioButtonId != -1 -> row3.checkedRadioButtonId
                row4.checkedRadioButtonId != -1 -> row4.checkedRadioButtonId
                else -> -1
            }

            if (seatSelectedId == -1) {
                Toast.makeText(this, "Por favor selecciona un asiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val seatSelected = findViewById<TextView>(seatSelectedId).text.toString()
            //Anadir la logica para reservar el luagr seleccionado por el usuario
            //hacer una nueva actividad donde se vea el resumen de la compra es decir que se agregue el nombre del cliente y se vea
            //el asiento que se selecciono.
            Toast.makeText(this, "Disfruta la pelicula :)", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ResumenCompraActivity::class.java)
            intent.putExtra("movieName", title.text.toString())
            intent.putExtra("seat", seatSelected)
            intent.putExtra("cliente", nombreCliente)
            intent.putExtra("posMovie", posMovie)
            startActivity(intent)

        }



        row1.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row1.check(checkedId)
            }
        }
        row2.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId > -1) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
            }
        }

        row3.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId > -1) {
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
            }
        }

        row4.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
            }
        }

    }
}