package com.example.goingto.ui.profile

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.goingto.R
import kotlinx.android.synthetic.main.profile_fragment.*


class ProfileFragment() : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var image : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel

        btnEdit.setOnClickListener{
            createLoginDialogo()?.show()
        }

    }

    fun createLoginDialogo(): AlertDialog? {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val v: View = inflater.inflate(R.layout.dialog_updatephoto, null)
        builder.setView(v)
        val opt1: Button = v.findViewById<View>(R.id.btnOpt1) as Button
        val opt2: Button = v.findViewById<View>(R.id.btnOpt2) as Button
        val cancel: Button = v.findViewById<View>(R.id.btnExit) as Button
        image = v.findViewById(R.id.image_profile);
        opt1.setOnClickListener(
            View.OnClickListener {
                dispatchTakePictureIntent()
            }
        )
        opt2.setOnClickListener(
            View.OnClickListener {
                cargarImagen()
            }
        )

        return builder.create()
    }
    private fun cargarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/"
        startActivityForResult(Intent.createChooser(intent, "Seleccione la Aplicacion"), 10)
    }
    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            lateinit var packageManager: PackageManager
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val path:Uri = data?.data!! as Uri
            image.setImageURI(path)
        }
    }





}

