package com.mmk.systemdesign.ui.screens.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.mmk.systemdesign.domain.model.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    topic: Topic?,
    onBack: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(topic) {
        if (topic != null) {
            viewModel.handleIntent(DetailIntent.LoadDetail(topic.url))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topic?.title ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (state.content != null) {
                val backgroundColor = MaterialTheme.colorScheme.surface
                val textColor = MaterialTheme.colorScheme.onSurface
                val hexBackground = String.format("#%06X", (0xFFFFFF and backgroundColor.toArgb()))
                val hexText = String.format("#%06X", (0xFFFFFF and textColor.toArgb()))

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            setBackgroundColor(backgroundColor.toArgb())
                        }
                    },
                    update = { webView ->
                        val styledContent = """
                        <html>
                        <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <style>
                        body {
                            font-family: 'Segoe UI', 'Noto Sans Bengali', sans-serif;
                            line-height: 1.6;
                            padding: 16px;
                            background-color: ${hexBackground};
                            color: ${hexText};
                        }
                        h1, h2, h3, h4 {
                            color: ${hexText};
                        }
                        img {
                            max-width: 100%;
                            height: auto;
                            border-radius: 8px;
                        }
                        pre {
                            background-color: #f0f0f0;
                            padding: 12px;
                            overflow: auto;
                            border-radius: 4px;
                            color: #333;
                        }
                        code {
                             font-family: monospace;
                             background-color: #eee;
                             padding: 2px 4px;
                        }
                        </style>
                        </head>
                        <body>
                        ${state.content}
                        </body>
                        </html>
                    """.trimIndent()
                        webView.loadDataWithBaseURL(
                            "https://lahin31.github.io/system-design-bangla/",
                            styledContent,
                            "text/html",
                            "UTF-8",
                            null
                        )
                    }
                )
            } else {
                Text(
                    text = "Select a topic to start reading",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
