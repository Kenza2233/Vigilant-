package com.example.vinote.data.cloud

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.model.File as DriveFile
import java.io.File
import java.util.Collections

class GoogleDriveService(private val context: Context) : CloudSyncService {
    private lateinit var drive: Drive

    override suspend fun authenticate() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE_FILE))
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        val signInIntent = googleSignInClient.signInIntent
        (context as Activity).startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun handleSignInResult(account: GoogleSignInAccount) {
        val credential = GoogleAccountCredential.usingOAuth2(
            context, Collections.singleton(DriveScopes.DRIVE_FILE)
        )
        credential.selectedAccount = account.account
        drive = Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            GsonFactory(),
            credential
        ).setApplicationName("Vinote").build()
    }

    override suspend fun uploadFile(file: File): String {
        val fileMetadata = DriveFile()
        fileMetadata.name = file.name
        val mediaContent = com.google.api.client.http.FileContent("text/plain", file)
        val driveFile = drive.files().create(fileMetadata, mediaContent).execute()
        return driveFile.id
    }

    override suspend fun downloadFile(fileId: String): File {
        val file = File(context.cacheDir, "downloaded_file.txt")
        drive.files().get(fileId).executeMediaAndDownloadTo(file.outputStream())
        return file
    }

    override suspend fun getFileId(fileName: String): String? {
        val result = drive.files().list()
            .setQ("name = '$fileName'")
            .setSpaces("drive")
            .execute()
        return result.files.firstOrNull()?.id
    }

    companion object {
        const val RC_SIGN_IN = 1001
    }
}
