package com.siren.docuved_admin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.siren.docuved_admin.R
import com.siren.docuved_admin.adapter.BerkasAdapter
import com.siren.docuved_admin.base.BaseFragment
import com.siren.docuved_admin.model.BerkasResponse

class ExploreFragment : BaseFragment() {

    private lateinit var mContainer: ConstraintLayout

    private lateinit var mSearch: SearchView

    private lateinit var rvSiswa: RecyclerView

    private lateinit var txtNama: TextView
    private lateinit var txtNisn: TextView
    private lateinit var txtJurusan: TextView
    private lateinit var txtTahun: TextView

    private var mBerkasList: ArrayList<BerkasResponse>  = ArrayList()

    private val firebaseRef                             = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_explore, container, false)

        mContainer  = root.findViewById(R.id.content_container)
        mSearch     = root.findViewById(R.id.search)
        txtNama     = root.findViewById(R.id.txt_nama)
        txtNisn     = root.findViewById(R.id.txt_nisn)
        txtJurusan  = root.findViewById(R.id.txt_jurusan)
        txtTahun    = root.findViewById(R.id.txt_tahun)
        rvSiswa     = root.findViewById(R.id.rv_siswa)

        mSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("ben", mSearch.query.toString())
                getDataSiswa()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })


        return root
    }

    private fun getDataSiswa(){

        emptyText()
        mContainer.visibility   = View.GONE

        if(client.isOnline()) {

            mBerkasList.clear()

            firebaseRef.collection("siswa")
                .whereEqualTo("nama", mSearch.query.toString())
                .limit(1)
                .get()
                .addOnSuccessListener { documents ->

                    if(documents.size() > 0){

                        for(document in documents) {

                            txtNama.text    = document.data["nama"].toString()
                            txtNisn.text    = document.data["nisn"].toString()
                            txtJurusan.text = document.data["jurusan"].toString()
                            txtTahun.text   = document.data["tahun"].toString()

                            getDataBerkas(document.id)
                        }
                    }

                    rvSiswa.apply {

                        layoutManager   = LinearLayoutManager(activity)
//                        adapter         = HistoryAdapter(
//                            archiveArrayList,
//                            { partItem: ArchiveResponse -> processDownload(partItem) },
//                            { partItemEmail: ArchiveResponse -> processEmail(partItemEmail) }
//                        )
                    }
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
        }else
            showMessage("No Internet Connection")
    }

    private fun getDataBerkas(id: String){

        firebaseRef.collection("archive")
            .whereEqualTo("user_id", id)
            .get()
            .addOnSuccessListener { documents ->

                mBerkasList.addAll(documents.toObjects(BerkasResponse::class.java))

                rvSiswa.apply {

                    layoutManager   = LinearLayoutManager(activity)
                    adapter         = BerkasAdapter(
                        mBerkasList
                    )
                }

                mContainer.visibility   = View.VISIBLE
            }
            .addOnFailureListener { exception ->

                showMessage(exception.toString())
            }
    }

    private fun emptyText(){

        txtNama.text    = ""
        txtNisn.text    = ""
        txtJurusan.text = ""
        txtTahun.text   = ""
    }
}