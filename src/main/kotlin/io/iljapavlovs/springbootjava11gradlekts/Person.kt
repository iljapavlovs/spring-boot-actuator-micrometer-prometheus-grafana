package io.iljapavlovs.cdc.springcloudcontractdemo.provider

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

data class PersonRequestDto(
    val name: String,
    val ssn: String
)

data class PersonResponseDto(
    val id: String,
    val name: String,
    val ssn: String
)


data class PersonMessage(
    val name: String,
    val ssn: String
//     val eventTime: LocalDateTime
)

/**
 * Needed to add Kotlin JPA plugin
 */
@Entity
data class PersonEntity(
    @Id
    val id: String,

    @Column
    @NotNull
    var name: String,

    @Column
    @NotNull
    var ssn: String
) {

     fun updateFrom(person: PersonEntity) {
          name = person.name
          ssn = person.ssn
     }
}


fun PersonRequestDto.toEntity(id: String): PersonEntity =
     PersonEntity(
          id = id,
          name = name,
          ssn = ssn
     )

fun PersonEntity.toDto(): PersonResponseDto =
     PersonResponseDto(
          id = id,
          name = name,
          ssn = ssn
     )