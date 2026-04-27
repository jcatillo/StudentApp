package com.example.studentapp.domain.usecase

import com.example.studentapp.ui.screens.library.models.LibraryBook
import com.example.studentapp.ui.screens.library.models.LibraryTab
import com.example.studentapp.ui.screens.library.models.StockStatus
import com.example.studentapp.domain.repository.StudentRepository

class GetLibraryBooksUseCase(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(tab: LibraryTab? = null, page: Int = 1): List<LibraryBook> {
        return try {
            val response = repository.getLibraryBooks(tab?.name, page, 20)
            if (response.isSuccessful) {
                response.body()?.data?.map { dto ->
                    LibraryBook(
                        id = dto.id,
                        title = dto.title,
                        author = dto.author,
                        rating = dto.rating,
                        genre = dto.genre,
                        stockLabel = dto.stockLabel,
                        stockStatus = try {
                            StockStatus.valueOf(dto.stockStatus)
                        } catch (e: Exception) {
                            StockStatus.Available
                        },
                        isNew = dto.isNew,
                        tab = try {
                            LibraryTab.valueOf(dto.tab)
                        } catch (e: Exception) {
                            LibraryTab.Available
                        }
                    )
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
