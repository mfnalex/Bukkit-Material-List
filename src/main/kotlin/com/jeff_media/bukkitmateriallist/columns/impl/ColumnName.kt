package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.StringColumn
import org.bukkit.Material

class ColumnName(material: Material) : StringColumn(material) {
    override fun name(): String {
        return "Name"
    }

    override fun value(): String {
        return material.name
    }
}