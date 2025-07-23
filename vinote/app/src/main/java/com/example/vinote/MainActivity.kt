package com.example.vinote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.vinote.data.cloud.GoogleDriveService
import com.example.vinote.navigation.VinoteNavHost
import com.example.vinote.ui.theme.VinoteTheme
import com.example.vinote.ui.settings.InterfaceSettingsViewModel
import com.example.vinote.ui.theme.ThemeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels { AppViewModelProvider.Factory }
    private val interfaceSettingsViewModel: InterfaceSettingsViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
            val fontSize by interfaceSettingsViewModel.fontSize.collectAsState()
            VinoteTheme(darkTheme = isDarkTheme, fontSize = fontSize) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    VinoteNavHost(navController = navController)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GoogleDriveService.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Signed in successfully, handle account
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
            }
        }
    }
}
