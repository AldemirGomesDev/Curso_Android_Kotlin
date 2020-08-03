package com.aldemir.cursoandroidkotlin

import androidx.fragment.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean)
}