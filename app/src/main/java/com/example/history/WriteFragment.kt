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
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import retrofit2.Retrofit


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
                    //val storyService = StoryService()
                    //storyService.writeStory()
//                    val storyService = StoryService()
//                    storyService.writeStory()

//                    val path = getRealPathFromURI(requireContext(), urii)
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

//    fun getImageUri(inContext: Context?, inImage: Bitmap?): Uri? {
//        val bytes = ByteArrayOutputStream()
//        if (inImage != null) {
//            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        }
//        val path = MediaStore.Images.Media.insertImage(inContext?.getContentResolver(), inImage, "Title" + " - " + Calendar.getInstance().getTime(), null)
//        return Uri.parse(path)
//    }
//
//
//    fun getRealPathFromURI(context: Context?, uri: Uri?): String? {
//
//        // DocumentProvider
//        if (DocumentsContract.isDocumentUri(context, uri)) {
//
//            if (isExternalStorageDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                return if ("primary".equals(type, ignoreCase = true)) {
//                    (Environment.getExternalStorageDirectory().toString() + "/"
//                            + split[1])
//                } else {
//                    val SDcardpath = getRemovableSDCardPath(context)?.split("/Android".toRegex())!!.toTypedArray()[0]
//                    SDcardpath + "/" + split[1]
//                }
//            } else if (isDownloadsDocument(uri)) {
//                val id = DocumentsContract.getDocumentId(uri)
//                val contentUri = ContentUris.withAppendedId(
//                    Uri.parse("content://downloads/public_downloads"),
//                    java.lang.Long.valueOf(id))
//                return getDataColumn(context, contentUri, null, null)
//            } else if (isMediaDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                var contentUri: Uri? = null
//                if ("image" == type) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                } else if ("video" == type) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                } else if ("audio" == type) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                }
//                val selection = "_id=?"
//                val selectionArgs = arrayOf(split[1])
//                return getDataColumn(context, contentUri, selection,
//                    selectionArgs)
//            }
//        } else if (uri != null) {
//            if ("content".equals(uri.getScheme(), ignoreCase = true)) {
//                // Return the remote address
//                return if (isGooglePhotosUri(uri)) uri.getLastPathSegment() else getDataColumn(context, uri, null, null)
//            } else if ("file".equals(uri.getScheme(), ignoreCase = true)) {
//                return uri.getPath()
//            }
//        }
//        return null
//    }

}