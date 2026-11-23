package com.example.composequadrant

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


// Two alternating panel colors for each mode, taken from the reference screenshots.
private val LightA = Color(0xFFFFDBC5); private val LightB = Color(0xFFFDE9DF)
private val DarkA = Color(0xFF5C4333);  private val DarkB = Color(0xFF2B1F18)

@Composable
fun QuadrantTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (dark) darkColorScheme(onBackground = Color(0xFFF8E0D4)) else lightColorScheme(onBackground = Color(0xFF231810)),
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { QuadrantTheme { QuadrantScreen() } }
    }
}

@Composable
fun QuadrantScreen(modifier: Modifier = Modifier) {
    val dark = isSystemInDarkTheme()
    val a = if (dark) DarkA else LightA
    val b = if (dark) DarkB else LightB
    Column(modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            QuadrantPanel("Text composable", "Displays text and follows the recommended Material Design guidelines.", a, Modifier.weight(1f))
            QuadrantPanel("Image composable", "Creates a composable that lays out and draws a given Painter class object.", b, Modifier.weight(1f))
        }
        Row(Modifier.weight(1f)) {
            QuadrantPanel("Row composable", "A layout composable that places its children in a horizontal sequence.", b, Modifier.weight(1f))
            QuadrantPanel("Column composable", "A layout composable that places its children in a vertical sequence.", a, Modifier.weight(1f))
        }
    }
}

@Composable
fun QuadrantPanel(title: String, body: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(color).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body, textAlign = TextAlign.Justify, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun QuadrantLightPreview() { QuadrantTheme { QuadrantScreen() } }

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuadrantDarkPreview() { QuadrantTheme { QuadrantScreen() } }
