package org.example.dto;

import jakarta.validation.constraints.*;

public class BooksRequest {

    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters.")
    private String title;

    @NotBlank(message = "Author name cannot be blank.")
    @Size(min = 2, max = 50, message = "Author name must be between 2 and 50 characters.")
    private String author;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0.")
    private Double price;

    @NotNull(message = "Pages count is required.")
    @Min(value = 1, message = "Book must have at least 1 page.")
    @Max(value = 2000, message = "Book cannot have more than 2000 pages.")
    private Integer pages;

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
