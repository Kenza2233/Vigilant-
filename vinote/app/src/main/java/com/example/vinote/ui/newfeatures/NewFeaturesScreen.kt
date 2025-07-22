package com.example.vinote.ui.newfeatures

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vinote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFeaturesScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.new_features)) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "🎉 Yang Baru di Vinote! 🎉")
            Text(text = "- Sinkronisasi Cloud (Beta)")
            Text(text = "- Dasbor Statistik Penulisan")
            Text(text = "- Pelacakan Waktu dan Kecepatan Mengetik")
        }
    }
}
