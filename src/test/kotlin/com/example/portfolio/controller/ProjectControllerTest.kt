package com.example.portfolio.controller

import com.example.portfolio.PortfolioApplication
import com.example.portfolio.controller.dto.ProjectDto
import com.example.portfolio.model.Project
import com.example.portfolio.model.Technology
import com.example.portfolio.repository.ProjectRepository
import com.example.portfolio.utils.LocalizedString
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import java.util.*
import java.util.stream.Collectors

@SpringBootTest(classes = [PortfolioApplication::class])
@ActiveProfiles("test")
open class ProjectControllerTest {

    private val savedProjects = ArrayList<Project>()
    private val objectMapper: ObjectMapper = ObjectMapper()
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()

        val savedP1 = projectRepository.save(
            Project(
                LocalizedString().setTranslations(
                    mapOf(
                        Locale.ENGLISH to "title1",
                        Locale.FRENCH to "titre1"
                    )
                ),
                "2000"
            )
                .setDescription(
                    LocalizedString().setTranslations(
                        mapOf(
                            Locale.ENGLISH to "descren1",
                            Locale.FRENCH to "descrfr1"
                        )
                    )
                )
                .setGithubUrl("www.gittest.com/project1")
                .setEndYear("2001")
        )
            .addTechnologies(Technology.JAVA, Technology.AI, Technology.SQL)

        val savedP2 = projectRepository.save(
            Project(
                LocalizedString().setTranslations(
                    mapOf(
                        Locale.ENGLISH to "title2",
                        Locale.FRENCH to "titre2"
                    )
                ),
                "2001"
            )
                .setDescription(
                    LocalizedString().setTranslations(
                        mapOf(
                            Locale.ENGLISH to "descren2",
                            Locale.FRENCH to "descrfr2"
                        )
                    )
                )
                .setGithubUrl("www.gittest.com/project2")
                .addTechnologies(Technology.PYTHON)
        )

        val savedP3 = projectRepository.save(
            Project(
                LocalizedString().setFallbackText("title3"),
                "2002"
            )
                .setDescription(
                    LocalizedString().setTranslations(
                        mapOf(
                            Locale.ENGLISH to "descren3",
                            Locale.FRENCH to "descrfr3"
                        )
                    )
                )
                .setGithubUrl("www.gittest.com/project3")
                .setEndYear("2003")
                .addTechnologies(Technology.JAVA, Technology.AI)
        )

        savedProjects.add(savedP1)
        savedProjects.add(savedP2)
        savedProjects.add(savedP3)
    }

    @Test
    @Transactional
    open fun getProjects() {
        val mvcResult: ResultActions = mockMvc.perform(get("/projects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", Matchers.iterableWithSize<Int>(3)))

        val projectDto = objectMapper.readValue(
            mvcResult.andReturn().response.contentAsString,
            object : TypeReference<List<ProjectDto>>() {}
        )

        assertThat(projectDto.size).isEqualTo(3)

        assertThat(
            projectDto.stream()
                .map { it.title }
                .map { Pair(it?.translations, it?.fallbackText) }
                .collect(Collectors.toSet())
        ).allMatch { (dtoTranslations, dtoFallbackText) ->
            savedProjects.stream()
                .map { Pair(it.title?.translations, it.title?.fallbackText) }
                .anyMatch { (projectTranslations, projectFallbackText) ->
                    dtoTranslations == projectTranslations && dtoFallbackText == projectFallbackText
                }
        }
    }

    @Test
    @Transactional
    open fun getProjectExists() {
        val mvcResult: ResultActions = mockMvc.perform(get("/project/{projectId}", savedProjects[0].id))
            .andExpect(status().isOk())

        val projectDto = objectMapper.readValue(
            mvcResult.andReturn().response.contentAsString,
            ProjectDto::class.java
        )

        assertThat(projectDto).isNotNull()

        assertThat(projectDto.githubUrl).isEqualTo(savedProjects[0].githubUrl)
        assertThat(projectDto.startYear).isEqualTo(savedProjects[0].startYear)
        assertThat(projectDto.endYear).isEqualTo(savedProjects[0].endYear)
        assertThat(projectDto.technologies).isEqualTo(savedProjects[0].technologies)
        assertThat(projectDto.title).isEqualTo(savedProjects[0].title)
        assertThat(projectDto.description).isEqualTo(savedProjects[0].description)
    }

    @Test
    @Transactional
    open fun getProjectNotExists() {
        mockMvc.perform(get("/project/{projectId}", "notAnId"))
            .andExpect(status().isNotFound)
    }

    @Test
    @Transactional
    open fun createProjectWithAllAttributes() {
        val projectDto = ProjectDto()
            .setTitle(LocalizedString().setTranslations(mapOf(Locale.ENGLISH to "testen", Locale.FRENCH to "testfr")))
            .setDescription(LocalizedString().setFallbackText("description fallback"))
            .setStartYear("2000")
            .setEndYear("2000")
            .setGithubUrl("test@gittest.com")
            .setTechnologies(listOf(Technology.AI, Technology.JAVA, Technology.PYTHON))

        val mvcResult: ResultActions = mockMvc.perform(
            post("/project/create")
                .content(objectMapper.writeValueAsString(projectDto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())

        val savedProjectDto = objectMapper.readValue(
            mvcResult.andReturn().response.contentAsString,
            ProjectDto::class.java
        )

        assertThat(savedProjectDto).isNotNull()
        assertThat(savedProjectDto.id).isNotNull()
        assertThat(projectRepository.findById(savedProjectDto.id!!)).isNotEmpty

        val savedProject = projectRepository.findById(savedProjectDto.id!!).get()

        assertThat(savedProjectDto.githubUrl).isEqualTo(projectDto.githubUrl)
        assertThat(savedProjectDto.startYear).isEqualTo(projectDto.startYear)
        assertThat(savedProjectDto.endYear).isEqualTo(projectDto.endYear)
        assertThat(savedProjectDto.technologies).isEqualTo(projectDto.technologies)
        assertThat(savedProjectDto.title).isEqualTo(projectDto.title)
        assertThat(savedProjectDto.description).isEqualTo(projectDto.description)

        assertThat(savedProjectDto.id).isEqualTo(savedProject.id)
        assertThat(savedProjectDto.githubUrl).isEqualTo(savedProject.githubUrl)
        assertThat(savedProjectDto.startYear).isEqualTo(savedProject.startYear)
        assertThat(savedProjectDto.endYear).isEqualTo(savedProject.endYear)
        assertThat(savedProjectDto.technologies).isEqualTo(savedProject.technologies)
        assertThat(savedProjectDto.title).isEqualTo(savedProject.title)
        assertThat(savedProjectDto.description).isEqualTo(savedProject.description)
    }
}
