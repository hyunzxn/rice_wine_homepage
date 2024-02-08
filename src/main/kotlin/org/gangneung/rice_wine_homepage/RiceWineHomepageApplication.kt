package org.gangneung.rice_wine_homepage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class RiceWineHomepageApplication

fun main(args: Array<String>) {
    runApplication<RiceWineHomepageApplication>(*args)
}
