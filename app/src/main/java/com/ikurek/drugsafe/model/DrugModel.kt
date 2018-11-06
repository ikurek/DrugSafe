package com.ikurek.drugsafe.model

data class DrugModel(
    var id: Long,
    var name: String,
    var commonName: String,
    var ammountOfSubstance: String
)