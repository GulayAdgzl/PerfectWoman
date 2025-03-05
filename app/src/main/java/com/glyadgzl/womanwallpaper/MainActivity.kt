package com.glyadgzl.womanwallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.glyadgzl.womanwallpaper.ui.theme.WomanWallpaperTheme

class MainActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(onItemClick = {item->
                val intent= Intent(this, DetailActivity::class.java)
                intent.putExtra("object",item)
                startActivity(intent)


            })
        }
    }
}

@Composable
fun MainScreen(onItemClick: (FilmItemModel) -> Unit) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121212)) // Koyu tema için arka plan rengi
                    .padding(paddingValues) // Scaffold içeriğini uygun hizalamak için
            ) {
                MainContent(onItemClick)
            }
        }
    )
}
@Composable
fun MainContent(onItemClick: (FilmItemModel) -> Unit) {
    val viewModel = MainViewModel()
    val upcomingMovies = remember { mutableStateListOf<FilmItemModel>() }
    val newMovies = remember { mutableStateListOf<FilmItemModel>() }
    
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
            .padding(top = 60.dp, bottom = 100.dp)
    ) {
        // Başlık
        Text(
            text = "What would you like to watch?",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        // Arama Çubuğu
        SearchBar(hint = "Search for movies")

      
        if (isLoadingNewMovies) {
            LoadingIndicator()
        } else {
            MovieList(newMovies, onItemClick)
        }

        if (isLoadingNewMovies) {
            LoadingIndicator()
        } else {
            MovieList(newMovies, onItemClick)
        }




    }
}

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
fun MovieList(movies: List<FilmItemModel>, onItemClick: (FilmItemModel) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp), // Öğeler arası boşluk
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            FilmItem(movie, onItemClick)
        }
    }
}


