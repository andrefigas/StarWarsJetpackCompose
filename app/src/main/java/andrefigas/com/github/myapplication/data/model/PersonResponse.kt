package andrefigas.com.github.myapplication.data.model

data class PersonResponse(
    val url: String,
    val name: String,
    val homeworld: String?,
    val films: List<String>,
    val species: List<String>,
    val starships: List<String>,
    val vehicles: List<String>
)