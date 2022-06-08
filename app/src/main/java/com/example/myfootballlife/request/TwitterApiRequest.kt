package com.example.myfootballlife.request

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitterApiRequest @Inject constructor(
    val retrofit:TwitterApiService
)