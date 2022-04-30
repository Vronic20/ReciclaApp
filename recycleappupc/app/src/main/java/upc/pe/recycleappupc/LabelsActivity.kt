package upc.pe.recycleappupc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LabelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labels)
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("labels")
            var textView = this.findViewById<TextView>(R.id.idLabelsTV)
            textView.text = value
        }
    }

}