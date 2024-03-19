package com.example.portfolio.controller

import com.example.portfolio.controller.dto.ProfileDto
import com.example.portfolio.model.Profile
import com.example.portfolio.service.ProfileManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(private val profileManager: ProfileManager) {
    @GetMapping(value = ["/profile/{email}"])
    fun getProfile(@PathVariable email: String?): ProfileDto {
        return toDto(profileManager.getProfile(email))
    }

    private fun toDto(profile: Profile): ProfileDto {
        return ProfileDto.from(profile)
    }
}
