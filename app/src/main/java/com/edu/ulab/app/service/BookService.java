package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto userDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBookById(Long id);
    List<Long> getBooksByUserId(Long userId);
}
