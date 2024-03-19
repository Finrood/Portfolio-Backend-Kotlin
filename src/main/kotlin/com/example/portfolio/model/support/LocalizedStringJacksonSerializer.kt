package com.example.portfolio.model.support

import com.example.portfolio.utils.LocalizedString
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException

class LocalizedStringJacksonSerializer : StdSerializer<LocalizedString>(LocalizedString::class.java) {
    @Throws(IOException::class)
    override fun serialize(
        value: LocalizedString,
        jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        if (value.hasAnyTranslation()) {
            jsonGenerator.writeStartObject()
            for ((key, value1) in value.translations) {
                jsonGenerator.writeStringField(key.language, value1)
            }
            jsonGenerator.writeEndObject()
        } else {
            jsonGenerator.writeString(value.fallbackText)
        }
    }
}
