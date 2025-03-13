package mx.edu.itson.potros.practica6

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Catalogo : AppCompatActivity() {
    var adapter: PeliculaAdapter? = null
    var seriesAdapter: PeliculaAdapter? = null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        cargarPeliculas()
        cargarSeries()

        adapter = PeliculaAdapter(this, peliculas)
        seriesAdapter = PeliculaAdapter(this, series)

        val gridPelis: GridView = findViewById(R.id.movies_catalogo)
        val gridSeries: GridView = findViewById(R.id.mseries_catalogo)

        gridPelis.adapter = adapter
        gridSeries.adapter = seriesAdapter
    }

    private fun cargarPeliculas() {
        peliculas.add(
            Pelicula(
                "Demon Slayer: Kimetsu no Yaiba",
                R.drawable.demon,
                R.drawable.demon,
                "A thrilling battle in the epic anime saga.",
                arrayListOf()
            )
        )
        peliculas.add(
            Pelicula(
                "Dune",
                R.drawable.dune,
                R.drawable.dune2,
                "An epic journey exploring new worlds.",
                arrayListOf()
            )
        )
        // Add more movies here
    }

    private fun cargarSeries() {
        series.add(
            Pelicula(
                "Halo",
                R.drawable.halo,
                R.drawable.halos,
                "A high-stakes sci-fi action series.",
                arrayListOf()
            )
        )
        series.add(
            Pelicula(
                "The Witcher",
                R.drawable.thewitcher,
                R.drawable.thewitchers,
                "A gripping tale of monsters and magic.",
                arrayListOf()
            )
        )
        // Add more series here
    }
}

class PeliculaAdapter(private val context: Context, private val peliculas: ArrayList<Pelicula>) : BaseAdapter() {

    override fun getCount(): Int = peliculas.size

    override fun getItem(position: Int): Any = peliculas[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val pelicula = peliculas[position]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val vista = inflator.inflate(R.layout.pelicula, null)

        // Correctly reference the IDs in pelicula.xml
        val ivPelicula: ImageView = vista.findViewById(R.id.image_movie_cell)
        val tvTitulo: TextView = vista.findViewById(R.id.movie_title_cell)

        // Set data for ImageView and TextView
        ivPelicula.setImageResource(pelicula.image)
        tvTitulo.text = pelicula.titulo

        // Set onClickListener for the ImageView
        ivPelicula.setOnClickListener {
            var seatsAvailable=20-pelicula.seats.size
            val intent = Intent(context, detalle_pelicula::class.java).apply {
                putExtra("nombre", pelicula.titulo)
                putExtra("image", pelicula.image)
                putExtra("header", pelicula.header)
                putExtra("sinopsis", pelicula.sinopsis)
                putExtra("numberSeats", (20 - pelicula.seats.size))
            }
            context!!.startActivity(intent)
        }

        return vista
    }
}

