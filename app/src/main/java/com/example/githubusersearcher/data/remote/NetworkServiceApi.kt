package com.example.githubusersearcher.data.remote

import com.example.githubusersearcher.data.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServiceApi {
    /**
     * Retrieves a list of users matching the specified search query.
     *
     * This function makes a GET request to the "search/users" endpoint with the provided
     * search query as a query parameter named "q". It returns a [SearchResponse] object
     * containing the search results.
     *
     * The function is a suspending function, which means it can be paused and resumed
     * without blocking the main thread. This is important for network operations, which
     * can take some time to complete.
     *
     * @param q The search query string.
     * @return A [SearchResponse] object containing the search results.
     */
    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : SearchResponse


}