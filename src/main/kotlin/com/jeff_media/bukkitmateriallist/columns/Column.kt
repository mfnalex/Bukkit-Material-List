package com.jeff_media.bukkitmateriallist.columns

import org.bukkit.Material

abstract class Column<T>(val material: Material) {

    abstract fun name(): String
    abstract fun value(): T?

    open fun valueAsString(): String {
        if(value() == null) return " "
        return value().toString()
    }

    fun material(): Material {
        return material
    }

}