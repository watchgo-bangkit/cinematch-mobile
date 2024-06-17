package com.example.cinematch.data

data class MovieDetailResponse(
    val data: MovieDetail
)
data class MovieDetail(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<MovieGenre>,
    val homepage: String,
    val imdb_id: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val credits: Credits
)

data class MovieGenre(
    val id: Int,
    val name: String
)

data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val director: Director
)

data class Cast(
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val name: String,
    val popularity: Double,
    val profile_path: String?,
    val character: String
)

data class Director(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val credit_id: String,
    val department: String,
    val job: String
)
