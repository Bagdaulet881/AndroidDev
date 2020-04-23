package com.example.moviedbapi.main.user


import androidx.appcompat.app.AlertDialog
import android.app.Activity
import android.content.ContentResolver
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.moviedbapi.utilities.RequestConstants
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import com.example.moviedbapi.R
import com.example.moviedbapi.base.ParentFragment
import com.example.moviedbapi.utilities.AppPreferences
import org.koin.android.ext.android.inject
import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class UserFragment : ParentFragment() {
    private val viewModel: UserViewModel by inject()
    private lateinit var progressBar: ProgressBar
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var logout: Button
    private lateinit var ivAvatar: ImageView
    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button
    private var selectedPhotoFile: File? = null

    private lateinit var showText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        Log.d("profile", "Here is an error")
        setData()
    }

    public fun getAvaFromStorage(nameOfAva: String): Uri {
        val storageDirectory = getContext()!!.getExternalFilesDir(null).toString()
        val file = File(storageDirectory, nameOfAva)
        val currentAva = Uri.parse(file.absolutePath)
        return currentAva
    }

    override fun bindViews(view: View) = with(view) {
        //        to delete
        showText = view.findViewById(R.id.showText)
        showText.setText("Click ava to change")
//        te delete
        progressBar = view.findViewById(R.id.progressBarProfile)
        tvName = view.findViewById(R.id.NameValue)
        tvUsername = view.findViewById(R.id.UsernameValue)
        logout = view.findViewById(R.id.logout)
        ivAvatar = findViewById(R.id.ivAvatar)
        buttonCamera = findViewById(R.id.buttonCamera)
        buttonGallery = findViewById(R.id.buttonGallery)
        ivAvatar.setImageURI(getAvaFromStorage(saveOrGetAvaName("current", 2)))

        logout.setOnClickListener() {
            Toast.makeText(context, "You are Logged Out", Toast.LENGTH_SHORT).show()
            val i: Intent? = getActivity()!!.getPackageManager()
                .getLaunchIntentForPackage(getActivity()!!.getPackageName())
            i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
        }

        ivAvatar.setOnClickListener {
            getPermissions()
        }

        buttonCamera.setOnClickListener {
            getPermissionsForCamera()
        }

        buttonGallery.setOnClickListener {
            getPermissionForGallery()
        }
    }

    override fun setData() {
        val sessionId = AppPreferences.getSessionId(activity?.applicationContext!!)
        sessionId?.let {
            viewModel.getAccountDetails(sessionId)
        }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is UserViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is UserViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is UserViewModel.State.Result -> {
                    tvName.text = result.account?.username
                    tvUsername.text = ("MEGOGO ID: " + result.account?.id.toString())

                }
                is UserViewModel.State.Error -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
                is UserViewModel.State.IntError -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    public fun showSavedAvatars(): ArrayList<String> {
        val externalStorage = Environment.getExternalStorageState()
        val out = ArrayList<String>()
        if (externalStorage.equals(Environment.MEDIA_MOUNTED)) {
            val storageDirectory = getContext()!!.getExternalFilesDir(null).toString()
            val drc = File(storageDirectory)
            val files = drc.listFiles()
            for (file in files) {
                if (file.extension == "jpg") {
                    out.add(file.name)
                }
            }
            return out
        } else return out
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RequestConstants.CAMERA) {
                selectedPhotoFile?.let { file ->
                    val myBitmap = BitmapFactory.decodeFile(file!!.getAbsolutePath())
                    ivAvatar.setImageBitmap(myBitmap)
                    saveImageToStorage(myBitmap)
                }
            } else if (requestCode == RequestConstants.GALLERY) {
                val image = data?.data
                ivAvatar.setImageURI(image)
                var temp = ivAvatar.drawable
                var myBitmap = (temp as BitmapDrawable).bitmap
                saveImageToStorage(myBitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RequestConstants.AVATAR_CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
            return
        } else if (requestCode == RequestConstants.AVATAR_GALLERY_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
            return
        } else if (requestCode == RequestConstants.AVATAR_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageChooserDialog()
            }
        }
    }

    public fun saveImageToStorage(btmp: Bitmap) {
        val externalStorage = Environment.getExternalStorageState()
        if (externalStorage.equals(Environment.MEDIA_MOUNTED)) {
            val storageDirectory = getContext()!!.getExternalFilesDir(null).toString()
            val newName = generateAvaName()
            val file = File(storageDirectory, newName)
            try {
                val stream: OutputStream = FileOutputStream(file)
                val myBitmap = btmp
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.flush()
                stream.close()
                Toast.makeText(
                    getContext(),
                    "New ava saved " + saveOrGetAvaName(newName, 1),
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            Toast.makeText(getContext(), "no access to storage", Toast.LENGTH_SHORT).show()

        }
    }

    public fun saveOrGetAvaName(newAvaName: String, op: Number): String {
        val memoryName = "currentAva.txt"
        var file = File(getContext()!!.getExternalFilesDir(null).toString(), memoryName)
        val isNewFileCreated: Boolean = file.createNewFile()
        if (op.equals(1)) {
            file.writeText("")
            file.writeText(newAvaName)
            return newAvaName
        } else {
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            return inputString
        }
    }

    public fun generateAvaName(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_" + ".jpg"
        return imageFileName
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val imageFile = File.createTempFile("avatar", ".jpg", context?.cacheDir)
        val avatarUri = this.context?.let {
            FileProvider.getUriForFile(
                it,
                "${context?.packageName}.provider",
                imageFile
            )
        }
        selectedPhotoFile = imageFile
        intent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri)
        startActivityForResult(intent, RequestConstants.CAMERA)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(intent, RequestConstants.GALLERY)
    }

    private fun getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraGranted = context?.let {
                ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA)
            } == PackageManager.PERMISSION_GRANTED
            val galleryGranted = ContextCompat.checkSelfPermission(
                getContext()!!,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            val writeGranted = ContextCompat.checkSelfPermission(
                getContext()!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            if (cameraGranted && galleryGranted && writeGranted) {
                imageChooserDialog()
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    RequestConstants.AVATAR_PERMISSION_REQUEST
                )
            }
        } else {
            imageChooserDialog()
        }
    }

    private fun getPermissionsForCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraGranted = ContextCompat.checkSelfPermission(
                getContext()!!,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            if (cameraGranted) {
                openCamera()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    RequestConstants.AVATAR_CAMERA_PERMISSION_REQUEST
                )
            }
        } else {
            openCamera()
        }
    }

    private fun getPermissionForGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val galleryGranted = ContextCompat.checkSelfPermission(
                getContext()!!,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            if (galleryGranted) {
                openGallery()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    RequestConstants.AVATAR_GALLERY_PERMISSION_REQUEST
                )
            }
        } else {
            openGallery()
        }
    }

    private fun imageChooserDialog() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(getContext()!!, android.R.layout.simple_list_item_1)
        adapter.add("Camera")
        adapter.add("Gallery")
        adapter.add("Saved Avatars")
        AlertDialog.Builder(getContext()!!)
            .setTitle("Change avatar")
            .setAdapter(adapter) { dialog, which ->
                if (which == 0) {
                    openCamera()
                } else
                    if (which == 1) {
                        openGallery()
                    } else {
                        oldAvatarChooser()
                    }
            }
            .create()
            .show()
    }

    private fun oldAvatarChooser() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(getContext()!!, android.R.layout.simple_list_item_1)
        adapter.addAll(showSavedAvatars())

        AlertDialog.Builder(getContext()!!)
            .setTitle("Change avatar")
            .setAdapter(adapter) { dialog, which ->
                saveOrGetAvaName(showSavedAvatars().get(which), 1)
                ivAvatar.setImageURI(getAvaFromStorage(saveOrGetAvaName("current", 2)))
                Toast.makeText(context, "Current Ava changed", Toast.LENGTH_SHORT).show()


            }
            .create()
            .show()
    }
}
