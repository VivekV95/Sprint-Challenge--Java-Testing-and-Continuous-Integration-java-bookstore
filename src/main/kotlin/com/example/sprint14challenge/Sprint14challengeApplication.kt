package com.example.sprint14challenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
@EnableSwagger2
class Sprint14challengeApplication

fun main(args: Array<String>) {
    runApplication<Sprint14challengeApplication>(*args)
}
