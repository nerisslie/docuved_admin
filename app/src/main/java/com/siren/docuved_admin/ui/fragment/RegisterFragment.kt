package com.siren.docuved_admin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.siren.docuved_admin.R
import com.siren.docuved_admin.base.BaseFragment

class RegisterFragment : BaseFragment() {

    private lateinit var spinner: Spinner
    private lateinit var spinnerJurusan: Spinner

    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var spinnerJurusanAdapter: SpinnerAdapter

    private lateinit var mNisn: EditText
    private lateinit var mNama: EditText
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText

    private lateinit var btnSave: Button

    private var firebaseRef     = FirebaseFirestore.getInstance()
    private var firebaseAuth    = Firebase.auth

    private val tahunArray      = arrayOf(
        "2017",
        "2018",
        "2019",
        "2020"
    )
    private val jurusanArray    = arrayOf(
        "IPA",
        "IPS"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root                = inflater.inflate(R.layout.fragment_register, container, false)

        spinner                 = root.findViewById(R.id.tahun)
        spinnerJurusan          = root.findViewById(R.id.jurusan)
        mNisn                   = root.findViewById(R.id.nisn)
        mNama                   = root.findViewById(R.id.nama)
        mEmail                  = root.findViewById(R.id.email)
        mPassword               = root.findViewById(R.id.password)
        btnSave                 = root.findViewById(R.id.btn_save)

        spinner.adapter         = spinnerAdapter
        spinnerJurusan.adapter  = spinnerJurusanAdapter

        spinner.dropDownVerticalOffset
        spinnerJurusan.dropDownVerticalOffset

        btnSave.setOnClickListener {

            validateRegister()
        }

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        spinnerAdapter          = ArrayAdapter(
            context,
            R.layout.spinner,
            tahunArray
        )

        spinnerJurusanAdapter   = ArrayAdapter(
            context,
            R.layout.spinner,
            jurusanArray
        )
    }

    private fun validateRegister(){

        when{
            isEmpty(mNama)          ->  mNama.error         = "Nama siswa dibutuhkan"
            isEmpty(mNisn)          ->  mNisn.error         = "Nomor induk siswa dibutuhkan"
            isEmpty(mEmail)         ->  mEmail.error        = "Email siswa dibutuhkan"
            isEmpty(mPassword)      ->  mPassword.error     = "Password siswa dibutuhkan"
            client.isOnline()       ->  validateNisn()
            else                    ->  showMessage("Tidak ada koneksi internet")
        }
    }

    private fun validateNisn(){

        loadingDialog.show()

        firebaseRef.collection("siswa")
            .whereEqualTo("nisn", mNisn.text.toString())
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->

                if(documents.size() > 0) {

                    loadingDialog.dismiss()
                    showMessage("Nomor induk siswa sudah terdaftar")
                }else{

                    processRegister()
                }
            }
            .addOnFailureListener { exception ->

                showMessage(exception.toString())
            }
    }

    private fun processRegister(){

        firebaseAuth.createUserWithEmailAndPassword(mEmail.text.toString(), mPassword.text.toString())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    setProfile()
                } else {

                    showMessage("Authentication Failed : ${task.exception}")
                    loadingDialog.dismiss()
                }
            }
    }

    private fun setProfile(){

        if(firebaseAuth.uid != null){

            firebaseRef.collection("siswa").document(firebaseAuth.uid.toString())
                .set(hashMapOf(
                    "nisn" to mNisn.text.toString(),
                    "nama" to mNama.text.toString(),
                    "tahun" to spinner.selectedItem.toString(),
                    "jurusan" to spinnerJurusan.selectedItem.toString()
                )).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        showMessage("Registration Success")
                        loadingDialog.dismiss()
                    }else{

                        showMessage(task.exception.toString())
                        loadingDialog.dismiss()
                    }

                    firebaseAuth.signOut()
                }
        }else {

            showMessage("Something went wrong, please try again")
            loadingDialog.dismiss()
        }
    }
}
