package andrefigas.com.github.myapplication.data

import andrefigas.com.github.myapplication.data.model.*
import andrefigas.com.github.myapplication.domain.model.*
import andrefigas.com.github.myapplication.presenter.model.*

private fun getIdFromUrl(url: String): Int {
    return url.split("/").filter { it.isNotEmpty() }.let { it[it.size - 1].toInt() }
}

fun VehicleResponse.toVehicle() = Vehicle(getIdFromUrl(this.url), this.name)

fun FilmResponse.toFilm() = Film(getIdFromUrl(this.url), this.title)

fun PlanetResponse.toPlanet() = Planet(getIdFromUrl(this.url), this.name)

fun SpecieResponse.toSpecie() = Specie(getIdFromUrl(this.url), this.name)

fun StarshipResponse.toStarship() = Starship(getIdFromUrl(this.url), this.name)

fun PersonResponse.toPerson(homeWorld : Planet?,
                            films :  List<Film>,
                            species : List<Specie>,
                            starships : List<Starship>,
                            vehicles : List<Vehicle>) = Person(
    getIdFromUrl(url),
    name,
    homeWorld,
    films,
    species,
    starships,
    vehicles
)

fun Vehicle.toUIModel() = VehicleUIModel(id, name)

fun Film.toUIModel() = FilmUIModel(id, title)

fun Planet.toUIModel() = PlanetUIModel(id, name)

fun Specie.toUIModel() = SpecieUIModel(id, name)

fun Starship.toUIModel() = StarshipUIModel(id, name)

fun Person.toUiModel() = PersonUIModel(
    id,
    name,
    homeworld?.toUIModel(),
    films.map { it.toUIModel() },
    species.map { it.toUIModel() },
    starships.map { it.toUIModel() },
    vehicles.map { it.toUIModel() }
)

