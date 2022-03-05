package com.arindom.koa2.infra.services.ktor

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

val client_gson = HttpClient(Android) {
    defaultRequest {
        with(url) {
            protocol = URLProtocol.HTTPS
            host = "www.omdbapi.com"
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }
    install(JsonFeature) {
        serializer = GsonSerializer {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }
    /* install(Logging) {
         logger = Logger.ANDROID
         level = LogLevel.BODY
     }*/
}

val client_kotlin_serializer = HttpClient(Android) {
    defaultRequest {
        with(url) {
            protocol = URLProtocol.HTTPS
            host = "www.omdbapi.com"
            parameters["apikey"] = "2f7b1663"
        }
    }
    // not required if serializing via Json.decodeToString(string)
    install(JsonFeature) {
         serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
//             prettyPrint = true
             isLenient = true
             encodeDefaults = true
             useAlternativeNames = true
             ignoreUnknownKeys = true
         })
    }
     install(Logging) {
         logger = Logger.ANDROID
         level = LogLevel.BODY
     }
}



