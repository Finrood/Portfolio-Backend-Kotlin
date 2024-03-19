package com.example.portfolio.repository

import com.example.portfolio.model.Profile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProfileRepository : CrudRepository<Profile?, String?> {
    fun findByEmail(email: String?): Profile?
}
