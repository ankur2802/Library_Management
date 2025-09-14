package com.example.library.repository;

import com.example.library.entity.BorrowRecord;
import com.example.library.entity.User;
import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findFirstByUserAndBookAndReturnDateIsNull(User user, Book book);
    java.util.List<BorrowRecord> findByUser(com.example.library.entity.User user);
}