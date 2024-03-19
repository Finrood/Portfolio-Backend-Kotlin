package com.example.portfolio.repository

import com.example.portfolio.model.Project
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : CrudRepository<Project?, String?> {
    override fun findAll(): List<Project?>
}
