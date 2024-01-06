package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.BooleanColumn
import org.bukkit.Material

class ColumnIsSolid(material: Material) : BooleanColumn(material) {
    override fun value(): Boolean {
        return material.isSolid
    }

    override fun name(): String {
        return "solid"
    }
}