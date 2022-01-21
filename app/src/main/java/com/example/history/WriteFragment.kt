package com.example.history

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentWriteBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import android.R
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import androidx.recyclerview.widget.RecyclerView




class WriteFragment : Fragment() {
    lateinit var binding : FragmentWriteBinding
    private var hashtagList = arrayListOf<Hashtag>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(inflater, container, false)
        binding.writeHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        binding.writeHashtagRv.adapter = HashTagRVAdapter(hashtagList)
        binding.writeThemeTv.setOnClickListener {
            if(binding.writeHashtagEt.text.isNotEmpty()){
                addHashTag()
            }
            hideKeyboard(binding.writeHashtagEt)
        }
        binding.writeHashtagEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.writeHashtagEt)
            }
        }

//        binding.writeHashtagEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
//            if(p1){
//
//            } else if (binding.writeHashtagEt.text.isNotEmpty()) {
//
//            } else {
//
//                hideKeyboard(binding.writeHashtagEt)
//            }
//        }

//        auto line change
//        FlexboxLayoutManager(context as MainActivity).apply {
//            flexWrap = FlexWrap.WRAP
//            flexDirection = FlexDirection.ROW
//            justifyContent = JustifyContent.CENTER
//        }
        return binding.root
    }
    private fun addHashTag(){
        val text = binding.writeHashtagEt.text.toString()
        hashtagList.add(Hashtag("#${text}"))
        binding.writeHashtagEt.setText("")
        binding.writeHashtagRv.adapter?.notifyDataSetChanged()

    }
    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

}