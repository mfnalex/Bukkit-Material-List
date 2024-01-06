package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.StringColumn
import org.bukkit.Material

class ColumnBlockTranslationKey(material: Material) : StringColumn(material) {
    override fun name(): String {
        return "blockTranslationKey"
    }

    override fun value(): String? {
        return material.blockTranslationKey
    }

}