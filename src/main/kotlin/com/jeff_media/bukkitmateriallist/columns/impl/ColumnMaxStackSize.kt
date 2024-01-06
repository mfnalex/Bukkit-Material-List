package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.StringColumn
import org.bukkit.Material

class ColumnMaxStackSize(material: Material) : StringColumn(material) {
    override fun name(): String {
        return "maxStack"
    }

    override fun value(): String? {
        return material.maxStackSize.toString()
    }
}