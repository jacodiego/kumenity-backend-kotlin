package dev.kimun.kumenity.config

import dev.kimun.kumenity.arch.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["dev.kimun.kumenity"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = arrayOf(UseCase::class))]
)
class DomainConfig