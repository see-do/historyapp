package com.example.history

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentWriteBinding
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.service.voice.VoiceInteractionSession
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class WriteFragment : Fragment() {
    lateinit var binding : FragmentWriteBinding
    private var hashtagList = arrayListOf<Hashtag>()
    private var imageList = arrayListOf<Image>()
    private val REQUEST_GET_IMAGE = 105
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(inflater, container, false)
        binding.writeHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.writeHashtagRv.adapter = HashtagRVAdapter(hashtagList, 0)
        var category = ""
        Log.d("category","$category")
        binding.writeCategoryKoreanRb.setOnClickListener {
            category = "korean"
            Log.d("category", "$category")
        }
        binding.writeConfirmBtn.setOnClickListener {
            if(binding.writeTitleEt.text.isEmpty()){

            }
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, HomeFragment())
                .commit()
        }

        binding.writeImgRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.writeImgRv.adapter = ImageRVAdapter(imageList)

        binding.writeTitleEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){
            } else {
                hideKeyboard(binding.writeTitleEt)
            }
        }

        binding.writeHashtagEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){
            } else {
                if(binding.writeHashtagEt.text.isNotEmpty()){
                    addHashTag()
                }
                hideKeyboard(binding.writeHashtagEt)
            }
        }

        binding.writeImgLo.setOnClickListener {
            addImage()
        }

        return binding.root
    }
    // Consider change into flexbox
    private fun addHashTag(){
        //Log.d("공백","${binding.writeHashtagEt.text}")
        val text = binding.writeHashtagEt.text.toString().trim()

        hashtagList.add(Hashtag("#${text}"))
        binding.writeHashtagEt.setText("")
        binding.writeHashtagRv.adapter?.notifyDataSetChanged()
    }

    private fun addImage(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GET_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val uri = data?.data
            if(requestCode == REQUEST_GET_IMAGE){
                val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                imageList.add(Image(bitmap))
                binding.writeImgRv.adapter?.notifyItemInserted(imageList.lastIndex)
            }
        }
    }


    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

}