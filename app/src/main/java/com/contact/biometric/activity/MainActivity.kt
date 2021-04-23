package com.contact.biometric.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.contact.biometric.R
import com.contact.biometric.activity.adapter.MyAdapter
import com.contact.biometric.activity.model.ApiResponseModel
import com.contact.biometric.activity.model.MyViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    var model: MyViewModel? = null
    var recyclerImages: RecyclerView? = null
    var edtSearch: EditText? = null
    var btnSearch: Button? = null
    var cardResultNotFound: CardView? = null


    var list: List<ApiResponseModel.Hit> = ArrayList<ApiResponseModel.Hit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this@MainActivity)[MyViewModel::class.java]
        findIds()    //find all ids

        initListener()   //control clicks

    }

    private fun initListener() {
        btnSearch!!.setOnClickListener {
            if (edtSearch!!.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT).show()
            } else {
                closeKeyboard()
                apiHit()
            }
        }
    }


    //close keyboard
    private fun closeKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edtSearch!!.windowToken, 0)
    }


    //hittting api
    private fun apiHit() {

        model!!.getUserChannelModelLiveData(this@MainActivity, edtSearch!!.text.toString())
            .observe(this@MainActivity,
                androidx.lifecycle.Observer { apiResponseModel ->
                    if (apiResponseModel!!.getTotal() != null) {

                        list = apiResponseModel.getHits() as List<ApiResponseModel.Hit>
                        if (list.size > 0) {
                            recyclerImages!!.visibility = View.VISIBLE
                            cardResultNotFound!!.visibility = View.INVISIBLE
                            initAdapter()
                        } else {
                            recyclerImages!!.visibility = View.INVISIBLE
                            cardResultNotFound!!.visibility = View.VISIBLE

                        }
                    }
                })


    }

    //intialize adapter
    private fun initAdapter() {
        val adapter = MyAdapter(this@MainActivity, list, object : MyAdapter.Select {
            override fun onSelected(position: Int) {}
        })
        recyclerImages!!.adapter = adapter
    }


    private fun findIds() {
        edtSearch = findViewById(R.id.edt_search)
        btnSearch = findViewById(R.id.btn_Search)
        recyclerImages = findViewById(R.id.recycler_images)
        cardResultNotFound = findViewById(R.id.card_noResult)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}