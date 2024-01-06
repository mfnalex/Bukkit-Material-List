package com.jeff_media.bukkitmateriallist


import org.bukkit.Material


class ListEntry(val material: Material) {

    companion object {
        private val functions: MutableMap<String, (material: Material) -> String?> = LinkedHashMap()
        private val separator: String = "|"

        init {
            functions["name"] = { it.name }

            // Block / Item
            functions["isBlock"] = { if (it.isBlock) "block" else null }
            functions["isItem"] = { if (it.isItem) "item" else null }

            // Solid / Occluding
            functions["isSolid"] = { if (it.isSolid) "solid" else null }
            functions["isOccluding"] = { if (it.isOccluding) "occluding" else null }

            // NamespacedKey
            functions["key"] = { it.key.toString() }

            // TranslationKeys
            functions["blockTranslationKey"] = { it.blockTranslationKey }
            functions["itemTranslationKey"] = { it.itemTranslationKey }

            // Max Stack Size
            functions["maxStackSize"] = { it.maxStackSize.toString() }

            // Max Durability
            functions["maxDurability"] = { it.maxDurability.toString() }

            // BlockData Class
            functions["blockDataClass"] = { if(it.isBlock)  it.createBlockData().javaClass.simpleName  else null}

            // isInteractable
            functions["isInteractable"] = { if (it.isInteractable) "interactable" else null }

            // hasGravity
            functions["hasGravity"] = { if (it.hasGravity()) "gravity" else null }

            // isEdible
            functions["isEdible"] = { if (it.isEdible) "edible" else null }

            // isRecord
            functions["isRecord"] = { if (it.isRecord) "record" else null }

            // isFlammable
            functions["isFlammable"] = { if (it.isFlammable) "flammable" else null }

            // isBurnable
            functions["isBurnable"] = { if (it.isBurnable) "burnable" else null }

            // isFuel
            functions["isFuel"] = { if (it.isFuel) "fuel" else null }

            // hardness
            functions["hardness"] = { if(it.isBlock) it.hardness.toString() else null }

            // blastResistence
            functions["blastResistence"] = { if(it.isBlock) it.blastResistance.toString() else null }

            // slipperiness
            functions["slipperiness"] = { if(it.isBlock) it.slipperiness.toString() else null }

            // craftingRemainingItem
            functions["craftingRemainingItem"] = { if(it.isItem) it.craftingRemainingItem?.name else null }

            // equipmentSlot
            functions["equipmentSlot"] = { if(it.isItem) it.equipmentSlot.name else null }

            // creativeCategory
            functions["creativeCategory"] = { it.creativeCategory?.name }



        }

        fun getHeader(): String {
            return functions.keys.joinToString(separator, separator, separator)
        }

        fun getHeaderSeparator(): String {
            return functions.keys.joinToString(separator, separator, separator) { "-" }
        }
    }

    private val properties: MutableMap<String, String> = LinkedHashMap()

    init {
        for((key, value) in functions) {
            properties[key] = value(material) ?: " "
        }
    }

    fun getTable(): String {
        return properties.values.joinToString(separator, separator, separator)
    }

}