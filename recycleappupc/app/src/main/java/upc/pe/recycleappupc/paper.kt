package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class paper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare1)
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
        expTv.setText("¿Cuál es el impacto ambiental de la fabricación del papel? \nEl impacto de la fabricación del papel sobre los bosques es pues claro: cada año se cortan muchos millones de árboles solo para fabricar papel. Sumémosles los que se cortan para leña o carpintería, los que arrasan los incendios y los que se destruyen al construir grandes infraestructuras como carreteras. Entonces nos podremos hacer una idea de la alarmante deforestación que está afectando al planeta entero y en nuestro caso concreto a la Amazonía.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cómo puedo reciclar el papel? \nSe puede reciclar las hojas de papel, folletos, libros, periodicos y revistas, sobres. Etapas para reciclar el papel: \n" +
                "\n" +
                "Cortar el papel en trozos pequeños. \n" +
                "\n" +
                "Echar los trozos en el recipiente y añadir el doble de agua caliente que de papel. Dejarlos en remojo un rato para que absorban bien el agua. \n" +
                "\n" +
                "Licuar hasta obtener una textura de salsa. \n" +
                "\n" +
                "Colocamos la mezcla en una malla con ayuda de una cuchara. Podemos usar un rodillo para hacerlo más fácil. \n" +
                "\n" +
                "Quitar el exceso de agua con una esponja y dejar que seque. \n" +
                "\n" +
                "")

        val expTv3 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView3)
        expTv3.setText("¿Cómo dejar de desperdiciar el papel? \nComo resultado del impacto negativo que tiene el papel sobre el medio ambiente, en la actualidad se han desarrollado nuevas formas de contrarrestar el uso excesivo del papel como reducir el uso diario de papel, aprovechar la tecnología y digitalizar sus procesos (firma electrónica, formularios digitales, etc.)")


    }
}