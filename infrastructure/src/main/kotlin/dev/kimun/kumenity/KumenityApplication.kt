package dev.kimun.kumenity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KumenityApplication

fun main(args: Array<String>) {
	runApplication<KumenityApplication>(*args)
}
