package io.iljapavlovs.cdc.springcloudcontractdemo.provider

import org.springframework.data.repository.CrudRepository


interface PersonRepository : CrudRepository<PersonEntity?, String?>