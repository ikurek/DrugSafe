package com.ikurek.drugsafe.model

data class PackageModel(
        var id: Long,
        var size: Long?,
        var sizeUnit: String?,
        var ean: Long?,
        var category: String?,
        var removed: Boolean?,
        var euNumber: String?,
        var parallelDistributor: String?,
        var drugs: List<DrugModel>?
)