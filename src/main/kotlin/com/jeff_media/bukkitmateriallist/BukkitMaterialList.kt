package com.jeff_media.bukkitmateriallist

import com.jeff_media.bukkitmateriallist.util.McVersion
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.stream.Collectors


class BukkitMaterialList: JavaPlugin() {

        override fun onEnable() {
            dataFolder.mkdirs()
            createMaterialList()
        }

    private fun createMaterialList() {
        val header: String = ListEntry.getHeader()
        val headerSeparator: String = ListEntry.getHeaderSeparator()
        val table: String = Material.entries.stream()
            .sorted(Comparator.comparing(Material::name))
            .filter { !it.name.startsWith("LEGACY_") }
            .map { ListEntry(it).table() }
            .collect(Collectors.joining("\n"))

        val version: String = McVersion.version

        val content = header + System.lineSeparator() + headerSeparator + System.lineSeparator() + table

        File(dataFolder, "${version}-table.md").writeText(content)
    }

}