package com.jeff_media.bukkitmateriallist

import com.jeff_media.bukkitmateriallist.columns.Column
import com.jeff_media.bukkitmateriallist.columns.impl.ColumnBlockTranslationKey
import com.jeff_media.bukkitmateriallist.columns.impl.ColumnIsBlock
import com.jeff_media.bukkitmateriallist.columns.impl.ColumnIsItem
import com.jeff_media.bukkitmateriallist.columns.impl.ColumnName
import org.bukkit.Material
import kotlin.reflect.KClass

class ListEntry(val material: Material) {

    private var columns: MutableList<Column<out Any>> = ArrayList()

    companion object {

        val columnClasses: List<KClass<out Column<out Any>>> = listOf(
            ColumnName::class,
            ColumnIsBlock::class,
            ColumnIsItem::class,
            ColumnBlockTranslationKey::class)

        fun getHeader(): String {
            return "|" + columnClasses.joinToString("|") { it.constructors.first().call(Material.AIR).name() } + "|"
        }


    }

    init {
        columnClasses.forEach {
            val column = it.constructors.first().call(material)
            columns.add(column)
        }
    }

    fun contents(): String {
        //println("Returning contents(): " + columns.size + " columns")
        //columns.forEach { println("Column: ${it.name()} = ${it.valueAsString()}") }
        return "|" + columns.joinToString("|") { it.valueAsString() } + "|"
    }


}