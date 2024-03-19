package com.example.portfolio.service

import com.example.portfolio.controller.support.ResourceNotFoundException
import com.example.portfolio.model.Profile
import com.example.portfolio.repository.ProfileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProfileManager(private val profileRepository: ProfileRepository) {

    @Transactional(readOnly = true)
    open fun getProfile(email: String?): Profile {
        return profileRepository.findByEmail(email)
            ?: throw ResourceNotFoundException("No profile with email [$email] was found")
    }
}
