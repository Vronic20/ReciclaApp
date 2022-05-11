package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class information_tetrapck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_tetrapck)
        var btnInfo = this.findViewById<Button>(R.id.button5)

        btnInfo.setOnClickListener{
            val intent = Intent(this@information_tetrapck, tetrapack::class.java)
            startActivity(intent)
        }
    }
}