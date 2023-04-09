package com.timestampmicroservice.controller

import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.awt.PageAttributes


@Controller
class HomeController{

    @GetMapping("/")
    @ResponseBody
    fun welcomeAsHTML(): String? {
        return """
             <html>
             <header><title>Timestamp</title></header>
             <body>
             Timestamp Microservice
             </body>
             </html>
             """.trimIndent()
    }
}