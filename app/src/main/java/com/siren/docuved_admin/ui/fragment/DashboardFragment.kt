package com.siren.docuved_admin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anychart.anychart.*
import com.siren.docuved_admin.R
import java.util.ArrayList

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val anyChartView: AnyChartView = root.findViewById(R.id.chart_view)

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
        val column: CartesianSeriesColumn = cartesian.column(data)

        column.tooltip
            .setTitleFormat("{%X")
            .setPosition(Position.CENTER_BOTTOM)
            .setAnchor(EnumsAnchor.CENTER_BOTTOM)
            .setOffsetX(0.0)
            .setOffsetY(5.0)
            .setFormat("\${%Value}{groupsSeparator: }")

        cartesian.setAnimation(true)
        cartesian.setTitle("Data Berkas Tahun 2019/2020")

        cartesian.setYScale("0.0")

        cartesian.setXScale("0").labels.setFormat("\${%Value}{groupsSeparator: }")

        cartesian.tooltip.setPositionMode(TooltipPositionMode.POINT)
        cartesian.interactivity.setHoverMode(HoverMode.BY_X)

        cartesian.setXAxis("0").setTitle("Product")
        cartesian.setYAxis("0").setTitle("Revenue")

        anyChartView.setChart(cartesian)
        return root
    }
}