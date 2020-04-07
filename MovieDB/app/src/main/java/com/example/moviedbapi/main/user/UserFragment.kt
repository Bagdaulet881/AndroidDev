package com.example.moviedbapi.main.user


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.moviedbapi.R
import com.example.moviedbapi.base.ParentFragment
import com.example.moviedbapi.utilities.AppPreferences
import org.koin.android.ext.android.inject


class UserFragment : ParentFragment() {
    private val viewModel: UserViewModel by inject()
    private lateinit var progressBar: ProgressBar
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var logout: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.user_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        Log.d("profile","Here is an error")
        setData()
    }

    override fun bindViews(view: View) = with(view){
        progressBar = view.findViewById(R.id.progressBarProfile)
        tvName = view.findViewById(R.id.NameValue)
        tvUsername = view.findViewById(R.id.UsernameValue)
        logout = view.findViewById(R.id.logout)
        logout.setOnClickListener(){
            Toast.makeText(context, "You are Logged Out", Toast.LENGTH_SHORT).show()
            val i: Intent? = getActivity()!!.getPackageManager()
                .getLaunchIntentForPackage(getActivity()!!.getPackageName())
            i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
        }
    }

    override fun setData() {
        val sessionId = AppPreferences.getSessionId(activity?.applicationContext!!)
        sessionId?.let {
            viewModel.getAccountDetails(sessionId)
        }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is UserViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is UserViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is UserViewModel.State.Result -> {
                    tvName.text = result.account?.username
                    tvUsername.text = ("MEGOGO ID: " + result.account?.id.toString())

                }
                is UserViewModel.State.Error -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
                is UserViewModel.State.IntError -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
