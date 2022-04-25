package andrefigas.com.github.myapplication.presenter.model

data class PersonUIModel(
    val id: Int,
    val name: String,
    val homeworld: PlanetUIModel?,
    val films: List<FilmUIModel>,
    val species: List<SpecieUIModel>,
    val starships: List<StarshipUIModel>,
    val vehicles: List<VehicleUIModel>
)
