package io.iljapavlovs.cdc.springcloudcontractdemo.provider

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid


@RestController
@RequestMapping("/persons")
class PersonController(
    private val personRepository: PersonRepository
) {
    @PostMapping
    fun createPerson(@RequestBody @Valid personRequestDto: PersonRequestDto): ResponseEntity<PersonResponseDto> {
        val savedPerson: PersonEntity = personRepository.save(personRequestDto.toEntity(UUID.randomUUID().toString()))
        return ResponseEntity
            .status(201)
            .body(savedPerson.toDto())
    }

    @PutMapping("/{id}")
    fun updatePerson(
        @RequestBody @Valid person: PersonEntity,
        @PathVariable id: String
    ): ResponseEntity<PersonResponseDto> {
        val personFromDb: PersonEntity =
            personRepository.findByIdOrNull(id) ?: throw RuntimeException("Person with $id not found")
        val updatedPerson = personFromDb.copy(
            name = person.name,
            ssn = person.ssn
        )
        return personRepository.save(updatedPerson)
            .let {
                ResponseEntity.ok(it.toDto())
            }
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable("id") id: String): ResponseEntity<PersonEntity> {
        return ResponseEntity.ok(personRepository.findById(id).get())
    }

}