package com.example.library.controller;

import com.example.library.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        borrowService.borrowBook(userId, bookId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/return/{userId}/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long userId, @PathVariable Long bookId) {
        borrowService.returnBook(userId, bookId);
        return ResponseEntity.ok("Book returned successfully");}
    
    @GetMapping("/borrow/myhistory")
    public ResponseEntity<?> getUserHistory(@RequestParam String email) {
        return ResponseEntity.ok(borrowService.getBorrowHistoryByUser(email));
    }

    @GetMapping("/borrow/history/all")
    public ResponseEntity<?> getAllHistory() {
        return ResponseEntity.ok(borrowService.getAllBorrowHistory());
    }

}