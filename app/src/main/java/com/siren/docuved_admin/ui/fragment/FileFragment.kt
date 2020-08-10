package com.siren.docuved_admin.ui.fragment

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.siren.docuved_admin.R
import com.siren.docuved_admin.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class FileFragment : BaseFragment() {

    private lateinit var spinnerJenisAdapter: SpinnerAdapter
    private lateinit var spinnerKelasAdapter: SpinnerAdapter

    private lateinit var mJenisBerkas: Spinner
    private lateinit var mKelas: Spinner

    private lateinit var mUnggahBerkas: TextView

    private lateinit var mNisn: EditText
    private lateinit var mNoBerkas: EditText
    private lateinit var mKeterangan: EditText

    private lateinit var btnUpload: Button
    private lateinit var btnSubmit: Button

    private val firebaseRef         = FirebaseFirestore.getInstance()
    private var storageRef          = Firebase.storage.reference
    private var filePath: String?   = ""
    private var localeID            = Locale("in", "ID")

    private var currentTime: String = SimpleDateFormat(
        "ddMMyyyyHHmmss",
        localeID
    ).format(Date())

    private val jenisArray  = arrayOf(
        "Ijazah",
        "Raport"
    )
    private val kelasArray= arrayOf(
        "10",
        "11",
        12
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)

        spinnerJenisAdapter = ArrayAdapter(
            context,
            R.layout.spinner,
            jenisArray
        )

        spinnerKelasAdapter = ArrayAdapter(
            context,
            R.layout.spinner,
            kelasArray
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root            = inflater.inflate(R.layout.fragment_file, container, false)

        mUnggahBerkas       = root.findViewById(R.id.unggahberkas)
        mJenisBerkas        = root.findViewById(R.id.jenisberkas)
        mKelas              = root.findViewById(R.id.kelas)
        mNisn               = root.findViewById(R.id.nisn)
        mNoBerkas           = root.findViewById(R.id.noberkas)
        mKeterangan         = root.findViewById(R.id.keterangan)
        btnUpload           = root.findViewById(R.id.btn_upload)
        btnSubmit           = root.findViewById(R.id.btn_submit)

        mJenisBerkas.adapter    = spinnerJenisAdapter
        mKelas.adapter          = spinnerKelasAdapter

        btnUpload.setOnClickListener {

            chooseImage()
        }

        btnSubmit.setOnClickListener {

            currentTime = SimpleDateFormat(
                "ddMMyyyyHHmmss",
                localeID
            ).format(Date())
            validateUpload()
        }

        return root
    }

    private fun chooseImage(){

        Intent(Intent.ACTION_PICK).run {

            this.type = "image/*"
            startActivityForResult(

                Intent.createChooser(this, "Select a File to Upload"), 0
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){

            0   ->  {

                if(resultCode == AppCompatActivity.RESULT_OK){

                    val uri     = data?.data

                    filePath    = when(uri){

                        null    -> ""
                        else    -> getPathKitkatPlus(uri)
                    }

                    mUnggahBerkas.text  = File(filePath.toString()).name
                }
            }
        }
    }

    private fun validateUpload(){

        launch(Dispatchers.Main) {

            if(client.isOnline()) {

                loadingDialog.show()

                when {

                    isEmpty(mNisn)              -> mNisn.error      = "Silahkan memasukkan nomor induk siswa"
                    filePath.isNullOrEmpty()    -> btnUpload.error  = "Belum melakukan upload file"
                    else                        -> validateNisn()
                }
            }else
                showMessage("Tidak ada koneksi internet")
        }
    }

    private fun validateNisn(){

        firebaseRef.collection("siswa")
            .whereEqualTo("nisn", mNisn.text.toString())
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->

                if(documents.size() > 0) {

                    for (document in documents) {
                        uploadFile(document.id)
                    }
                }else{

                    loadingDialog.dismiss()
                    showMessage("Nomor induk siswa tidak terdaftar")
                }
            }
            .addOnFailureListener { exception ->

                showMessage(exception.toString())
            }
    }

    private fun uploadFile(userID: String){

        val file            = File(filePath.toString())
        val fileUri         = Uri.fromFile(file)
        val orBitMap        = BitmapFactory.decodeFile(filePath)
        val size            = ((orBitMap.width + orBitMap.height) / 2) / 5
        val bitMap          = mark(orBitMap, "DOCUVED", Point(0,(orBitMap.height*0.5).toInt()), 100, size, false)
        val baos = ByteArrayOutputStream()
        bitMap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = storageRef
            .child("images/${currentTime + "." + file.extension}")
            .putBytes(data)
            .addOnFailureListener {

                loadingDialog.dismiss()
                showMessage(it.toString())
            }.addOnSuccessListener {

                processUpload(userID, file)
            }

//        storageRef
//            .child("images/${currentTime + "." + file.extension}")
//            .putFile(fileUri)
//            .addOnFailureListener {
//
//                loadingDialog.dismiss()
//                showMessage(it.toString())
//            }.addOnSuccessListener {
//
//                processUpload(userID, file)
//            }
    }

    private fun processUpload(userID: String, file: File){

        firebaseRef.collection("archive")
            .add(
                hashMapOf(
                    "file" to currentTime + "." + file.extension,
                    "nomor_berkas" to mNoBerkas.text.toString(),
                    "name" to ("${mJenisBerkas.selectedItem} ${mKelas.selectedItem}"),
                    "type" to mJenisBerkas.selectedItem,
                    "keterangan" to mKeterangan.text.toString(),
                    "user_id" to userID
                )
            )
            .addOnSuccessListener {

                loadingDialog.dismiss()
                showMessage("Success Upload")
            }
            .addOnFailureListener { exception ->

                loadingDialog.dismiss()
                showMessage(exception.toString())
            }
    }

    private fun getPathKitkatPlus(uri: Uri): String? {

        when {

            DocumentsContract.isDocumentUri(activity?.applicationContext, uri) -> {

                val docId = DocumentsContract.getDocumentId(uri)

                when {

                    uri.isExternalStorageDocument -> {

                        val parts = docId.split(":")

                        if ("primary".equals(parts[0], true))
                            return "${Environment.getExternalStorageDirectory()}/${parts[1]}"
                    }

                    uri.isDownloadsDocument -> {

                        val contentUri = ContentUris.withAppendedId(

                            Uri.parse("content://downloads.documents/document/"),
                            docId.toLong()
                        )

                        return getDataColumn(contentUri, null, null)
                    }

                    uri.isMediaDocument -> {

                        val parts = docId.split(":")

                        val contentUri = when (parts[0].toLowerCase()) {

                            "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                            "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                            else -> return null
                        }

                        return getDataColumn(contentUri, "_id=?", arrayOf(parts[1]))
                    }
                }
            }

            "content".equals(uri.scheme, true) -> {

                return if (uri.isGooglePhotosUri)
                    uri.lastPathSegment
                else
                    getDataColumn(uri, null, null)
            }

            "file".equals(uri.scheme, true) -> {

                return uri.path
            }
        }

        return null
    }

    private fun getDataColumn(uri: Uri, selection: String?, args: Array<String>?): String? {

        activity?.contentResolver?.query(uri, arrayOf("_data"), selection, args, null)?.use {

            if (it.moveToFirst())
                return it.getString(it.getColumnIndexOrThrow("_data"))
        }

        return null
    }

    private fun mark(
        src: Bitmap,
        watermark: String?,
        location: Point,
        alpha: Int,
        size: Int,
        underline: Boolean
    ): Bitmap? {

        val w = src.width
        val h = src.height
        val result = Bitmap.createBitmap(w, h, src.config)
        val canvas = Canvas(result)
        val paint = Paint()

        canvas.drawBitmap(src, 0F, 0F, null)

        paint.color             = Color.BLACK
        paint.alpha             = alpha
        paint.textSize          = size.toFloat()
        paint.isAntiAlias       = true
        paint.isUnderlineText   = underline

        if(watermark != null )
            canvas.drawText(watermark, location.x.toFloat(), location.y.toFloat(), paint)

        return result
    }

    private val Uri.isExternalStorageDocument: Boolean
        get() = authority == "com.android.externalstorage.documents"

    private val Uri.isDownloadsDocument: Boolean
        get() = authority == "com.android.providers.downloads.documents"

    private val Uri.isMediaDocument: Boolean
        get() = authority == "com.android.providers.media.documents"

    private val Uri.isGooglePhotosUri: Boolean
        get() = authority == "com.google.android.apps.photos.content"
}