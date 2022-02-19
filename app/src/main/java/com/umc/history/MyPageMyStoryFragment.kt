package com.umc.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.databinding.FragmentMypageLikestoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageMyStoryFragment: Fragment() {
    lateinit var binding: FragmentMypageLikestoryBinding
    private var myPageStoryDatas = ArrayList<MyPageStory>()
    private var token : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLikestoryBinding.inflate(inflater,container,false)
        //더미데이터랑 어댑터 연결
        val myPageStoryRVAdapter = MyPageStoryRVAdapter(myPageStoryDatas)
        //리사이클러뷰에 어댑터를 연결
        binding.myPageStoryRecyclerView.adapter = myPageStoryRVAdapter


        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val userWroteStoryService = retrofit.create(UserWroteStoryInterface::class.java)

        val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
        token = spf?.getString("accessToken", null)

        userWroteStoryService.getUserWroteStory(token).enqueue(object : Callback<GetUserWroteStoryResponse>{
            override fun onResponse(call: Call<GetUserWroteStoryResponse>, response: Response<GetUserWroteStoryResponse>) {
                Log.d("getUserWroteStory_OnResponse","$response")
                // response.body()?.body?.post?.title?.
                myPageStoryDatas.apply {
                    add(MyPageStory(response.body()?.body?.title,response.body()?.body?.user?.profileImageUrl,response.body()?.body?.totalLike,response.body()?.body?.totalComment,response.body()?.body?.contents,response.body()?.body?.user?.nickName))
                }
                myPageStoryRVAdapter.notifyDataSetChanged()

            }
            override fun onFailure(call: Call<GetUserWroteStoryResponse>, t: Throwable) {
                Log.d("getUserWroteStory_OnFailure","$t")
            }
        })

//        //데이터 리스트 생성 더미데이터
//        myPageStoryDatas.apply {
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//        }




        val dividerItemDecoration =
            DividerItemDecoration(binding.myPageStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.myPageStoryRecyclerView.addItemDecoration(dividerItemDecoration)


        return binding.root
    }

}