package com.jeff_media.bukkitmateriallist

import com.jeff_media.bukkitmateriallist.columns.Column
import com.jeff_media.bukkitmateriallist.columns.impl.*
import org.bukkit.Material
import java.util.stream.Collectors
import kotlin.reflect.KClass

class ListEntry(val material: Material) {

    private var columns: MutableList<Column<out Any>> = ArrayList()

    companion object {

        val columnClasses: List<KClass<out Column<out Any>>> = listOf(
            ColumnName::class,
            ColumnIsBlock::class,
            ColumnIsItem::class,
            ColumnBlockTranslationKey::class,
            ColumnItemTranslationKey::class,
            ColumnIsSolid::class,
            ColumnIsOccluding::class,
            ColumnMaxStackSize::class
        )

        fun getHeader(): String {
            return "|" + columnClasses.joinToString("|") {
                it.constructors.first().call(Material.AIR).name()
            } + "|"
        }

        fun getHeaderSeparator(): String {
            return "|" + columnClasses.stream().map { "---" }.collect(Collectors.joining("|")) + "|"
        }


    }

    init {
        columnClasses.forEach {
            val column = it.constructors.first().call(material)
            columns.add(column)
        }
    }

    fun table(): String {
        //println("Returning contents(): " + columns.size + " columns")
        //columns.forEach { println("Column: ${it.name()} = ${it.valueAsString()}") }
        return "|" + columns.joinToString("|") { it.valueAsString() } + "|"
    }

    fun columns(): List<Column<out Any>> {
        return columns.toList()
    }


}