package com.zalesskyi.domain.models

const val DEFAULT_LIMIT = 10

data class Page(val limit: Int, val offset: Int)