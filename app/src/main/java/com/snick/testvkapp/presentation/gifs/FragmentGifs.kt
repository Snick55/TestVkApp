package com.snick.testvkapp.presentation.gifs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snick.testvkapp.presentation.MainActivity
import com.snick.testvkapp.R
import com.snick.testvkapp.core.App
import com.snick.testvkapp.core.appComponent
import com.snick.testvkapp.databinding.FragmentGifsBinding
import com.snick.testvkapp.presentation.GifUi
import com.snick.testvkapp.presentation.GifsUi
import com.snick.testvkapp.presentation.details.FragmentDetails
import javax.inject.Inject

class FragmentGifs : Fragment(), GifsAdapter.Listener {

    private lateinit var binding: FragmentGifsBinding
    private var lastQuery = ""

    @Inject
    lateinit var viewModel: GifsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
        lastQuery = viewModel.readQuery()
        viewModel.fetchGifs(lastQuery)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGifsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setTitle(R.string.title_gifs)
        val adapter = GifsAdapter(this)
        setupLayoutManager(binding, adapter)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null || query.isBlank()) {

                    return true
                }
                else {
                    viewModel.currentQuery = query
                    binding.recyclerGifs.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                    viewModel.saveQuery(query)
                    viewModel.fetchGifs(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lastQuery = newText?:""
                return false
            }
        })


        viewModel.gifs.observe(viewLifecycleOwner) {
            render(
                it,
                binding.recyclerGifs,
                binding.errorContainer,
                binding.progress,
                binding.errorTextview,
                adapter
            )
        }

        binding.tryAgainBtn.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            binding.errorContainer.visibility = View.INVISIBLE
            viewModel.fetchGifs(lastQuery)
        }
    }

    override fun handle(gif: GifUi) {
        val fragment = FragmentDetails.newInstance(gif)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null).commit()
    }

    private fun setupLayoutManager(binding: FragmentGifsBinding, adapter: GifsAdapter) {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.root.width
                val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)
                val columns = width / itemWidth
                binding.recyclerGifs.adapter = adapter
                binding.recyclerGifs.layoutManager = GridLayoutManager(requireContext(), columns)
            }
        })
    }

    private fun render(
        gifsUi: GifsUi,
        recyclerGifs: RecyclerView,
        errorContainer: LinearLayout,
        progress: ProgressBar,
        errorTextview: TextView,
        adapter: GifsAdapter
    ) {
        when (gifsUi) {
            is GifsUi.Gifs -> {
                recyclerGifs.visibility = View.VISIBLE
                errorContainer.visibility = View.GONE
                progress.visibility = View.GONE
                adapter.setUp(gifsUi.gifs)
                recyclerGifs.scrollToPosition(0)
            }
            is GifsUi.Fail -> {
                recyclerGifs.visibility = View.GONE
                progress.visibility = View.GONE
                errorContainer.visibility = View.VISIBLE
                gifsUi.handle(errorTextview)
            }
            else -> {
                recyclerGifs.visibility = View.GONE
                progress.visibility = View.VISIBLE
            }
        }
    }
}





