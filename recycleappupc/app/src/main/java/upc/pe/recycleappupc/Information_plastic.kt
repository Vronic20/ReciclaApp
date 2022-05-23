package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Information_plastic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_plastic)

        var btnInfo = this.findViewById<Button>(R.id.button1)

        btnInfo.setOnClickListener{
            val intent = Intent(this@Information_plastic, PlasticActivity::class.java)
            startActivity(intent)
        }
        val homeBottomNav = findViewById<BottomNavigationView>(R.id.home_buttom_navigation)
        homeBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ItemReconocer -> {
                    startActivity(Intent(baseContext,CameraActivity::class.java))
                    true
                }
                R.id.ItemGreenPoints -> {
                    startActivity(Intent(baseContext,puntos_verdes::class.java))
                    true
                }
                R.id.ItemStatistics -> {
                    startActivity(Intent(baseContext,EstadisticasActivity::class.java))
                    true
                }
                else -> {false}
            }
        }
    }
}