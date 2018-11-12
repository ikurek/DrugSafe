package com.ikurek.drugsafe.model

data class DrugModel(
        var id: Long,
        var name: String?,
        var commonName: String?,
        var drugType: String?,
        var ammountOfSubstance: String?,
        var type: String?,
        var owner: String?,
        var procedureType: String?,
        var permissionNumber: Long?,
        var permissionExpiry: String?,
        var atc: String?,
        var substances: List<String>?
) {

    inline fun toIndexedStringMap(): List<Pair<String, String>> {

        var map = mutableListOf<Pair<String, String>>()
        var substancesString: String = ""

        map.add(Pair("id", id.toString()))

        if (!name.isNullOrBlank() && name != "null") map.add(Pair("name", name!!))
        if (!commonName.isNullOrBlank() && commonName != "null") map.add(Pair("commonName", commonName!!))
        if (!ammountOfSubstance.isNullOrBlank() && ammountOfSubstance != "null") map.add(Pair("ammountOfSubstance", ammountOfSubstance!!))
        if (!drugType.isNullOrBlank() && drugType != "null") map.add(Pair("drugType", drugType!!))
        if (!type.isNullOrBlank() && type != "null") map.add(Pair("type", type!!))
        if (!owner.isNullOrBlank() && owner != "null") map.add(Pair("owner", owner!!))
        if (!procedureType.isNullOrBlank() && procedureType != "null") map.add(Pair("procedureType", procedureType!!))
        if (permissionNumber != null) map.add(Pair("permissionNumber", permissionNumber.toString()))
        if (!permissionExpiry.isNullOrBlank() && permissionExpiry != "null") map.add(Pair("permissionExpiry", permissionExpiry!!))
        if (!atc.isNullOrBlank() && atc != "null") map.add(Pair("atc", atc!!))

        for (substance in substances!!.iterator()) {
            substancesString = "${substancesString}, ${substance}"
        }

        map.add(Pair("substances", substancesString.drop(2)))

        return map

    }
}