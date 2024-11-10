package com.example.githubusersearcher.data.remote

import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.data.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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


    /**
     * Retrieves detailed information about a specific GitHub user.
     *
     * @param username The username of the GitHub user to retrieve details for.
     * @return A [UserDetailResponse] object containing the user's detailed information.
     */
    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ) : UserDetailResponse


}