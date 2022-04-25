package andrefigas.com.github.myapplication.data

import andrefigas.com.github.myapplication.data.api.Output
import andrefigas.com.github.myapplication.data.api.SwApi
import andrefigas.com.github.myapplication.data.api.parseResponse
import andrefigas.com.github.myapplication.data.model.FilmResponse
import andrefigas.com.github.myapplication.data.model.SpecieResponse
import andrefigas.com.github.myapplication.data.model.StarshipResponse
import andrefigas.com.github.myapplication.data.model.VehicleResponse
import andrefigas.com.github.myapplication.domain.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SwRepositoryIml(private val service: SwApi) : SwRepository {

    companion object {
        const val REQUEST_SIZE = 5
    }

    var lastId = 1

    override suspend fun getPeople(): List<Person> {
        val newLastId = lastId + REQUEST_SIZE
        val requests = (lastId..newLastId).map { id ->
            CoroutineScope(Dispatchers.IO).async {
                getPersonById(id)
            }
        }

        lastId = newLastId
        lastId++
        return requests.mapNotNull {
            try {
                it.await()
            } catch (e: GetPersonError) {
                e.printStackTrace()
                null
            }

        }
    }

    private suspend fun getPersonById(id: Int): Person {

        return when (val person = service.getPersonById(id).parseResponse()) {
            is Output.Success -> {
                val response = person.value

                val homeWorld =
                    if (response.homeworld.isNullOrEmpty()) null
                    else when (val homeWorld =
                        service.getPlanetByUrl(response.homeworld).parseResponse()) {
                        is Output.Success -> homeWorld.value.toPlanet()
                        is Output.Failure -> throw GetPersonError()
                    }

                val films = response.films.map { service.getFilmByUrl(it).parseResponse() }
                    .filterIsInstance<Output.Success<FilmResponse>>().map { it.value.toFilm() }

                val species = response.species.map { service.getSpecieByUrl(it).parseResponse() }
                    .filterIsInstance<Output.Success<SpecieResponse>>().map { it.value.toSpecie() }

                val starships =
                    response.starships.map { service.getStarshipByUrl(it).parseResponse() }
                        .filterIsInstance<Output.Success<StarshipResponse>>()
                        .map { it.value.toStarship() }

                val vehicles = response.vehicles.map { service.getVehicleByUrl(it).parseResponse() }
                    .filterIsInstance<Output.Success<VehicleResponse>>()
                    .map { it.value.toVehicle() }

                response.toPerson(
                    homeWorld,
                    films,
                    species,
                    starships,
                    vehicles
                )
            }
            is Output.Failure -> throw GetPersonError()
        }

    }


}

interface SwRepository {

    suspend fun getPeople(): List<Person>
}

class GetPersonError : Exception()

