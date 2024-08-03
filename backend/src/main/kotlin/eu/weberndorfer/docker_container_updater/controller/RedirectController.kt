package eu.weberndorfer.docker_container_updater.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RedirectController {
    @GetMapping(value = ["/{regex:\\w+}", "/**/{regex:\\w+}", "/**/{regex:\\w+/}"])
    fun forward(): String {
        return "forward:/"
    }
}