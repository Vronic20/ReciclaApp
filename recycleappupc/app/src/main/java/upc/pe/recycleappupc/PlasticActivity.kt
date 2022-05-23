package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlasticActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare)
        btnShare.setOnClickListener{
            var shareBody = "Download RecycleApp on Play Store: Link"
            var shareSub = "Test";

            var shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

            startActivity(shareIntent)
        }



        val expTv = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView)
        expTv.setText("¿Cuál es el impacto ambiental del plástico? \nLos plásticos han tomado la Tierra. Su creciente producción amenaza con contaminar cada rincón del planeta, especialmente los mares, destino final de muchos de ellos. Según la Fundación Ellen MacArthur, si los actuales patrones de producción y consumo de plástico permanecen, en 2050: Habrá más plástico que peces en el océano, aproximadamente 99% de aves habrán ingerido plástico, la basura marina perjudicará a 600 especies marinas. El 15% de especies afectadas por ingestión y enredamiento con basura marina plástica se encontrarán en peligro de extinción.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cómo puedo reutilizar una botella de plástico? \nExisten muchas opciones para darle una segunda vida a las botellas de plástico como hace maceteros, contenedores de todo tipo, entre otras opciones.")

        val expTv3 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView3)
        expTv3.setText("¿Qué debo hacer para dejar de consumir plástico? \nEliminar por completo el plástico de nuestras vidas es una tarea difícil, sin embargo, reducir su consumo es más sencillo de lo que parece. Con pequeñas acciones en el día a día, se puede limitar mucho la generación de residuos plásticos como utlizar bolsas reutilizables, evitar comprar agua embotellada, no más pajitas de plástico y limitar los alimentos en recipientes de plástico.")


    }
}