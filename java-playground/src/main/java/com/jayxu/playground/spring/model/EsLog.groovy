package com.jayxu.playground.spring.model

import org.springframework.data.annotation.Id

//@Document(indexName = "nginx-blockchain-2021.08.23.09-000006", createIndex = false)
class EsLog {
    @Id
    String id
}
