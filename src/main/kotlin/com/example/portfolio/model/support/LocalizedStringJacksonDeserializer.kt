package com.example.portfolio.model.support

import com.example.portfolio.utils.LocalizedString
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.util.*

class LocalizedStringJacksonDeserializer : StdDeserializer<LocalizedString?>(LocalizedString::class.java) {
    /**
     * Implements the behavior for creating a LocalizedString from different input types:
     *
     *
     *  * `null`: Returns a `null` LocalizedString.
     *  * Text value: Returns a LocalizedString with the fallback text initialized to the value of the attribute.
     * Normally, this scenario is unexpected since we expect a localized string to be represented in its structured form
     * when edited, but it's supported anyway.
     *  * Map value: Returns a LocalizedString initialized with the provided translations. The fallback text is intentionally lost
     * as long as at least one translation exists, as it is no longer valuable. If no translation exists, it falls back
     * to either scenario #1 or #2.
     *
     */
    @Throws(IOException::class)
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): LocalizedString? {
        val node = jsonParser.codec.readTree<JsonNode>(jsonParser)
        if (node.isNull) {
            return null
        } else if (node.isTextual) {
            return LocalizedString().setFallbackText(node.asText())
        } else {
            val fallbackText: String?
            val translations: MutableMap<Locale, String> = HashMap()

            val iterator = node.fields()
            while (iterator.hasNext()) {
                val entry = iterator.next()

                if (!entry.value.isNull) {
                    translations[Locale.forLanguageTag(entry.key)] = entry.value.asText()
                }
            }

            fallbackText = if (translations.isEmpty()) {
                iterator.next().value.asText()
            } else {
                null
            }

            return if (fallbackText == null && translations.isEmpty()) {
                null
            } else {
                LocalizedString()
                    .setTranslations(translations)
                    .setFallbackText(fallbackText)
            }
        }
    }
}
