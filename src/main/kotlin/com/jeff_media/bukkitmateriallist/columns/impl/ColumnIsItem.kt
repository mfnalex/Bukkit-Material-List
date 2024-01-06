package com.jeff_media.bukkitmateriallist.columns.impl

import com.jeff_media.bukkitmateriallist.columns.BooleanColumn
import org.bukkit.Material

class ColumnIsItem(material: Material) : BooleanColumn(material) {
    override fun name(): String {
        return "isItem"
    }

    override fun value(): Boolean {
        return material.isItem
    }
}