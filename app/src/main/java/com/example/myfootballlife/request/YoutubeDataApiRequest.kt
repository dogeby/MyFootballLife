package com.example.myfootballlife.request

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDataApiRequest @Inject constructor(
    val retrofit:YoutubeDataService
)