package com.example.portfolio.controller

import com.example.portfolio.PortfolioApplication
import com.example.portfolio.model.Profile
import com.example.portfolio.repository.ProfileRepository
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDate
import java.util.List

@SpringBootTest(classes = [PortfolioApplication::class])
@ActiveProfiles("test")
open class ProfileControllerTest {
    private var mockMvc: MockMvc? = null

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    @PostConstruct
    fun initialize() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.webApplicationContext)
            .build()
    }

    @BeforeEach
    fun setUp() {
        profileRepository!!.saveAll(
            List.of(
                Profile("John", "Doe", LocalDate.of(2000, 6, 1), "john.doe@test.com"),
                Profile("Mark", "Zuck", LocalDate.of(2000, 6, 1), "mark.zuck@test.com")
            )
        )
    }

    @Throws(Exception::class)
    @Transactional
    @Test
    open fun profileExists() {
            mockMvc!!.perform(MockMvcRequestBuilders.get("/profile/{email}", "john.doe@test.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("2000-06-01"))
        }

    @Throws(Exception::class)
    @Transactional
    @Test
    open fun profileNotExists() {
            mockMvc!!.perform(MockMvcRequestBuilders.get("/profile/{email}", "missing.person@test.com"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        }
}