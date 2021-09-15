package com.bsf.moneytransfer.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * Swagger configuration
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@Configuration
class SpringFoxConfig {

    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis( RequestHandlerSelectors.basePackage( "com.bsf.moneytransfer" ) )
            .paths(PathSelectors.any())
            .build()
}