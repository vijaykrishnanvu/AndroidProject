package com.example.digital

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DAshboard : AppCompatActivity(),AdapterClass.OnItemClickListener {
    lateinit var myDataList: MutableList<DataClass>
    lateinit var myAdapter: AdapterClass // Changed to MyUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        myDataList = ArrayList()
        myAdapter = AdapterClass(myDataList, this) // Pass this as the listener

        val myRecyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        myRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myRecyclerView.adapter = myAdapter
        fetchData()
    }
    // Function to fetch data from API
    private fun fetchData() {

        // Create API call using retrofitBuilder
        val makeCall = ApiClient.retrofitBuilder.getData()

        // Enqueue the call to execute asynchronously
        makeCall.enqueue(object : Callback<List<DataClass>> {
            override fun onResponse(call: Call<List<DataClass>>?, response: Response<List<DataClass>>?)
            {
                // Handle response
                val dataList: List<DataClass>? = response?.body()
                if (dataList != null) {
                    Log.d("DataList", dataList.toString())
                    myDataList.clear() // Clear existing data in myDataList
                    myDataList.addAll(dataList) // Add new data to myDataList
                    myAdapter.setData(dataList) // Update adapter data with new dataList
                }
            }

            override fun onFailure(call: Call<List<DataClass>>?, t: Throwable?) {
                Log.i("mytag", "Error is ${t.toString()}")
            }
        })
    }

    override fun onItemClick(data: DataClass) {
        val intent = Intent(this, OpenRecycler::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }



}