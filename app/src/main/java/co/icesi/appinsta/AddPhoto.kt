package co.icesi.appinsta

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import co.icesi.appinsta.databinding.FragmentAddPhotoBinding

class AddPhoto : Fragment() {
    private var binding1 : FragmentAddPhotoBinding ? = null
    private val binding get() = binding1!!

    var listener: OnNewPostListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding1 = FragmentAddPhotoBinding.inflate(inflater, container, false)

        val places  = arrayOf(
            "Calarcá",
            "Aldea del Sonido",
            "Valle de la muerte",
            "Villavicencio",
            "Pitalio",
            "Barrancabermeja",
            "Cali",
            "Rioacha",
            "Donde tu no llegas"
        )

        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            places
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                }
                return view
            }
        }

        binding.city.adapter = adapter

        binding.city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                identificaction: Long
            ) {
                val value = parent!!.getItemAtPosition(position).toString()
                if (value == places[0]) {
                    (view as TextView).setTextColor(Color.GRAY)
                }
            }

        }

        binding.gallery.setOnClickListener {
            requestPermission()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

        binding.camera.setOnClickListener {
            val action = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            launcher.launch(action)
        }

        binding.publishPost.setOnClickListener {

        }
        return binding.root
    }

    fun onResult(result: ActivityResult) {
        val map = result.data?.extras?.get("data") as Bitmap
        binding.galleryCollection.setImageBitmap(map)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddPhoto()
    }

    private fun selectPhoto() {
        val action = Intent(Intent.ACTION_GET_CONTENT)
        action.type = "image/*"
        startForActivityGallery.launch(action)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            selectPhoto()
        }
    }

    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                selectPhoto()
            }
            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        selectPhoto()
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { rs ->
        if (rs.resultCode == Activity.RESULT_OK) {
            val photo = rs.data?.data!!
            binding.galleryCollection.setImageURI(photo) //imageview
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding1 = null
    }

    interface OnNewPostListener {
        fun onNewPost(post: Post)
    }
}