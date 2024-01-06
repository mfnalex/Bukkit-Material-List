package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.BooleanColumn
import org.bukkit.Material

class ColumnIsOccluding(material: Material) : BooleanColumn(material) {
    override fun value(): Boolean {
        return material.isOccluding
    }

    override fun name(): String {
        return "occluding"
    }
}