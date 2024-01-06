package com.jeff_media.bukkitmateriallist

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
        val contents: String = Material.entries.stream()
            .sorted(Comparator.comparing(Material::name))
            .filter { !it.name.startsWith("LEGACY_") }
            .map { ListEntry(it).contents() }
            .collect(Collectors.joining("\n"))

        File(dataFolder, "materials.md").writeText(header + "\n" + contents)
    }
}