package com.example.portfolio.controller.dto

import com.example.portfolio.model.Project
import com.example.portfolio.model.Technology
import com.example.portfolio.utils.LocalizedString

class ProjectDto {
    var id: String? = null
        private set
    var title: LocalizedString? = null
        private set
    var description: LocalizedString? = null
        private set
    var technologies: List<Technology> = ArrayList()
        private set
    var githubUrl: String? = null
        private set
    var startYear: String? = null
        private set
    var endYear: String? = null
        private set

    fun setId(id: String?): ProjectDto {
        this.id = id
        return this
    }

    fun setTitle(title: LocalizedString?): ProjectDto {
        this.title = title
        return this
    }

    fun setDescription(description: LocalizedString?): ProjectDto {
        this.description = description
        return this
    }

    fun setTechnologies(technologies: List<Technology>): ProjectDto {
        this.technologies = technologies
        return this
    }

    fun setGithubUrl(githubUrl: String?): ProjectDto {
        this.githubUrl = githubUrl
        return this
    }

    fun setStartYear(startYear: String?): ProjectDto {
        this.startYear = startYear
        return this
    }

    fun setEndYear(endYear: String?): ProjectDto {
        this.endYear = endYear
        return this
    }

    companion object {
        @JvmStatic
        fun from(project: Project): ProjectDto {
            return ProjectDto()
                .setId(project.id)
                .setTitle(project.title)
                .setDescription(project.description)
                .setTechnologies(project.technologies)
                .setGithubUrl(project.githubUrl)
                .setStartYear(project.startYear)
                .setEndYear(project.endYear)
        }
    }
}
