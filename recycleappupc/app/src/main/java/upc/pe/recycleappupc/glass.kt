package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.ms.square.android.expandabletextview.ExpandableTextView

class glass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glass)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare3)
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
        expTv.setText("¿Cuál es el impacto ambiental del vidrio? \nEl vidrio es una sustancia que se obtiene de fundir un determinado tipo de arena a alta temperatura. Su fabricación exige un gran gasto energético, aunque la materia prima es abundante, y genera una importante contaminación atmosférica, que no siempre es tratada convenientemente.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cómo reciclar vidrio en casa? \nCada vez es más común reciclar vidrio en casa. Normalmente, se utiliza un cubo de la basura para el reciclaje de envases de vidrio en exclusiva, el cual es importante que siempre esté bien marcado para evitar confundirnos durante el proceso del reciclaje. Por otro lado, siempre debemos intentar eliminar todo el posible líquido que pueda quedar como resto en las botellas y diferentes envases de vidrio que estamos reciclando.")

        val expTv3 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView3)
        expTv3.setText("Ideas para reciclar botellas de vidrio \nAunque si lo que buscas son manualidades con botellas de vidrio que te permitan reutilizar y alargar su vida útil de una forma más ingeniosa y original, no te pierdas la siguiente lista de ideas: \n" +
                "\n" +
                "Usarlas como floreros de decoración, centros de mesa y en la cocina con plantas aromáticas. \n" +
                "\n" +
                "Ponerlas en el baño, con palitos de sándalo que mantengan un olor fresco. \n" +
                "\n" +
                "Como zapatero, colocándolas con tablas de madera u otro material. \n" +
                "\n" +
                "Decorándolas con una fotografía a modo de etiqueta de la botella de vino y hacer un regalo de lo más original. \n" +
                "\n" +
                "Como lámparas, con bombillas o luces pequeñas e, incluso, portavelas.")


    }
}