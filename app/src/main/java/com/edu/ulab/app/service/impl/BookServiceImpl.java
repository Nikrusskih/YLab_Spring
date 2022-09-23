package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.AddingToStorageException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private static AtomicLong generator = new AtomicLong();

    private BookMapper bookMapper;
    private Storage storage;

    public BookServiceImpl(BookMapper bookMapper, Storage storage) {
        this.bookMapper = bookMapper;
        this.storage = storage;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        bookDto.setId(generator.incrementAndGet());

        Book book = bookMapper.bookDtoToBook(bookDto);

        try {
            storage.addBook(book);
        } catch (AddingToStorageException e) {
            return null;
        }

        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        storage.deleteUserById(id);
    }

    @Override
    public List<Long> getBooksByUserId(Long userId) {
        return storage.getBooksByUserId(userId);
    }


}
