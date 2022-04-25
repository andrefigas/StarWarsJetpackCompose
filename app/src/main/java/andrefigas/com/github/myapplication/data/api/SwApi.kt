package andrefigas.com.github.myapplication.data.api

import andrefigas.com.github.myapplication.data.model.*
import andrefigas.com.github.myapplication.domain.model.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface SwApi {
    @GET("people/{id}")
    suspend fun getPersonById(@Path("id") id: Int): Response<PersonResponse>

    @GET
    suspend fun getFilmByUrl(@Url url: String): Response<FilmResponse>

    @GET
    suspend fun getPlanetByUrl(@Url url: String): Response<PlanetResponse>

    @GET
    suspend fun getSpecieByUrl(@Url url: String): Response<SpecieResponse>

    @GET
    suspend fun getStarshipByUrl(@Url url: String): Response<StarshipResponse>

    @GET
    suspend fun getVehicleByUrl(@Url url: String): Response<VehicleResponse>
}