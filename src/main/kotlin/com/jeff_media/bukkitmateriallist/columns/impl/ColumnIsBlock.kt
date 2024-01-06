package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.BooleanColumn
import org.bukkit.Material

class ColumnIsBlock(material: Material) : BooleanColumn(material) {
    override fun name(): String {
        return "isBlock"
    }

    override fun value(): Boolean {
        return material.isBlock
    }
}