package tech.kaomidev.springboot.helloworld.api

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/hello")
class HelloController {

    private val counter = AtomicLong()

    @GetMapping
    fun helloWorld(@RequestParam name: String): Map<String, Any> {
        return mapOf("user" to name, "visitor" to counter.incrementAndGet())
    }
}