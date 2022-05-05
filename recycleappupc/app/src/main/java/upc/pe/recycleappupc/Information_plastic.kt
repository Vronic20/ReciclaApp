package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Information_plastic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_plastic)

        var btnInfo = this.findViewById<Button>(R.id.btnInfo)

        btnInfo.setOnClickListener{
            val intent = Intent(this@Information_plastic, PlasticActivity::class.java)
            startActivity(intent)
        }
    }
}