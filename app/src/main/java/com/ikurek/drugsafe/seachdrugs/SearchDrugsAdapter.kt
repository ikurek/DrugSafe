package com.ikurek.drugsafe.seachdrugs

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.databinding.RowDrugBinding
import com.ikurek.drugsafe.model.DrugModel

class SearchDrugsAdapter(var drugs: List<DrugModel>) : RecyclerView.Adapter<SearchDrugsAdapter.ViewHolder>(),
    SearchDrugsItemClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowDrugBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_drug,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = drugs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(drugs[position])

    override fun onItemClicked(drug: DrugModel) {
        Log.d("Clicked", "Drug ${drug.name}")
    }

    class ViewHolder(var drugLayoutBinding: RowDrugBinding) : RecyclerView.ViewHolder(drugLayoutBinding.root) {
        fun bind(drug: DrugModel) {
            drugLayoutBinding.drug = drug
            drugLayoutBinding.executePendingBindings()

        }
    }
}

