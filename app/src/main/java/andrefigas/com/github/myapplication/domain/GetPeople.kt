package andrefigas.com.github.myapplication.domain

import andrefigas.com.github.myapplication.data.SwRepository
import andrefigas.com.github.myapplication.domain.model.Person


class GetPeople( private val repository: SwRepository) : GetPeopleUseCase {
    override suspend fun invoke(): List<Person> = repository.getPeople()
}

interface GetPeopleUseCase {

    suspend operator fun invoke(): List<Person>

}