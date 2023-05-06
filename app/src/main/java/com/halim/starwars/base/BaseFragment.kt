package com.halim.starwars.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    lateinit var baseActivity: BaseActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }
    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }
}