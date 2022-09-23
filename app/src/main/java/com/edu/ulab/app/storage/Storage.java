package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.AddingToStorageException;

import java.util.List;

public interface Storage {

    void addUser(User user) throws AddingToStorageException;

    void addBook(Book book) throws AddingToStorageException;

    List<Long> getBooksByUserId(Long userId);

    User updateUser(User user);

    void deleteBookById(Long bookId);

    void deleteUserById(Long userId);
}
