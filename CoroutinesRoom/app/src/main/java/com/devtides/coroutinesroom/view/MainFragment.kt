package com.devtides.coroutinesroom.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.R
import com.devtides.coroutinesroom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signoutBtn.setOnClickListener { onSignout() }
        deleteUserBtn.setOnClickListener { onDelete() }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(this, Observer {

        })
        viewModel.userDeleted.observe(this, Observer {

        })
    }

    private fun onSignout() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(usernameTV).navigate(action)
    }

    private fun onDelete() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(usernameTV).navigate(action)
    }

}
