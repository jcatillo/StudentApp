package com.example.studentapp.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.studentapp.ui.screens.profile.utils.createProfileQrBitmap

data class QrExportResult(
    val isSuccess: Boolean,
    val message: String
)

class ExportProfileQrCodeUseCase {
    operator fun invoke(
        context: Context,
        outputUri: Uri,
        payload: String
    ): QrExportResult {
        return try {
            val bitmap = createProfileQrBitmap(payload = payload)

            context.contentResolver.openOutputStream(outputUri)?.use { outputStream ->
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, outputStream)
            }

            QrExportResult(
                isSuccess = true,
                message = "QR code downloaded successfully."
            )
        } catch (error: Exception) {
            QrExportResult(
                isSuccess = false,
                message = "Unable to download QR code."
            )
        }
    }
}
