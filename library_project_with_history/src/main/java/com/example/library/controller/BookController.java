
package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> listAll() {
        return ResponseEntity.ok(bookService.listAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        if (title != null) return ResponseEntity.ok(bookService.searchByTitle(title));
        if (author != null) return ResponseEntity.ok(bookService.searchByAuthor(author));
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/general-search")
    public ResponseEntity<List<Book>> generalSearch(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.generalSearch(keyword));
    }
    
}