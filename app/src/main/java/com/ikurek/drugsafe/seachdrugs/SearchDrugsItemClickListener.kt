package com.ikurek.drugsafe.seachdrugs

import com.ikurek.drugsafe.model.DrugModel

interface SearchDrugsItemClickListener {
    fun onItemClicked(drug: DrugModel)
}