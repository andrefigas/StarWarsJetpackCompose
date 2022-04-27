package andrefigas.com.github.myapplication.presenter

import andrefigas.com.github.myapplication.R
import andrefigas.com.github.myapplication.presenter.model.PersonUIModel
import andrefigas.com.github.myapplication.presenter.theme.MyApplicationTheme
import andrefigas.com.github.myapplication.presenter.theme.Shade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
            backgroundColor = ColorUtils.getLightColorTheme(person.name),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                Row() {
                    Text(
                        text = person.id.toString(),
                        style = typography.caption,
                        modifier = Modifier
                            .padding(top = 5.dp, end = 5.dp, bottom = 5.dp)
                            .align(CenterVertically)
                    )

                    Text(
                        text = person.name,
                        style = typography.h6,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )

                }

                if (person.homeworld != null) {

                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(9.dp, 9.dp, 9.dp, 9.dp),
                        // or shape = CircleShape
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black,
                            backgroundColor = Shade
                        )
                    ) {


                        Row() {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_language_24),
                                "homewolrd"
                            )
                            Text(
                                text = person.homeworld.name,
                                modifier = Modifier.absolutePadding(left = 8.dp)
                            )
                        }
                    }
                }

                if (person.films.isNotEmpty()) {

                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = stringResource(R.string.films),
                        fontWeight = FontWeight.Bold,
                    )

                    ChipVerticalGrid(
                        spacing = 7.dp,
                        modifier = Modifier
                    ) {
                        person.films.forEach { film ->

                            OutlinedButton(
                                onClick = { },
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(9.dp, 9.dp, 9.dp, 9.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    backgroundColor = Shade
                                )
                            ) {
                                Text(
                                    text = film.title,
                                    style = typography.caption,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                )
                            }

                        }
                    }

                }

                if (person.vehicles.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = stringResource(R.string.veichles),
                        fontWeight = FontWeight.Bold,
                    )

                    ChipVerticalGrid(
                        spacing = 7.dp,
                        modifier = Modifier
                    ) {
                        person.vehicles.forEach { vehicle ->

                            OutlinedButton(
                                onClick = { },
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(9.dp, 9.dp, 9.dp, 9.dp),
                                // or shape = CircleShape
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    backgroundColor = Shade
                                )
                            ) {
                                Text(
                                    text = vehicle.name,
                                    style = typography.caption,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                )
                            }

                        }
                    }
                }

                if (person.species.isNotEmpty()) {

                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = stringResource(R.string.specie),
                        fontWeight = FontWeight.Bold,
                    )

                    ChipVerticalGrid(
                        spacing = 7.dp,
                        modifier = Modifier
                    ) {
                        person.species.forEach { specie ->

                            OutlinedButton(
                                onClick = { },
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(9.dp, 9.dp, 9.dp, 9.dp),
                                // or shape = CircleShape
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    backgroundColor = Shade
                                )
                            ) {
                                Text(
                                    text = specie.name,
                                    style = typography.caption,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                )
                            }

                        }
                    }
                }


                if (person.starships.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = stringResource(R.string.starship),
                        fontWeight = FontWeight.Bold,
                    )

                    ChipVerticalGrid(
                        spacing = 7.dp,
                        modifier = Modifier
                    ) {
                        person.starships.forEach { starship ->

                            OutlinedButton(
                                onClick = { },
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(9.dp, 9.dp, 9.dp, 9.dp),
                                // or shape = CircleShape
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    backgroundColor = Shade
                                )
                            ) {
                                Text(
                                    text = starship.name,
                                    style = typography.caption,
                                    modifier = Modifier
                                        .padding(all = 8.dp)
                                )
                            }

                        }
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

    @OptIn(ExperimentalFoundationApi::class)
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

    @Composable
    fun ChipVerticalGrid(
        modifier: Modifier = Modifier,
        spacing: Dp,
        content: @Composable () -> Unit
    ) {
        Layout(
            content = content,
            modifier = modifier
        ) { measurables, constraints ->
            var currentRow = 0
            var currentOrigin = IntOffset.Zero
            val spacingValue = spacing.toPx().toInt()
            val placeables = measurables.map { measurable ->
                val placeable = measurable.measure(constraints)

                if (currentOrigin.x > 0f && currentOrigin.x + placeable.width > constraints.maxWidth) {
                    currentRow += 1
                    currentOrigin = currentOrigin.copy(
                        x = 0,
                        y = currentOrigin.y + placeable.height + spacingValue
                    )
                }

                placeable to currentOrigin.also {
                    currentOrigin = it.copy(x = it.x + placeable.width + spacingValue)
                }
            }

            layout(
                width = constraints.maxWidth,
                height = placeables.lastOrNull()?.run { first.height + second.y } ?: 0
            ) {
                placeables.forEach {
                    val (placeable, origin) = it
                    placeable.place(origin.x, origin.y)
                }
            }
        }
    }


}