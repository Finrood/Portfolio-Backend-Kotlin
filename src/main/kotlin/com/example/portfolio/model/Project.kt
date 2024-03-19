package com.example.portfolio.model

import com.example.portfolio.model.support.LocalizedStringJpaConverter
import com.example.portfolio.model.support.TechnologyCollectionJpaConverter
import com.example.portfolio.utils.LocalizedString
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Project {
    @Id
    var id: String? = null
        private set

    @Column(nullable = false)
    @Convert(converter = LocalizedStringJpaConverter::class)
    var title: LocalizedString? = null
        private set

    @Column(columnDefinition = "TEXT")
    @Convert(converter = LocalizedStringJpaConverter::class)
    var description: LocalizedString? = null

    @Convert(converter = TechnologyCollectionJpaConverter::class)
    var technologies: MutableList<Technology> = ArrayList()
    var githubUrl: String? = null
    var startYear: String? = null
        private set
    var endYear: String? = null


    protected constructor()

    constructor(title: LocalizedString?, startYear: String?) {
        this.id = UUID.randomUUID().toString()
        this.title = title
        this.startYear = startYear
    }

    fun setTitle(title: LocalizedString?): Project {
        this.title = title
        return this
    }

    fun setDescription(description: LocalizedString?): Project {
        this.description = description
        return this
    }

    fun addTechnologies(vararg technologies: Technology?): Project {
        this.technologies.addAll(technologies.filterNotNull())
        return this
    }

    fun setGithubUrl(githubUrl: String?): Project {
        this.githubUrl = githubUrl
        return this
    }

    fun setEndYear(endYear: String?): Project {
        this.endYear = endYear
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val project = o as Project
        return id == project.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "Project{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", description=" + description +
                ", technologies=" + technologies +
                ", githubUrl='" + githubUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}'
    }
}
