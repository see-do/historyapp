package com.example.history

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentWriteBinding
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class WriteFragment : Fragment() {
    lateinit var binding : FragmentWriteBinding
    private var hashtagList = arrayListOf<Hashtag>()
    private var imageList = arrayListOf<Image>()
    private val REQUEST_GET_IMAGE = 105
    private var uriList = arrayListOf<Uri>()
    private var pathList = arrayListOf<MultipartBody.Part?>()
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
        binding.writeCategoryRb.setOnCheckedChangeListener { _, id ->
            when (id){
                R.id.write_category_korean_rb -> category = "korean"
                R.id.write_category_western_rb -> category = "western"
                R.id.write_category_oriental_rb -> category = "oriental"
                R.id.write_category_etc_rb -> category = "etc"
            }
            hideWarning(binding.writeCategoryWarningIv,binding.writeCategoryWarningTv)
        }

        binding.writeConfirmBtn.setOnClickListener {
            when{
                binding.writeTitleEt.text.isEmpty() -> showWarning(binding.writeTitleWarningIv, binding.writeTitleWarningTv)
                category == "" -> showWarning(binding.writeCategoryWarningIv, binding.writeCategoryWarningTv)
                else -> {

                        Log.d("pathFind","$uriList")
                        val file = File(absolutePath(uriList[0]))
                        Log.d("pathFind_List","$file")
                        val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
                        val body =
                            MultipartBody.Part.createFormData("image", file.name, requestFile)
                        pathList.add(body)

                        Log.d("pathFind_List","$pathList")


                    Log.d("pathFind","$pathList")
                    val storyService = StoryService()
                    storyService.writeStory(pathList)


                    Log.d("category", "$category")
                    (context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, HomeFragment())
                        .commit()
                }

            }
        }

        binding.writeImgRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.writeImgRv.adapter = ImageRVAdapter(imageList)

        binding.writeTitleEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
            if(p1){
            } else {
                hideWarning(binding.writeTitleWarningIv,binding.writeTitleWarningTv)
                hideKeyboard(binding.writeTitleEt)
            }
        }

        binding.writeHashtagEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
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
                uriList.add(uri!!)
                Log.d("pathFind","$uriList")
                binding.writeImgRv.adapter?.notifyItemInserted(imageList.lastIndex)
            }
        }
    }


    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(iv : ImageView, tv: TextView){
        iv.visibility = View.VISIBLE
        tv.visibility = View.VISIBLE
    }
    private fun hideWarning(iv : ImageView, tv: TextView){
        iv.visibility = View.GONE
        tv.visibility = View.GONE
    }

    fun absolutePath(path: Uri) : String? {
        val context = requireContext()
        val contentResolver = context.contentResolver ?: return null

        val filePath = (context.applicationInfo.dataDir + File.separator + System.currentTimeMillis())
        val file = File(filePath)
        try{
            val inputStream = contentResolver.openInputStream(path) ?: return null
            val outputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len : Int
            while(inputStream.read(buf).also {len = it} > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (e : IOException){
            e.printStackTrace()
        }
        return file.absolutePath
    }

}