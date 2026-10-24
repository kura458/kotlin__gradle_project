
package org.dbu.library.ui

import org.dbu.library.model.Book
import org.dbu.library.model.Patron
import org.dbu.library.repository.LibraryRepository
import org.dbu.library.service.BorrowResult
import org.dbu.library.service.LibraryService

fun handleMenuAction(
    choice: String,
    service: LibraryService,
    repository: LibraryRepository
): Boolean {
    return when (choice) {
        "1" -> {
            addBook(service)
            true
        }
        "2" -> {
            registerPatron(repository)
            true
        }
        "3" -> {
            borrowBook(service)
            true
        }
        "4" -> {
            returnBook(service)
            true
        }
        "5" -> {
            search(service)
            true
        }
        "6" -> {
            listAllBooks(repository)
            true
        }
        "0" -> false
        else -> {
            println("Invalid option")
            true
        }
    }
}

private fun addBook(service: LibraryService) {
    print("ISBN: ")
    val isbn = readLine()?.trim().orEmpty()

    print("Title: ")
    val title = readLine()?.trim().orEmpty()

    print("Author: ")
    val author = readLine()?.trim().orEmpty()

    print("Year: ")
    val year = readLine()?.trim()?.toIntOrNull()

    if (year == null) {
        println("Invalid year")
        return
    }

    val added = service.addBook(Book(isbn, title, author, year))
    println("Book added: $added")
}

private fun registerPatron(repository: LibraryRepository) {
    print("Patron ID: ")
    val id = readLine()?.trim().orEmpty()

    print("Name: ")
    val name = readLine()?.trim().orEmpty()

    val added = repository.addPatron(Patron(id, name))
    println("Patron added: $added")
}

private fun borrowBook(service: LibraryService) {
    print("Patron ID: ")
    val patronId = readLine()?.trim().orEmpty()

    print("ISBN: ")
    val isbn = readLine()?.trim().orEmpty()

    when (service.borrowBook(patronId, isbn)) {
        BorrowResult.SUCCESS -> println("Borrowed")
        BorrowResult.BOOK_NOT_FOUND -> println("Book not found")
        BorrowResult.PATRON_NOT_FOUND -> println("Patron not found")
        BorrowResult.NOT_AVAILABLE -> println("Book not available")
        BorrowResult.LIMIT_REACHED -> println("Borrowing limit reached")
    }
}

private fun returnBook(service: LibraryService) {
    print("Patron ID: ")
    val patronId = readLine()?.trim().orEmpty()

    print("ISBN: ")
    val isbn = readLine()?.trim().orEmpty()

    if (service.returnBook(patronId, isbn)) {
        println("Returned")
    } else {
        println("Return failed")
    }
}

private fun search(service: LibraryService) {
    print("Query: ")
    val query = readLine()?.trim().orEmpty()
    val results = service.search(query)

    if (results.isEmpty()) {
        println("No books found")
    } else {
        results.forEachIndexed { index, book ->
            println("${index + 1}. ${book.title} - ${book.author}")
        }
    }
}

private fun listAllBooks(repository: LibraryRepository) {
    val books = repository.getAllBooks()

    if (books.isEmpty()) {
        println("No books found")
        return
    }

    books.forEach { book ->
        val status = if (book.isAvailable) "Available" else "Borrowed"
        println("${book.title} - ${book.author} (${book.year}) - $status")
    }
}
