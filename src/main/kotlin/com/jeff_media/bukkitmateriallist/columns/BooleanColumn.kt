package com.jeff_media.bukkitmateriallist.columns

import org.bukkit.Material

abstract class BooleanColumn(material: Material): Column<Boolean>(material) {

    abstract override fun value(): Boolean

    override fun valueAsString(): String {
        return if(value()) {
            name() //"true"
        } else {
            " " //"false"
        }
    }


}