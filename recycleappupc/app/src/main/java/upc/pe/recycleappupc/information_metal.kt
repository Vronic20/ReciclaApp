package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class information_metal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_metal)
        var btnInfo = this.findViewById<Button>(R.id.button3)

        btnInfo.setOnClickListener{
            val intent = Intent(this@information_metal, metal::class.java)
            startActivity(intent)
        }
    }
}