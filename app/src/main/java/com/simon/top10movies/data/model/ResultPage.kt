package com.simon.top10movies.data.model

// I decided to make this model class generic since a lot of responses from The Movie DB API has this structure,
// so if I ever want to add reviews, popular tv shows, or something else, I can reuse this generic model.

data class ResultPage<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)