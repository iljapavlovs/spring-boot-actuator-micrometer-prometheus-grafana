package io.iljapavlovs.cdc.springcloudcontractdemo.provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProviderApplication

fun main(args: Array<String>) {
    runApplication<ProviderApplication>(*args)
}
