package co.icesi.appinsta

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import co.icesi.appinsta.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var binding1 : FragmentProfileBinding ? = null
    private val  binding get() = binding1!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding1= FragmentProfileBinding.inflate(inflater, container, false)
        binding.editProfile.setOnClickListener{
            binding.accept.visibility= View.VISIBLE

            binding.accept.setOnClickListener{
                binding.accept.visibility= View.INVISIBLE
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    private fun selectPhoto(){
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        startForActivityGallery.launch(intent)
    }

    private val requestPermissionLauncher=registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){granted->
        if(granted){
            selectPhoto()
        }
    }

    private fun requestPermission() {
        when{
            ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED->{
                selectPhoto()
            }
            else->{
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        selectPhoto()

    }

    private val startForActivityGallery=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){rs->
        if(rs.resultCode== Activity.RESULT_OK){
            val photo = rs.data?.data!!
            binding.pp.setImageURI(photo)
        }
    }
}