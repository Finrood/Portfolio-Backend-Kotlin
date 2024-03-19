package com.example.portfolio.controller

import com.example.portfolio.controller.dto.ProjectDto
import com.example.portfolio.controller.dto.ProjectDto.Companion.from
import com.example.portfolio.model.Project
import com.example.portfolio.service.ProjectManager
import org.springframework.web.bind.annotation.*

@RestController
class ProjectController(private val projectManager: ProjectManager) {
    @GetMapping(value = ["/project/{projectId}"])
    fun getProject(@PathVariable projectId: String?): ProjectDto {
        return toDto(projectManager.getProject(projectId))
    }

    @GetMapping(value = ["/projects"])
    fun findProjects(): List<ProjectDto> {
        return projectManager.getAllProject().stream()
            .map { project: Project -> this.toDto(project) }
            .toList()
    }

    @PostMapping(value = ["/project/create"])
    fun createProject(@RequestBody projectDto: ProjectDto): ProjectDto {
        return toDto(projectManager.createProject(projectDto))
    }

    private fun toDto(project: Project): ProjectDto {
        return from(project)
    }
}
