package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.square.android.expandabletextview.ExpandableTextView

class metal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metal)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare2)
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
        expTv.setText("¿Por qué reciclamos los metales? \nSegún la confederación EuRIC, de las industrias del reciclaje en Europa, la producción de materias primas a partir de metales reciclados reduce en un 80% la contaminación del aire, en un 76% la contaminación del agua y en un 40% el uso de agua, y reduce considerablemente las emisiones de CO2 a la atmósfera.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cuáles son los metales que se pueden reciclar? \nLos siguientes materiales siempre y cuando estén limpios se pueden reciclar: El hierro se encuentra en puertas, herramientas, tornillos, martillos o adornos; el acero, está muy presente en nuestra vida cotidiana, en electrodomésticos o piezas de coche; el aluminio, se encuentra en platos, papel de aluminio, latas, contenedores, ventanas o utensilios contienen aluminio.")

        val expTv3 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView3)
        expTv3.setText("¿Qué metales no se pueden reciclar? \nAunque casi la mayor parte de los metales se reciclan, existen algunos que no se pueden reutilizar. Tal es el caso de los usados para pinturas o productos tóxicos, tuberías (siempre y cuando no sean de cobre), perchas de ropa y pedacería de metal.")

    }
}