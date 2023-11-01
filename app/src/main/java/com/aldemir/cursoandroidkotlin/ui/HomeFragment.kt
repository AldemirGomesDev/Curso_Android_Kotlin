package com.aldemir.cursoandroidkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aldemir.cursoandroidkotlin.R
import com.aldemir.cursoandroidkotlin.retrofit.api.UserAPI
import com.aldemir.cursoandroidkotlin.retrofit.config.NetworkConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        CoroutineScope(Dispatchers.IO).launch {
            val response = NetworkConfig.provideApi(UserAPI::class.java, context)
                .getCategory()
            if (response.isSuccessful) {
                if (response.code() == 200){
                    Log.d("TAG_login_test", "success: ${response.body()}")
                    withContext(Dispatchers.Main) {
                        description.text = response.body().toString()
                    }
                } else {
                    Log.e("TAG_login_test", "error: ${response.code()}")
                }
            } else {
                Log.e("TAG_login_test", "onFailure: Error ao fazer login")
            }
        }
    }
}