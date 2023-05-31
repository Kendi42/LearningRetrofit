package com.example.savingsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savingsapp.adapter.myAdapter
import com.example.savingsapp.model.Post
import com.example.savingsapp.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

    private val myAdapter by lazy {myAdapter()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide Action Bar
        supportActionBar?.hide();

        //Time Splash screen
//        val secondsDelayed= 1
//        Handler().postDelayed({
//            startActivity(Intent(this@MainActivity, Login::class.java))
//            finish()
//        }, secondsDelayed * 1000L)


        // API
        val repository = Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setupRecyclerview()

        // Displaying API results on recyclerview
        viewModel.getCustomPosts(2, "id", "desc")
        viewModel.myCustomPosts.observe(this, Observer { response->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })



        // Getting user input to inform the API call
//        viewModel.getPost()
//        val options:HashMap<String, String> = HashMap()
//        options["_sort"] = "id"
//        options["_order"] = "desc"
//
//        btn_get.setOnClickListener{
//            val myNumber = tv_numberInput.text.toString()
//            viewModel.getCustomPosts2(Integer.parseInt(myNumber), options)
//            Log.d("myResponse_Number", myNumber.toString())
//
//            viewModel.myCustomPosts2.observe(this, Observer{response ->
//                if(response.isSuccessful){
//                    Log.d("myResponse", response.body().toString())
//                    tv_response.text= response.body().toString()
//
//                    response.body()?.forEach{
//                        Log.d("myResponse", it.userId.toString())
//                        Log.d("myResponse", it.id.toString())
//                        Log.d("myResponse", it.title.toString())
//                        Log.d("myResponse", it.body.toString())
//                        Log.d("myResponse", "------------------------------------------")
//
//                    }
//                }
//                else{
//                    tv_response.text= response.code().toString()
//
//                }
//            })
//
//        }


        // Pushing Data to Server
//        //val myPost= Post(2,2,"Kendi Anyika", "Android-Developer")
//        viewModel.getpushPost2(2,2,"Kendi Anyika", "Android-Developer")
//        viewModel.myResponse.observe(this, Observer { response->
//            if(response.isSuccessful){
//                Log.d("Main", response.body().toString())
//                Log.d("Main", response.code().toString())
//                Log.d("Main", response.message().toString())
//            }else{
//                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
//            }
//        })

        //Working with Headers
//        viewModel.getPost("11112222")
//        viewModel.myResponse.observe(this, Observer { response->
//            if(response.isSuccessful){
//                Log.d("Main", response.body().toString())
//                Log.d("Main", response.code().toString())
//                Log.d("Main", response.headers().toString())
//            }else{
//                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
//            }
//        })




    }

    private fun setupRecyclerview(){
        recyclerview.adapter= myAdapter
        recyclerview.layoutManager= LinearLayoutManager(this)
    }
}
