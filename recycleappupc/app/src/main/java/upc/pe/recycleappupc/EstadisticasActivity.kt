package upc.pe.recycleappupc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView
import upc.pe.recycleappupc.databinding.ActivityEstadisticasBinding


class EstadisticasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstadisticasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)
        showBarChart()

        val homeBottomNav = findViewById<BottomNavigationView>(R.id.home_buttom_navigation)
        homeBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ItemReconocer -> {
                    startActivity(Intent(baseContext,CameraActivity::class.java))
                    true
                }
                R.id.ItemGreenPoints -> {
                    startActivity(Intent(baseContext,puntos_verdes::class.java))
                    true
                }
                R.id.ItemStatistics -> {
                    startActivity(Intent(baseContext,EstadisticasActivity::class.java))
                    true
                }
                else -> {false}
            }
        }
    }


    private fun showBarChart() {
        val valueList = ArrayList<Double>()
        val xAxisLabel = ArrayList<String>()
        val entries: ArrayList<BarEntry> = ArrayList()
        val title = "Productos reciclables!"
        var barChart = this.findViewById<BarChart>(R.id.barChart_view)

        //input data
            valueList.add(3.1)
            valueList.add(9.1)
            valueList.add(1.1)
            valueList.add(5.1)
            valueList.add(5.1)

        // input label
            xAxisLabel.add("Metal")
            xAxisLabel.add("Vidrio")
            xAxisLabel.add("Orgánico")
            xAxisLabel.add("Papel")
            xAxisLabel.add("Plástico")


        //fit the data into a bar
        for (i in 0 until valueList.size) {
            val barEntry = BarEntry(i.toFloat(), valueList[i].toFloat())
            entries.add(barEntry)
        }


        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        barChart.setData(data)
        val xAxis = barChart.getXAxis()
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        barChart.axisRight.isEnabled = false
        barChart.legend.isEnabled = true
        barChart.description.isEnabled = false
        barChart.animateY(3000)

        xAxis.setGranularity(1f)
        xAxis.setGranularityEnabled(true)
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 9f

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setValueFormatter(IndexAxisValueFormatter(xAxisLabel))

        barChart.invalidate()
    }


}