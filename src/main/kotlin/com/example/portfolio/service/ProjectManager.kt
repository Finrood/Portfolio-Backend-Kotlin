package com.example.portfolio.service

import com.example.portfolio.controller.dto.ProjectDto
import com.example.portfolio.controller.support.ResourceNotFoundException
import com.example.portfolio.model.Project
import com.example.portfolio.repository.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProjectManager(private val projectRepository: ProjectRepository) {

    @Transactional(readOnly = true)
    fun getProject(projectId: String?): Project {
        return projectRepository.findByIdOrNull(projectId)
            ?: throw ResourceNotFoundException("Project with id [$projectId] not found")
    }

    @Transactional(readOnly = true)
    fun getAllProject(): List<Project> {
        return projectRepository.findAll().filterNotNull()
    }

    @Transactional
    fun createProject(projectDto: ProjectDto): Project {
        return projectRepository.save(
            Project(projectDto.title, projectDto.startYear)
                .apply {
                    description = projectDto.description
                    githubUrl = projectDto.githubUrl
                    endYear = projectDto.endYear
                    addTechnologies(*projectDto.technologies.toTypedArray())
                }
        )
    }
}
