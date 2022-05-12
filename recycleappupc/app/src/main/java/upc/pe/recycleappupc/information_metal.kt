package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class information_metal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_metal)
        var btnInfo = this.findViewById<Button>(R.id.button3)

        btnInfo.setOnClickListener{
            val intent = Intent(this@information_metal, metal::class.java)
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
                    Toast.makeText(this,"Funciona Puntos Verdes", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.ItemStatistics -> {
                    Toast.makeText(this,"Funciona Estadisticas", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {false}
            }
        }
    }
}