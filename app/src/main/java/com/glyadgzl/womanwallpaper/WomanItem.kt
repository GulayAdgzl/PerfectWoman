
@Composable
fun WomanItem(item: FilmItemModel, onItemClick: (WomanItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemClick(item) }
            .background(color = Color(android.graphics.Color.parseColor("#2f2f39")))
    ) {
        AsyncImage(
            model = item.Poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(androidx.compose.ui.graphics.Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color =androidx.compose.ui.graphics.Color.White, fontSize = 11.sp),
            maxLines=1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp,bottom=4.dp)
        ){
            Icon(
                imageVector= Icons.Filled.Star,
                contentDescription=null,
                tint=androidx.compose.ui.graphics.Color.Yellow,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text=item.Imdb.toString(),
                style=TextStyle(color=androidx.compose.ui.graphics.Color.White,fontSize=11.sp)
            )
        }
    }
}

@Composable
fun WomanItem(item: WomanItem, onItemClick: (WomanItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemClick(item) }
            .background(color = Color(android.graphics.Color.parseColor("#2f2f39")))
    ) {
        AsyncImage(
            model = item.Poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(androidx.compose.ui.graphics.Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color =androidx.compose.ui.graphics.Color.White, fontSize = 11.sp),
            maxLines=1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp,bottom=4.dp)
        ){
            Icon(
                imageVector= Icons.Filled.Star,
                contentDescription=null,
                tint=androidx.compose.ui.graphics.Color.Yellow,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text=item.Imdb.toString(),
                style=TextStyle(color=androidx.compose.ui.graphics.Color.White,fontSize=11.sp)
            )
        }
    }
}
