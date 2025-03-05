import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable 
fun WomanItem(item: WomanItemModel, onItemClick: (WomanItemModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onItemClick(item) }
            .background(color = Color(0xFF2F2F39))
    ) {
        Box(
            modifier = Modifier.size(width = 120.dp, height = 150.dp)
        ) {
            // Main image
            AsyncImage(
                model = item.picUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            
            if (item.birth > 1900) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color(0xFF3D7BF6), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "NEW",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // Verification badge can be conditionally shown
          // Show verification badge only for notable persons
if (item.notable.isNotEmpty()) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(8.dp)
            .size(20.dp)
            .background(Color(0xFF3D7BF6), CircleShape)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Verified",
            tint = Color.White,
            modifier = Modifier.size(12.dp)
        )
    }
}
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(2.dp))
            
            // Display profession instead of followers
            Text(
                text = item.profession,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
    }
}