package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon


private val CardBg = Color(0xFF0B1857)   // navy, same in both modes like the reference
private val Green = Color(0xFF3DDC84)

@Composable
fun BusinessCardTheme(content: @Composable () -> Unit) {
    // Card keeps its own palette; the theme only switches the system bars/status icons per mode.
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme(background = Color(0xFF050B2E)) else lightColorScheme(background = CardBg),
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BusinessCardTheme { BusinessCardScreen() } }
    }
}

@Composable
fun BusinessCardScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.android_logo),
                contentDescription = null,
                modifier = Modifier.width(120.dp)
            )
            Text(text = "Ibrahim O. Kaware", fontSize = 36.sp, color = Green, modifier = Modifier.padding(top = 8.dp))
            Text(text = "Expert Android Developer", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(top = 8.dp))
        }
        Column(modifier = Modifier.padding(bottom = 48.dp).align(Alignment.CenterHorizontally)) {
            ContactRow(Icons.Filled.Call, "+970 599 195 534")
            ContactRow(Icons.Filled.Share, "@AndroidDev")
            ContactRow(Icons.Filled.Email, "io.kaware@android.com")
        }
    }
}

@Composable
fun ContactRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Green)
        Text(text = text, color = Green, fontSize = 16.sp, modifier = Modifier.padding(start = 24.dp))
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BusinessCardLightPreview() { BusinessCardTheme { BusinessCardScreen() } }

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BusinessCardDarkPreview() { BusinessCardTheme { BusinessCardScreen() } }
