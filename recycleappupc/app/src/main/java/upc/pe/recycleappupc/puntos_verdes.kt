package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class puntos_verdes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntos_verdes)
        var btnColonial = this.findViewById<ImageView>(R.id.imageView7)
        btnColonial.setOnClickListener{
            val intent = Intent(this@puntos_verdes, plaza_colonial::class.java)
            startActivity(intent)
        }
        var btnVentanilla = this.findViewById<ImageView>(R.id.imageView32)
        btnVentanilla.setOnClickListener{
            val intent = Intent(this@puntos_verdes, plaza_ventanilla::class.java)
            startActivity(intent)
        }
        var btnBenavides = this.findViewById<ImageView>(R.id.imageView34)
        btnBenavides.setOnClickListener{
            val intent = Intent(this@puntos_verdes, plaza_benavides::class.java)
            startActivity(intent)
        }
        var btnFaucett = this.findViewById<ImageView>(R.id.imageView36)
        btnFaucett.setOnClickListener{
            val intent = Intent(this@puntos_verdes, faucett::class.java)
            startActivity(intent)
        }
    }


}