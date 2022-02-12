package com.example.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding : FragmentSearchBinding
    private var storyDatas = ArrayList<MyPageStory>()
    var flag = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchChangeIv.setOnClickListener {
            flag = 1
        }
        binding.searchSearchEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){
            } else {
                if(binding.searchSearchEt.text.isNotEmpty()){
                    binding.searchSearchTv.text = "'${binding.searchSearchEt.text.trim()}'와 일치하는 이야기"
                }
                val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
                val token = spf?.getString("accessToken",null)
                Log.d("search2","$token")
                val searchService = SearchService()
                when (flag){
                    0->searchService.searchContents(token!!, binding.searchSearchEt.text.toString().trim())
                    else->searchService.searchTitle(token!!, binding.searchSearchEt.text.toString().trim())
                }


                hideKeyboard(binding.searchSearchEt)
            }
        }
//        storyDatas.apply {
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex2,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex3,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//        }
        val searchStoryRVAdapter = MyPageStoryRVAdapter(storyDatas)
        binding.searchStoryRv.adapter = searchStoryRVAdapter
        val dividerItemDecoration = DividerItemDecoration(binding.searchStoryRv.context, LinearLayoutManager(activity).orientation)
        binding.searchStoryRv.addItemDecoration(dividerItemDecoration)
        return binding.root
    }

    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }
}