package com.ikurek.drugsafe.drugdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.model.DrugModel
import kotlinx.android.synthetic.main.row_drug_details.view.*

class DrugDetailsAdapter(val drug: DrugModel) : RecyclerView.Adapter<DrugDetailsAdapter.ViewHolder>() {

    val map = drug.toIndexedStringMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_drug_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = map.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(map[position])


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(keyToValuePair: Pair<String, String>) {

            view.title.text = keyToValuePair.first
            view.content.text = keyToValuePair.second
        }
    }
}