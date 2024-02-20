package com.jeff_media.bukkitmateriallist

import com.jeff_media.bukkitmateriallist.util.McVersion
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkUnloadEvent
import org.bukkit.plugin.java.JavaPlugin
import org.commonmark.Extension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
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
            .map { ListEntry(it).getTable() }
            .collect(Collectors.joining("\n"))

        val version: String = McVersion.version

        val markdown = header + System.lineSeparator() + headerSeparator + System.lineSeparator() + table
        File(dataFolder, "${version}.md").writeText(markdown)

        val html = html(markdown)
        File(dataFolder, "${version}.html").writeText(html)
    }

    private fun html(markdown: String): String {
        val html: String = BukkitMaterialList::class.java.getResource("/template.html")!!.readText()
        val extensions: List<Extension> = listOf(TablesExtension.create())
        val parser: Parser = Parser.builder()
            .extensions(extensions)
            .build()
        val document: Node = parser.parse(markdown)
        val renderer: HtmlRenderer = HtmlRenderer.builder()
            .extensions(extensions)
            .build()
        val table : String= renderer.render(document)

        return html
            .replace("{table}", table)
            .replace("{version}", McVersion.version)
            .replace("{bukkitversion}", Bukkit.getVersion())
            .replace("{bukkitbukkitversion}", Bukkit.getBukkitVersion())
    }

}