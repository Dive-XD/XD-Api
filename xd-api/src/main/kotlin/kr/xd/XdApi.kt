package kr.xd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class XdApi

fun main(args: Array<String>) {
    runApplication<XdApi>(*args)
}
