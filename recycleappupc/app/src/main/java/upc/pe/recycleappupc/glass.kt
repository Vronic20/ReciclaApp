package upc.pe.recycleappupc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.ms.square.android.expandabletextview.ExpandableTextView

class glass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glass)
        var btnShare = this.findViewById<ImageView>(R.id.btnShare3)
        btnShare.setOnClickListener{
            var shareBody = "Download RecycleApp on Play Store: Link"
            var shareSub = "Test";

            var shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

            startActivity(shareIntent)
        }


        val expTv = findViewById<ru.embersoft.expandabletextview.ExpandableTextView>(R.id.expandableTextView)
        expTv.setText("¿Cuál es el impacto ambiental del vidrio? \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")


    }
}