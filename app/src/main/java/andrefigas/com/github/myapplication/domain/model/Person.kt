package andrefigas.com.github.myapplication.domain.model

data class Person(
    val id: Int,
    val name: String,
    val homeworld: Planet?,
    val films: List<Film>,
    val species: List<Specie>,
    val starships: List<Starship>,
    val vehicles: List<Vehicle>
)
