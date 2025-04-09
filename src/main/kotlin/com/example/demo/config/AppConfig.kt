package com.example.demo.config

import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    companion object {
        const val DELAY = 100L
        const val USE_DELAY = false
        const val TRANSACTION_TIMEOUT = 1
    }
}
