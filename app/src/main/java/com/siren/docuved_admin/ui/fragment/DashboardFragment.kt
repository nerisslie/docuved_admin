package com.siren.docuved_admin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.firestore.FirebaseFirestore
import com.siren.docuved_admin.R
import com.siren.docuved_admin.base.BaseFragment


class DashboardFragment : BaseFragment() {

    private lateinit var chart: LineChart

    private val firebaseRef = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root    = inflater.inflate(R.layout.fragment_dashboard, container, false)

        chart       = root.findViewById(R.id.chart_view)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: HashMap<Int, Int>  = HashMap()
        val entries                     = ArrayList<Entry>()

        if(client.isOnline()) {

            firebaseRef.collection("archive")
                .get()
                .addOnSuccessListener { documents ->

                    for(document in documents){

                        val year = document["file"].toString().substring(4, 8).toInt()

                        when(val count = data[year]){

                            null    -> data[year]  = 1
                            else    -> data[year]  = count + 1
                        }
                    }

                    for((key, value) in data){

                        entries.add(Entry(key.toFloat(), value.toFloat()))
                    }

                    val dataSet = LineDataSet(entries, "Year")
                    val lineData = LineData(dataSet)
                    chart.setDrawGridBackground(false)
                    chart.data = lineData
                    chart.invalidate()
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
        }else
            showMessage("No Internet Connection")
    }
}