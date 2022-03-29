package com.sbilife.retrofitgsonexample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sbilife.retrofitgsonexample.databinding.RowOrganisationsBinding

class OrganisationAdapter(private val orgList: List<OrganisationsItem>) :
    RecyclerView.Adapter<OrganisationAdapter.OrganizationViewHolder>(){

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of row_organisations.xml
    // ie RowOrganisationsBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class OrganizationViewHolder(val binding: RowOrganisationsBinding) :
            RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val binding = RowOrganisationsBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return OrganizationViewHolder(binding)
    }

    // bind the items with each item
    // of the list orgList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        with(holder){
            with(orgList.get(position)){
                binding.txtOrgMemberID.text = memberId.toString()
                binding.txtOrgDescription.text = description.toString()
                Glide.with(holder.itemView.context)
                    .load(image).into(binding.ivOrgImage)

                binding.txtOrgDescription.setOnClickListener {
                    Toast.makeText(holder.itemView.context, "Total amount donated : $totalAmountDonated",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = orgList.size

}