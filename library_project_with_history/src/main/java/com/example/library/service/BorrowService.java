package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.entity.BorrowRecord;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import com.example.library.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class BorrowService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowService(UserRepository userRepository, BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Transactional
    public void borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord(user, book, LocalDate.now(), null);
        borrowRecordRepository.save(record);
    }

    @Transactional
    public void returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        BorrowRecord record = borrowRecordRepository
                .findFirstByUserAndBookAndReturnDateIsNull(user, book)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);
    }
    public java.util.List<BorrowRecord> getBorrowHistoryByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return borrowRecordRepository.findByUser(user);
    }

    public java.util.List<BorrowRecord> getAllBorrowHistory() {
        return borrowRecordRepository.findAll();
    }

}