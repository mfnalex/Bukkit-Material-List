package com.jeff_media.bukkitmateriallist.util

import org.bukkit.Bukkit
import java.util.regex.Matcher
import java.util.regex.Pattern


class McVersion {
    companion object {
        val version: String

        init {
            val bukkitGetVersionOutput = Bukkit.getVersion()
            val matcher: Matcher =
                Pattern.compile("\\(MC: (?<version>[\\d]+\\.[\\d]+(\\.[\\d]+)?)\\)").matcher(bukkitGetVersionOutput)
            if (matcher.find()) {
                version = matcher.group("version")
            } else {
                throw RuntimeException("Could not determine Minecraft version from Bukkit.getVersion(): $bukkitGetVersionOutput")
            }
        }
    }
}