package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Information_glass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_glass)
        var btnInfo = this.findViewById<Button>(R.id.button4)

        btnInfo.setOnClickListener{
            val intent = Intent(this@Information_glass, glass::class.java)
            startActivity(intent)
        }
    }
}