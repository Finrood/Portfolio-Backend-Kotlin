package com.example.portfolio.model

import com.example.portfolio.model.support.LocalizedStringJpaConverter
import com.example.portfolio.utils.LocalizedString
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
class Profile {
    @Id
    var id: String? = null
        private set

    @Column(nullable = false)
    var firstname: String? = null
        private set

    @Column(nullable = false)
    var lastname: String? = null
        private set

    @Column(nullable = false)
    var birthdate: LocalDate? = null
        private set

    @Column(nullable = false, unique = true)
    var email: String? = null
        private set
    @JvmField
    var phoneNumber: String? = null

    @JvmField
    @Column(columnDefinition = "TEXT")
    @Convert(converter = LocalizedStringJpaConverter::class)
    var biography: LocalizedString? = null

    @JvmField
    @Lob
    @Column
    var photoKey: String? = null

    protected constructor()

    constructor(firstname: String?, lastname: String?, birthdate: LocalDate?, email: String?) {
        this.id = UUID.randomUUID().toString()
        this.firstname = firstname
        this.lastname = lastname
        this.birthdate = birthdate
        this.email = email
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val project = o as Profile
        return id == project.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "Profile{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", biography=" + biography +
                ", photoKey='" + photoKey + '\'' +
                '}'
    }
}
