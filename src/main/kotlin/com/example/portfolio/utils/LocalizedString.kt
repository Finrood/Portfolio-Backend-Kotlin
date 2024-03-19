package com.example.portfolio.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class LocalizedString {
    var translations: Map<Locale, String> = HashMap()
        private set
    var fallbackText: String? = null
        private set

    fun setTranslations(translations: Map<Locale, String>): LocalizedString {
        this.translations = translations
        return this
    }

    fun setFallbackText(fallbackText: String?): LocalizedString {
        this.fallbackText = fallbackText
        return this
    }

    fun hasAnyTranslation(): Boolean {
        return translations.isNotEmpty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as LocalizedString
        return translations == that.translations && fallbackText == that.fallbackText
    }

    override fun hashCode(): Int {
        return Objects.hash(translations, fallbackText)
    }

    override fun toString(): String {
        if (translations.isEmpty()) {
            return fallbackText!!
        } else {
            val stringBuilder = StringBuilder()
            translations.forEach { (key: Locale, value: String?) ->
                stringBuilder.append(
                    String.format(
                        "{\"%s\": \"%s\"}",
                        key.toLanguageTag(),
                        value
                    )
                )
            }
            return stringBuilder.toString()
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LocalizedString::class.java)
    }
}
