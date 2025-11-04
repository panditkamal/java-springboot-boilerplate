package org.example.repository;

import org.example.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Books , Long> {
}
