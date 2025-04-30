package com.example.magicgallery

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magicgallery.databinding.FragmentMagicGalleryBinding
import kotlinx.coroutines.launch

private const val TAG = "MagicGalleryFragment"

class MagicGalleryFragment: Fragment() {
    private var _binding: FragmentMagicGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot get binding because it is null. Is the view visible?"
        }

    private val magicGalleryViewModel: MagicGalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentMagicGalleryBinding.inflate(inflater, container, false)
        binding.magicGrid.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                magicGalleryViewModel.galleryItems.collect { items ->
                    Log.d(TAG, "Response received: $items")
                    binding.magicGrid.adapter = MagicListAdapter(items)
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_magic_gallery, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_howto -> {
                showHowTo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showHowTo() {
        viewLifecycleOwner.lifecycleScope.launch {
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("magic.wizards.com")
                .appendPath("en")
                .appendPath("how-to-play")
            val howto = builder.build()
            findNavController().navigate(
                MagicGalleryFragmentDirections.showHowto(howto)
            )
        }

    }

}