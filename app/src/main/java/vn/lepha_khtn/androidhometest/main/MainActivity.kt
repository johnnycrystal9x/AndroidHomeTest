package vn.lepha_khtn.androidhometest.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import vn.lepha_khtn.androidhometest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = KeywordAdapter(this)
        rvKeyword?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvKeyword?.adapter = adapter
    }
}
