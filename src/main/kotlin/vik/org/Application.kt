package vik.org

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Mono
import java.util.function.Function
import java.util.function.Supplier

@SpringBootApplication
//@EnableConfigurationProperties(value = [ApplicationConfig::class])
class Application {
  private val log = LoggerFactory.getLogger(this::class.java)

  @Bean
  fun objectMapper(): ObjectMapper {
    return obSupplier.get()
  }


  @Bean
  // method that is being called by the lambda function
  fun process(): Function<String, Mono<String>> {
    return Function<String, Mono<String>> { value ->
      Mono.just("Done")
    }
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      SpringApplication.run(Application::class.java, *args)
    }


    val obSupplier = Supplier<ObjectMapper> {
      jacksonObjectMapper()
        .findAndRegisterModules()

    }
  }
}


data class Sample(
  val name: String,
)

