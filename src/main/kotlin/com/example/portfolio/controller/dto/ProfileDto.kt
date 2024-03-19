package com.example.portfolio.controller.dto

import com.example.portfolio.model.Profile
import com.example.portfolio.utils.LocalizedString
import java.time.LocalDate

class ProfileDto {
    var firstname: String? = null
        private set
    var lastname: String? = null
        private set
    var birthdate: LocalDate? = null
        private set
    var email: String? = null
        private set
    var phoneNumber: String? = null
        private set
    var biography: LocalizedString? = null
        private set
    var photoKey: String? = null
        private set

    fun setFirstname(firstname: String?): ProfileDto {
        this.firstname = firstname
        return this
    }

    fun setLastname(lastname: String?): ProfileDto {
        this.lastname = lastname
        return this
    }

    fun setBirthdate(birthdate: LocalDate?): ProfileDto {
        this.birthdate = birthdate
        return this
    }

    fun setEmail(email: String?): ProfileDto {
        this.email = email
        return this
    }

    fun setPhoneNumber(phoneNumber: String?): ProfileDto {
        this.phoneNumber = phoneNumber
        return this
    }

    fun setBiography(biography: LocalizedString?): ProfileDto {
        this.biography = biography
        return this
    }

    fun setPhotoKey(photoKey: String?): ProfileDto {
        this.photoKey = photoKey
        return this
    }

    companion object {
        fun from(profile: Profile): ProfileDto {
            return ProfileDto()
                .setFirstname(profile.firstname)
                .setLastname(profile.lastname)
                .setBirthdate(profile.birthdate)
                .setEmail(profile.email)
                .setPhoneNumber(profile.phoneNumber)
                .setBiography(profile.biography)
                .setPhotoKey(profile.photoKey)
        }
    }
}
