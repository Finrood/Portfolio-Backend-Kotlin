package com.example.portfolio.model.support

import com.example.portfolio.utils.LocalizedString
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.hibernate.type.SerializationException
import java.io.IOException

@Converter(autoApply = true)
class LocalizedStringJpaConverter : AttributeConverter<LocalizedString?, String?> {
    private val objectMapper = ObjectMapper()

    init {
        val module = SimpleModule()
        module.addSerializer(LocalizedString::class.java, LocalizedStringJacksonSerializer())
        module.addDeserializer(LocalizedString::class.java, LocalizedStringJacksonDeserializer())
        objectMapper.registerModule(module)
    }

    @Throws(SerializationException::class)
    override fun convertToDatabaseColumn(attribute: LocalizedString?): String? {
        try {
            return if ((attribute == null)) null else (if (attribute.hasAnyTranslation()) objectMapper.writeValueAsString(
                attribute
            ) else attribute.fallbackText)
        } catch (e: JsonProcessingException) {
            throw SerializationException("Unable to serialize localized string $attribute", e)
        }
    }

    @Throws(SerializationException::class)
    override fun convertToEntityAttribute(dbData: String?): LocalizedString? {
        try {
            return if (dbData == null) {
                null
            } else if (isValidJson(dbData.trim { it <= ' ' })) {
                objectMapper.readValue(dbData, LocalizedString::class.java)
            } else {
                LocalizedString().setFallbackText(dbData)
            }
        } catch (e: IOException) {
            throw SerializationException("Unable to deserialize localized string $dbData", e)
        }
    }

    private fun isValidJson(jsonString: String): Boolean {
        try {
            val jsonNode = objectMapper.readTree(jsonString)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
