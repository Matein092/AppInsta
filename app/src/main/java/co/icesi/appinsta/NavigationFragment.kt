package co.icesi.appinsta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.icesi.appinsta.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {

    private var binding1: FragmentNavigationBinding? = null
    private val binding get() = binding1!!

    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding1 = FragmentNavigationBinding.inflate(inflater, container, false)
        val recycler = binding.recycler
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NavigationFragment()
    }
}