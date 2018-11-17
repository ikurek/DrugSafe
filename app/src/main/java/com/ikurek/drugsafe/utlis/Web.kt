package com.ikurek.drugsafe.utlis

import android.net.Uri

object Web {

    fun getDrugUriById(id: Long): Uri {
        val defaultUri = "https://pub.rejestrymedyczne.csioz.gov.pl/ProduktSzczegoly.aspx?id="
        val customWebID = id.toString().subSequence(3, 8)
        return Uri.parse("$defaultUri$customWebID")
    }
}