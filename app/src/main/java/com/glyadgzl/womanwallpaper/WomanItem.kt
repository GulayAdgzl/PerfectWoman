import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyadgzl.womanwallpaper.R

/*
@Composable 
fun WomanItem(item: WomanItemModel, onItemClick: (WomanItemModel) -> Unit) {

    Card ( elevation= CardDefaults.cardElevation()){
        Column(
            modifier = Modifier
                .padding(4.dp)
                .width(120.dp)
                .clickable { onItemClick(item) }
                .background(color = Color(android.graphics.Color.parseColor("#2f2f39")))
        ) {
            AsyncImage(
                model = item.picUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 120.dp, height = 180.dp)
                    .background(androidx.compose.ui.graphics.Color.Gray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.name,
                modifier = Modifier.padding(start = 4.dp),
                style = TextStyle(color =androidx.compose.ui.graphics.Color.White, fontSize = 11.sp),
                maxLines=1
            )
            Spacer(modifier = Modifier.height(4.dp))




        }
    }

}
*/
@Composable
fun WomanItem(item: WomanItemModel, onItemClick: (WomanItemModel) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .width(250.dp)
            .height(500.dp)
            .clickable { onItemClick(item) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1C1C27)) // Koyu arkaplan rengi
        ) {
            // Arka plan resmi
            AsyncImage(
                model = item.picUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // Gradient Overlay (Alttan yukarı şeffaflık)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Transparent)
                        )
                    )
            )

            // Metin ve bilgiler
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))


                }

                Text(
                    text = "${item.profession} profession",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}
