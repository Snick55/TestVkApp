package com.snick.testvkapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snick.testvkapp.presentation.MainActivity
import com.snick.testvkapp.R
import com.snick.testvkapp.databinding.FragmentDetailsBinding
import com.snick.testvkapp.presentation.GifUi

class FragmentDetails: Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var gifUi: GifUi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        gifUi = (requireArguments().getSerializable(KEY_GIF)) as GifUi
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setTitle(R.string.title_details)
        gifUi.show(binding.gif,false)
        binding.title.text = gifUi.title

    }



    companion object{
        fun newInstance(gif: GifUi):FragmentDetails {
            val args = Bundle()
            args.putSerializable(KEY_GIF,gif)
            val fragment = FragmentDetails()
            fragment.arguments = args
            return fragment
        }

        const val KEY_GIF = "GIF"
    }
}