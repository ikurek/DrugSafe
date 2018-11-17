package com.ikurek.drugsafe.utlis

import android.net.Uri

object Web {

    fun getDrugUriById(id: Long): Uri {
        val defaultUri = "https://pub.rejestrymedyczne.csioz.gov.pl/ProduktSzczegoly.aspx?id="
        val customWebID = id.toString().subSequence(3, 8)
        return Uri.parse("$defaultUri$customWebID")
    }

    fun getDrugManualDownloadUriById(id: Long): Uri {
        val defaultUri = "https://pub.rejestrymedyczne.csioz.gov.pl/Pobieranie.ashx?type="
        var customWebID = id.toString().subSequence(3, 8)

        if (customWebID[0] == '0') customWebID = customWebID.trimStart('0')
        customWebID = "$customWebID-u"
        return Uri.parse("$defaultUri$customWebID")
    }
}