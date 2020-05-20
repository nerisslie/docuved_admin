package com.siren.docuved

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import java.util.*

class DashboardAdmin : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chart)
        val anyChartView: AnyChartView = findViewById(R.id.chart_view)
        val cartesian: Cartesian = AnyChart.column()
        val data: MutableList<DataEntry> = ArrayList<DataEntry>()
        data.add(ValueDataEntry(0, 25))
        data.add(ValueDataEntry(50, 50))
        data.add(ValueDataEntry(100, 120))
        data.add(ValueDataEntry(150, 190))
        data.add(ValueDataEntry(200, 230))
        data.add(ValueDataEntry(250, 300))
        data.add(ValueDataEntry(300, 140))
        data.add(ValueDataEntry(350, 25))
        val column: Column = cartesian.column(data)
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("\${%Value}{groupsSeparator: }")
        cartesian.animation(true)
        cartesian.yScale().minimum(0.0)
        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)
        anyChartView.setChart(cartesian)
    }
}