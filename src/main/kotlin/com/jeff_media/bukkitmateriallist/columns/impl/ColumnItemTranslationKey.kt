package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.StringColumn
import org.bukkit.Material

class ColumnItemTranslationKey(material: Material) : StringColumn(material) {
    override fun name(): String {
        return "itemTranslationKey"
    }

    override fun value(): String? {
        return material.itemTranslationKey
    }

}