package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class organic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organic)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare5)
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
        expTv.setText("¿Cuál es el impacto ambiental de residuos orgánicos? \nReciclar residuos orgánicos ayuda a reducir el impacto ambiental. La mayoría de estos residuos son desechos de comida, restos vegetales que provienen de la recogida municipal y comercial de nuestras ciudades, así como de las industrias agroalimentarias y derivados.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cómo puedo reciclar los residuos orgánicos? \nEs posible transformar los residuos sólidos orgánicos industriales o domiciliarios en abono gracias al compostaje. El compost puede utilizarse como abono y aportar grandes beneficios ambientales, ya que necesita una menor cantidad de energía para su producción, no genera otros desechos en su proceso y permite aprovechar los residuos orgánicos como nuevos recursos, y por lo tanto, se disminuye el volumen de residuos dispuestos en rellenos sanitarios.")

    }
}