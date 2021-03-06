package com.ikurek.drugsafe.mydrugs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.databinding.RowDrugBinding
import com.ikurek.drugsafe.model.DrugModel

class MyDrugsAdapter(
    var drugs: List<DrugModel>,
    private val onElementClicked: (DrugModel) -> Unit
) :
    RecyclerView.Adapter<MyDrugsAdapter.ViewHolder>() {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(drugs[position], onElementClicked)

    class ViewHolder(private var drugLayoutBinding: RowDrugBinding) :
        RecyclerView.ViewHolder(drugLayoutBinding.root) {
        fun bind(drug: DrugModel, onElementClicked: (DrugModel) -> Unit) {
            drugLayoutBinding.drug = drug
            drugLayoutBinding.executePendingBindings()
            drugLayoutBinding.root.setOnClickListener { onElementClicked(drug) }

        }
    }
}