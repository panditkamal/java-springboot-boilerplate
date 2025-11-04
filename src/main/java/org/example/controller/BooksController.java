package org.example.controller;
import jakarta.validation.Valid;
import org.example.dto.BooksRequest;
import org.example.entity.Books;
import org.example.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BooksController {
    private final BookRepository bookRepository;

    public BooksController(BookRepository  bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/books/create")
    public Books create(@Valid @RequestBody BooksRequest booksRequest){
        Books book = new Books();
        book.setTitle(booksRequest.getTitle());

        return  this.bookRepository.save(book);
    }

    @GetMapping("/")
    public String GetRoot(){
        return "Bhaboooooooosdddoosssssssssssssssss";
    }
    @GetMapping("/books")
    public String GetBooks(){
        return "Hello this is";
    }
}
