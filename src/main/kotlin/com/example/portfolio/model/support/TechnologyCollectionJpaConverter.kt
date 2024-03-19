package com.example.portfolio.model.support

import com.example.portfolio.model.Technology
import jakarta.persistence.AttributeConverter
import org.apache.commons.lang3.StringUtils
import org.springframework.util.CollectionUtils
import java.util.*
import java.util.stream.Collectors

class TechnologyCollectionJpaConverter : AttributeConverter<Collection<Technology?>, String?> {
    override fun convertToDatabaseColumn(attribute: Collection<Technology?>): String? {
        if (CollectionUtils.isEmpty(attribute)) {
            return null
        }
        return attribute.stream()
            .map { obj: Technology? -> obj!!.name }
            .collect(Collectors.joining(","))
    }

    override fun convertToEntityAttribute(dbData: String?): Collection<Technology?> {
        if (StringUtils.isEmpty(dbData)) {
            return ArrayList()
        }
        return Arrays.stream(dbData!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            .map { value: String? ->
                Technology.valueOf(
                    value!!
                )
            }
            .collect(Collectors.toList())
    }
}
