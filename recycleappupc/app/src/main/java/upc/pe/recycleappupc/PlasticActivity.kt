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
        val homeBottomNav = findViewById<BottomNavigationView>(R.id.home_buttom_navigation)
        homeBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ItemReconocer -> {
                    startActivity(Intent(baseContext,CameraActivity::class.java))
                    true
                }
                R.id.ItemGreenPoints -> {
                    Toast.makeText(this,"Funciona Puntos Verdes", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.ItemStatistics -> {
                    startActivity(Intent(baseContext,EstadisticasActivity::class.java))
                    true
                }
                else -> {false}
            }
        }


        val expTv = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView)
        expTv.setText("¿Cuál es el impacto ambiental del plástico? \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")

        val expTv2 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView2)
        expTv2.setText("¿Cómo puedo reutilizar una botella de plástico? \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")

        val expTv3 = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView3)
        expTv3.setText("¿Qué debo hacer para dejar de consumir plástico? \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")


    }
}