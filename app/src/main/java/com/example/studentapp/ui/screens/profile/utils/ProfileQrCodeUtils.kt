package com.example.studentapp.ui.screens.profile.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.security.MessageDigest

private const val PROFILE_QR_MATRIX_SIZE = 29

fun generateProfileQrMatrix(payload: String): Array<BooleanArray> {
    val matrix = Array(PROFILE_QR_MATRIX_SIZE) { BooleanArray(PROFILE_QR_MATRIX_SIZE) }
    val reserved = Array(PROFILE_QR_MATRIX_SIZE) { BooleanArray(PROFILE_QR_MATRIX_SIZE) }

    placeFinderPattern(matrix, reserved, 0, 0)
    placeFinderPattern(matrix, reserved, PROFILE_QR_MATRIX_SIZE - 7, 0)
    placeFinderPattern(matrix, reserved, 0, PROFILE_QR_MATRIX_SIZE - 7)
    placeTimingPatterns(matrix, reserved)

    val payloadBytes = MessageDigest
        .getInstance("SHA-256")
        .digest(payload.toByteArray())

    var bitIndex = 0

    for (row in 0 until PROFILE_QR_MATRIX_SIZE) {
        for (column in 0 until PROFILE_QR_MATRIX_SIZE) {
            if (reserved[row][column]) {
                continue
            }

            val byte = payloadBytes[(bitIndex / 8) % payloadBytes.size]
            val bit = (byte.toInt() shr (bitIndex % 8)) and 1

            matrix[row][column] = bit == 1
            bitIndex += 1
        }
    }

    return matrix
}

fun createProfileQrBitmap(
    payload: String,
    cellSize: Int = 12,
    paddingCells: Int = 3
): Bitmap {
    val matrix = generateProfileQrMatrix(payload)
    val totalCells = PROFILE_QR_MATRIX_SIZE + (paddingCells * 2)
    val bitmapSize = totalCells * cellSize
    val bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val darkPaint = Paint().apply { color = android.graphics.Color.BLACK }
    val lightPaint = Paint().apply { color = android.graphics.Color.WHITE }

    canvas.drawRect(
        0f,
        0f,
        bitmapSize.toFloat(),
        bitmapSize.toFloat(),
        lightPaint
    )

    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, isDark ->
            if (!isDark) {
                return@forEachIndexed
            }

            val left = (columnIndex + paddingCells) * cellSize
            val top = (rowIndex + paddingCells) * cellSize
            val right = left + cellSize
            val bottom = top + cellSize

            canvas.drawRect(
                left.toFloat(),
                top.toFloat(),
                right.toFloat(),
                bottom.toFloat(),
                darkPaint
            )
        }
    }

    return bitmap
}

private fun placeFinderPattern(
    matrix: Array<BooleanArray>,
    reserved: Array<BooleanArray>,
    startColumn: Int,
    startRow: Int
) {
    for (rowOffset in 0 until 7) {
        for (columnOffset in 0 until 7) {
            val row = startRow + rowOffset
            val column = startColumn + columnOffset

            val isBorder = rowOffset == 0 || rowOffset == 6 || columnOffset == 0 || columnOffset == 6
            val isInnerSquare = rowOffset in 2..4 && columnOffset in 2..4

            matrix[row][column] = isBorder || isInnerSquare
            reserved[row][column] = true
        }
    }
}

private fun placeTimingPatterns(
    matrix: Array<BooleanArray>,
    reserved: Array<BooleanArray>
) {
    for (index in 8 until PROFILE_QR_MATRIX_SIZE - 8) {
        val isDark = index % 2 == 0

        matrix[6][index] = isDark
        reserved[6][index] = true

        matrix[index][6] = isDark
        reserved[index][6] = true
    }
}
