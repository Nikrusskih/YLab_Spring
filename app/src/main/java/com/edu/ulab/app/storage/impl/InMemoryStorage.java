package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class InMemoryStorage implements Storage {
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Book> books = new HashMap<>();
    private final Map<Long, List<Long>> bookToUsers = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public void addBook(Book book) {
        books.put(book.getId(), book);

        bookToUsers.putIfAbsent(book.getUserId(), new ArrayList<>());
        bookToUsers.computeIfPresent(book.getUserId(), (userId, books) -> {
            books.add(book.getId());
            return books;
        });
    }

    @Override
    public List<Long> getBooksByUserId(Long userId) {
        return bookToUsers.getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public void deleteBookById(Long bookId) {
        books.remove(bookId);
    }

    @Override
    public void deleteUserById(Long userId) {
        users.remove(userId);
        bookToUsers.remove(userId);
    }
}
