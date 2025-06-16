package duran.cesar.popcornfactory_duran

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var bundle= intent.extras
        var ns=0
        var id= -1
        var title=""

        if (bundle!=null){

            ns=  bundle.getInt("numberSeats")
            title= bundle.getString("titulo")!!
            findViewById<android.widget.ImageView>(R.id.imgPelicula).setImageResource(bundle.getInt("header"))
            findViewById<android.widget.TextView>(R.id.tituloPelicula).setText(bundle.getString("titulo"))
            findViewById<android.widget.TextView>(R.id.movie_sumary).setText(bundle.getString("sinopsis"))
            findViewById<android.widget.TextView>(R.id.seatsLeft).setText("$ns seats available")
            id=bundle.getInt("pos")


        }
        if (ns==0){
            findViewById<android.widget.Button>(R.id.buyTickets).isEnabled= false
        }else{
            findViewById<android.widget.Button>(R.id.buyTickets).setOnClickListener {
                val intent: Intent= Intent(this, SeatSelection::class.java)

                intent.putExtra("movie", id)
                intent.putExtra("name", title)

                this.startActivity(intent)

            }
        }

    }
}