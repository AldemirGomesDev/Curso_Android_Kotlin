package com.aldemir.cursoandroidkotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aldemir.cursoandroidkotlin.NavigationHost
import com.aldemir.cursoandroidkotlin.R
import com.aldemir.cursoandroidkotlin.retrofit.api.UserAPI
import com.aldemir.cursoandroidkotlin.retrofit.config.NetworkConfig
import com.aldemir.cursoandroidkotlin.retrofit.config.SessionManager
import com.aldemir.cursoandroidkotlin.retrofit.model.UserRequest
import com.aldemir.cursoandroidkotlin.retrofit.model.UserResponse
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email_edit_text.setText("mor_2314")
        password_edit_text.setText("83r5^_")

        login_button.setOnClickListener {
            login(
                email_edit_text.text.toString(),
                password_edit_text.text.toString()
            )
        }
    }

    private fun login(email: String, password: String) {
        loading()
        NetworkConfig.provideApi(UserAPI::class.java, context)
            .onLogin(UserRequest(email, password)).enqueue(object :
                Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    stopLoading()
                    if (response.code() == 200){
                        Log.d("TAG_login_test", "success: ${response.body()}")
                        saveToken(response.body()?.token)
                        navigateToHome()
                    } else {
                        showToastError("Error: ${response.code()} Credenciais incorretas!")
                        Log.e("TAG_login_test", "error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                    stopLoading()
                    showToastError("Erro ao acessar o servidor!")
                    Log.e("TAG_login_test", "onFailure: Error ao fazer login")
                }
            })
    }

    private fun saveToken(token: String?) {
        token?.let {
            SessionManager(context).saveAuthToken(it)
        }
    }

    private fun navigateToHome() {
        (activity as NavigationHost).navigateTo(HomeFragment(), true)
    }

    private fun loading() {
        loading.visibility = View.VISIBLE
        login_button.isEnabled = false
    }

    private fun stopLoading() {
        loading.visibility = View.GONE
        login_button.isEnabled = true
    }

    private fun showToastError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}