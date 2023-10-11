package com.aldemir.cursoandroidkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aldemir.cursoandroidkotlin.NavigationHost
import com.aldemir.cursoandroidkotlin.R
import com.aldemir.cursoandroidkotlin.retrofit.api.UserAPI
import com.aldemir.cursoandroidkotlin.retrofit.config.NetworkConfig
import com.aldemir.cursoandroidkotlin.retrofit.model.UserRequest
import com.aldemir.cursoandroidkotlin.retrofit.model.UserResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = app_bar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        getCategory()

    }

    private fun getCategory() {
        NetworkConfig.provideApi(UserAPI::class.java, context)
            .getCategory().enqueue(object :
                Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.code() == 200){
                        Log.d("TAG_login_test", "success: ${response.body()}")
                    } else {
                        Log.e("TAG_login_test", "error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("TAG_login_test", "onFailure: Error ao fazer login")
                }
            })
    }
}