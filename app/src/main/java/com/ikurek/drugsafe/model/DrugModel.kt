package com.ikurek.drugsafe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class DrugModel(
    @PrimaryKey
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

    companion object {
        fun toIndexedStringMap(drugModel: DrugModel): List<Pair<String, String>> {

            var map = mutableListOf<Pair<String, String>>()
            var substancesString = ""

            map.add(Pair("id", drugModel.id.toString()))

            if (!drugModel.name.isNullOrBlank() && drugModel.name != "null") map.add(
                Pair(
                    "name",
                    drugModel.name!!
                )
            )
            if (!drugModel.commonName.isNullOrBlank() && drugModel.commonName != "null") map.add(
                Pair("commonName", drugModel.commonName!!)
            )
            if (!drugModel.ammountOfSubstance.isNullOrBlank() && drugModel.ammountOfSubstance != "null") map.add(
                Pair("ammountOfSubstance", drugModel.ammountOfSubstance!!)
            )
            if (!drugModel.drugType.isNullOrBlank() && drugModel.drugType != "null") map.add(
                Pair(
                    "drugType",
                    drugModel.drugType!!
                )
            )
            if (!drugModel.type.isNullOrBlank() && drugModel.type != "null") map.add(
                Pair(
                    "type",
                    drugModel.type!!
                )
            )
            if (!drugModel.owner.isNullOrBlank() && drugModel.owner != "null") map.add(
                Pair(
                    "owner",
                    drugModel.owner!!
                )
            )
            if (!drugModel.procedureType.isNullOrBlank() && drugModel.procedureType != "null") map.add(
                Pair("procedureType", drugModel.procedureType!!)
            )
            if (drugModel.permissionNumber != null) map.add(
                Pair(
                    "permissionNumber",
                    drugModel.permissionNumber.toString()
                )
            )
            if (!drugModel.permissionExpiry.isNullOrBlank() && drugModel.permissionExpiry != "null") map.add(
                Pair("permissionExpiry", drugModel.permissionExpiry!!)
            )
            if (!drugModel.atc.isNullOrBlank() && drugModel.atc != "null") map.add(
                Pair(
                    "atc",
                    drugModel.atc!!
                )
            )

            for (substance in drugModel.substances!!.iterator()) {
                substancesString = "${substancesString}, ${substance}"
            }

            map.add(Pair("substances", substancesString.drop(2)))

            return map

        }
    }
}