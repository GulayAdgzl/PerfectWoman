package com.glyadgzl.womanwallpaper

import MainViewModel
import WomanItem
import WomanItemModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun MainScreen(onItemClick: (WomanItemModel) -> Unit) {
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
fun MainContent(onItemClick: (WomanItemModel) -> Unit) {
    val viewModel = MainViewModel()
    val upcomingItems = remember { mutableStateListOf<WomanItemModel>() }
    val newItems = remember { mutableStateListOf<WomanItemModel>() }
    
    var isLoadingUpcoming by remember { mutableStateOf(true) }
    var isLoadingNewItems by remember { mutableStateOf(true) }
    
    // Load data
    LaunchedEffect(Unit) {
        viewModel.loadUpcoming().observeForever { items ->
            upcomingItems.clear()
            upcomingItems.addAll(items)
            isLoadingUpcoming = false
        }
    }
    
    LaunchedEffect(Unit) {
        viewModel.loadItems().observeForever { items ->
            newItems.clear()
            newItems.addAll(items)
            isLoadingNewItems = false
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, bottom = 80.dp)
            .background(Color(0xFF121218))
    ) {
        CategorySection(title = "Recommended", items = upcomingItems, isLoading = isLoadingUpcoming, onItemClick = onItemClick)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CategorySection(title = "New", items = newItems, isLoading = isLoadingNewItems, onItemClick = onItemClick)
    }
}

@Composable
fun CategorySection(
    title: String,
    items: List<WomanItemModel>,
    isLoading: Boolean,
    onItemClick: (WomanItemModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
        )
        
        if (isLoading) {
            LoadingIndicator()
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    WomanItem(item, onItemClick)
                }
            }
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
fun MovieList(womans: List<WomanItemModel>, onItemClick: (WomanItemModel) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp), // Öğeler arası boşluk
        contentPadding = PaddingValues(16.dp)
    ) {
        items(womans) { woman ->
            WomanItem(woman, onItemClick)
        }
    }
}


