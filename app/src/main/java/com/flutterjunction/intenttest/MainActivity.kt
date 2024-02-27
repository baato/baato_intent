package com.flutterjunction.intenttest

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flutterjunction.intenttest.ui.theme.IntentTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonClick)

        button.setOnClickListener {
            val deepLink = "baato://?navigate=27.7032367,85.3296298&scheme=io.baato"
//            val deepLink = "baato://?geo=27.6681,83.4314"
            DeepLinkOpener.openDeepLink(deepLink, this@MainActivity)
        }
    }
}

object DeepLinkOpener {
    fun openDeepLink(deepLink: String, context: Context) {
        val uri = Uri.parse(deepLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        // Create chooser
        val chooserIntent = Intent.createChooser(intent, "Open with")

        try {
            context.startActivity(chooserIntent)
        } catch (e: ActivityNotFoundException) {
            // Handle if no suitable apps are found
            Toast.makeText(context, "No apps can handle this action", Toast.LENGTH_SHORT).show()

            // Open Play Store
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data =
                Uri.parse("market://details?id=io.baato") // Replace "io.baato" with your app package name
            try {
                context.startActivity(playStoreIntent)
            } catch (e: ActivityNotFoundException) {
                // If Play Store is not available, open browser
                playStoreIntent.data =
                    Uri.parse("https://play.google.com/store/apps/details?id=io.baato")
                context.startActivity(playStoreIntent)
            }
        }
    }
}