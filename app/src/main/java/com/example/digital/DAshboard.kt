package com.example.digital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class activityDashboard : AppCompatActivity(),AdapterClass.OnItemClickListener {
    lateinit var myDataList: MutableList<DataClass>
    lateinit var myAdapter: AdapterClass // Changed to MyUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        myDataList = ArrayList()
        myAdapter = AdapterClass(myDataList, this) // Pass this as the listener
        //setting ToolBar
        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)
        val myRecyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        //setting Vertical Layout for Recycler view
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
    override fun onItemClick(data: DataClass) {

        // start the DetailsViewActivity by data  passed by extra
        val intent = Intent(this, DetailsViewActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemLogout -> finish()
            R.id.exitApp -> finishAffinity()
        }
        return true
    }

}