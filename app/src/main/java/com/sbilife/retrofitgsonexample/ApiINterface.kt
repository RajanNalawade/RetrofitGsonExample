package com.sbilife.retrofitgsonexample

import retrofit2.Call
import retrofit2.http.GET

interface ApiINterface {

    @GET("webpack/members/organizations.json")
    //fun getOrganisations(): Call<Organisations>
    fun getOrganisations(): Call<List<OrganisationsItem>>


}