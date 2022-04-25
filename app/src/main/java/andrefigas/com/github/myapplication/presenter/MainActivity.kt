package andrefigas.com.github.myapplication.presenter

import andrefigas.com.github.myapplication.R
import andrefigas.com.github.myapplication.presenter.model.PersonUIModel
import andrefigas.com.github.myapplication.presenter.theme.MyApplicationTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PeopleViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                LiveDataComponent(viewModel.people)
            }
        }

        viewModel.getPeople()
    }

    @Composable
    fun LiveDataLoadingComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
        }
    }

    @Composable
    fun PersonListItem(person: PersonUIModel) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = person.id.toString(),
                        style = typography.caption,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    Text(text = person.name, style = typography.h6)

                    if (person.homeworld != null) {
                        Text(
                            text = getString(R.string.homeworld),
                            style = typography.caption,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(text = person.homeworld.name, style = typography.caption)
                    }

                    if (person.films.isNotEmpty()) {
                        Text(
                            text = getString(R.string.films),
                            style = typography.caption,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }

                    person.films.forEach { film ->
                        Text(text = film.title, style = typography.caption)
                    }

                    if (person.vehicles.isNotEmpty()) {
                        Text(
                            text = getString(R.string.veichles),
                            style = typography.caption,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }

                    person.vehicles.forEach { vehicle ->
                        Text(text = vehicle.name, style = typography.caption)
                    }

                    if (person.species.isNotEmpty()) {
                        Text(
                            text = getString(R.string.specie),
                            style = typography.caption,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }

                    person.species.forEach { specie ->
                        Text(text = specie.name, style = typography.caption)
                    }

                    if (person.starships.isNotEmpty()) {
                        Text(
                            text = getString(R.string.starship),
                            style = typography.caption,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }

                    person.starships.forEach { starship ->
                        Text(text = starship.name, style = typography.caption)
                    }
                }
            }
        }

    }

    @Composable
    fun LiveDataComponent(personListLiveData: LiveData<List<PersonUIModel>>) {
        val personList by personListLiveData.observeAsState(initial = emptyList())
        if (personList.isEmpty()) {
            LiveDataLoadingComponent()
        } else {
            LiveDataComponentList(personList)
        }
    }

    @Composable
    fun LiveDataComponentList(personList: List<PersonUIModel>) {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(
                items = personList, itemContent = { person ->
                    PersonListItem(person)
                })
        }

        listState.OnBottomReached {
            // do on load more
            viewModel.getPeople()
        }
    }

    @Composable
    fun LazyListState.OnBottomReached(
        loadMore: () -> Unit
    ) {
        val shouldLoadMore = remember {
            derivedStateOf {
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                    ?: return@derivedStateOf true

                lastVisibleItem.index == layoutInfo.totalItemsCount - 1
            }
        }

        LaunchedEffect(shouldLoadMore) {
            snapshotFlow { shouldLoadMore.value }
                .collect {
                    if (it) loadMore()
                }
        }
    }


}