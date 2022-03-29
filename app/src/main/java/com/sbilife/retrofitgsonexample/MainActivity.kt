package com.sbilife.retrofitgsonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbilife.retrofitgsonexample.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private const val BASE_URL = "https://opencollective.com/"
    }

    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    //not null asserted operator (!!) executes function even name is null
    // throws error if name is null
    private val binding get() = _binding!!

    private lateinit var mAdapter: OrganisationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.rvOrgList.setHasFixedSize(true)
        val mLinearLayoutManager : LinearLayoutManager = LinearLayoutManager(this)
        binding.rvOrgList.layoutManager = mLinearLayoutManager

        getAllOrganisations()
    }

    private fun getAllOrganisations() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiINterface::class.java)

        val retrofitData = retrofitBuilder.getOrganisations()
        retrofitData.enqueue(object : Callback<List<OrganisationsItem>?> {
            override fun onResponse(
                call: Call<List<OrganisationsItem>?>,
                response: Response<List<OrganisationsItem>?>
            ) {
                //not null asserted operator (!!) executes function even name is null
                // throws error if name is null
                val responceBody = response.body()!!

                mAdapter = OrganisationAdapter(responceBody)
                mAdapter.notifyDataSetChanged()
                binding.rvOrgList.adapter = mAdapter
            }
            override fun onFailure(call: Call<List<OrganisationsItem>?>, t: Throwable) {
                Log.e("Failure : ", t.message.toString())
            }
        })
    }
}