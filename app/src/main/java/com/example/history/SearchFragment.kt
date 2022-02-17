package com.example.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.history.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), SearchView, OneStoryView {
    lateinit var binding : FragmentSearchBinding
    private var storyDatas = ArrayList<Body>()
    var flag = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchChangeIv.setOnClickListener {
            flag = when (flag){
                0 -> 1
                else -> 0
            }
            Log.d("searchTitle","change")
        }

        binding.searchSearchEt.setOnEditorActionListener(object : android.widget.TextView.OnEditorActionListener{
            override fun onEditorAction(p0: android.widget.TextView?, p1: Int, p2: android.view.KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH){
                    searchTitle()
                    hideKeyboard(binding.searchSearchEt)
                    return true
                }
                return false
            }
        })

        val dividerItemDecoration = DividerItemDecoration(binding.searchStoryRv.context, LinearLayoutManager(activity).orientation)
        binding.searchStoryRv.addItemDecoration(dividerItemDecoration)
        return binding.root
    }

    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    override fun onStoryFailure() {
        TODO("Not yet implemented")
    }

    override fun onStoryLoading() {
        TODO("Not yet implemented")
    }

    override fun onStorySuccess(status: String, body: OneStory?) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, StoryDetailFragment(body!!)).commitAllowingStateLoss()
    }

    override fun onSearchFailure() {
        TODO("Not yet implemented")
    }

    override fun onSearchLoading() {
        TODO("Not yet implemented")
    }

    override fun onSearchSuccess(searchBody : List<Body>?) {
        Log.d("searchBody","$searchBody")
        if(searchBody!!.isNotEmpty()){
            storyDatas.clear()
            for(story in searchBody){
                storyDatas.add(story)
            }
            binding.searchStoryRv.visibility = View.VISIBLE
            binding.searchNoneTv.visibility = View.GONE
            val searchStoryRVAdapter = SearchStoryRVAdapter(storyDatas)
            binding.searchStoryRv.adapter = searchStoryRVAdapter
            searchStoryRVAdapter.searchItemClickListener(object : SearchStoryRVAdapter.SearchItemClickListener{
                override fun onItemClick(story: Body) {
                    getOneStory(story)
                }
            })
        } else {
            binding.searchStoryRv.visibility = View.GONE
            binding.searchNoneTv.visibility = View.VISIBLE
        }
    }
    private fun getOneStory(story:Body){
        val storyService = StoryService()
        storyService.setOneStoryView(this)
        storyService.getStory(story.postIdx)
    }
    private fun searchTitle(){
        if(binding.searchSearchEt.text.isNotEmpty()){
            binding.searchSearchTv.text = "'${binding.searchSearchEt.text.trim()}'와 일치하는 이야기"
        }
        val searchService = SearchService()
        searchService.setSearchView(this)
        searchService.searchContents(binding.searchSearchEt.text.toString().trim())
        when (flag){
            0->searchService.searchContents(binding.searchSearchEt.text.toString().trim())
            else->searchService.searchTitle(binding.searchSearchEt.text.toString().trim())
        }
    }
}