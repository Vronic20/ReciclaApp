package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Information_paper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_paper)

        var btnInfo = this.findViewById<Button>(R.id.button2)

        btnInfo.setOnClickListener{
            val intent = Intent(this@Information_paper, paper::class.java)
            startActivity(intent)
        }
    }
}