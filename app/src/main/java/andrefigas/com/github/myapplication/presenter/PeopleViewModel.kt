package andrefigas.com.github.myapplication.presenter

import andrefigas.com.github.myapplication.data.toUiModel
import andrefigas.com.github.myapplication.domain.GetPeopleUseCase
import andrefigas.com.github.myapplication.presenter.model.PersonUIModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PeopleViewModel(private val getPeopleUseCase: GetPeopleUseCase) : ViewModel() {

    private val _people = MutableLiveData<List<PersonUIModel>>()
    val people = _people as LiveData<List<PersonUIModel>>

    fun getPeople() {
        viewModelScope.launch {
            val newData = getPeopleUseCase().map { it.toUiModel() }
            _people.value =
                if (_people.value.isNullOrEmpty()) newData else _people.value?.plus(newData)
            print(_people.value)
        }
    }
}