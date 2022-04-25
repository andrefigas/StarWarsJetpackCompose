package andrefigas.com.github.myapplication

import andrefigas.com.github.myapplication.data.SwRepository
import andrefigas.com.github.myapplication.data.SwRepositoryIml
import andrefigas.com.github.myapplication.data.api.Service
import andrefigas.com.github.myapplication.data.api.SwApi
import andrefigas.com.github.myapplication.domain.GetPeople
import andrefigas.com.github.myapplication.domain.GetPeopleUseCase
import andrefigas.com.github.myapplication.presenter.PeopleViewModel

import org.koin.dsl.module

val mealServiceModule = module {

    single { Service().createService(SwApi::class.java) }

    single<SwRepository> { SwRepositoryIml(get()) }

    single<GetPeopleUseCase> { GetPeople(get()) }

    single { PeopleViewModel(get()) }
}