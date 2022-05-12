package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class information_organic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_organic)
        var btnInfo = this.findViewById<Button>(R.id.button6)

        btnInfo.setOnClickListener{
            val intent = Intent(this@information_organic, organic::class.java)
            startActivity(intent)
        }
    }
}