package com.glyadgzl.womanwallpaper

import MainViewModel
import WomanItem
import WomanItemModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(onItemClick = {



            })
        }
    }
}
@Composable
fun MainScreen(onItemClick: (WomanItemModel) -> Unit) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF10022C)) // Koyu tema için arka plan rengi
                    .padding(paddingValues) // Scaffold içeriğini uygun hizalamak için
            ) {
                MainContent(onItemClick)
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(onItemClick: (WomanItemModel) -> Unit) {
    val viewModel = MainViewModel()
    val newMovies = remember { mutableStateListOf<WomanItemModel>() }
    var isLoadingNewMovies by remember { mutableStateOf(true) }

    var selectedMovie by remember { mutableStateOf<WomanItemModel?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadItems().observeForever { movies ->
            newMovies.clear()
            newMovies.addAll(movies)
            isLoadingNewMovies = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 80.dp, bottom = 200.dp)
    ) {
        if (isLoadingNewMovies) {
            LoadingIndicator()
        } else {
            MovieList(newMovies) { movie ->
                selectedMovie = movie
                coroutineScope.launch { sheetState.show() }
            }
        }
    }

    selectedMovie?.let { movie ->
        ModalBottomSheet(
            onDismissRequest = { coroutineScope.launch { sheetState.hide() } },
            sheetState = sheetState
        ) {
            BottomSheetContent(movie)
        }
    }
}
@Composable
fun BottomSheetContent(movie: WomanItemModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = movie.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.notable)

        Button(onClick = { /* Wallpaper yapma işlemi buraya eklenecek */ }) {
            Text("Wikipedia")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
/*
@Composable
fun MainContent(onItemClick: (WomanItemModel) -> Unit) {
    val viewModel = MainViewModel()
    val upcomingMovies = remember { mutableStateListOf<WomanItemModel>() }
    val newMovies = remember { mutableStateListOf<WomanItemModel>() }

    var isLoadingUpcoming by remember { mutableStateOf(true) }
    var isLoadingNewMovies by remember { mutableStateOf(true) }

    // Filmleri yüklem
    LaunchedEffect(Unit) {
        viewModel.loadUpcoming().observeForever { movies ->
            upcomingMovies.clear()
            upcomingMovies.addAll(movies)
            isLoadingUpcoming = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadItems().observeForever { movies ->
            newMovies.clear()
            newMovies.addAll(movies)
            isLoadingNewMovies = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 80.dp, bottom = 200.dp)
    ) {
        // Başlık




        if (isLoadingNewMovies) {
            LoadingIndicator()
        } else {
            MovieList(newMovies, onItemClick)
        }





    }
}
*/
@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieList(movies: List<WomanItemModel>, onItemClick: (WomanItemModel) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp), // Öğeler arası boşluk
        contentPadding = PaddingValues(50.dp)
    ) {
        items(movies) { movie ->
            WomanItem(movie, onItemClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    val sampleMovies = listOf(
        WomanItemModel(1798, 78,"Gülay","R.drawable.ic_launcher_background.xml",
            12.toString(), 41.toString()
        )
    )

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF4B1E9D))) {


        MovieList(sampleMovies, onItemClick = {})
    }
}
