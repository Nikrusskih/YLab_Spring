package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.exception.AddingToStorageException;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final AtomicLong generator = new AtomicLong();

    private final UserMapper userMapper;
    private final Storage storage;

    public UserServiceImpl(UserMapper userMapper, Storage storage) {
        this.userMapper = userMapper;
        this.storage = storage;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setId(generator.incrementAndGet());
        User user = userMapper.userDtoToUser(userDto);
        try {
            storage.addUser(user);
            return userDto;
        } catch (AddingToStorageException e) {
            return null;
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        User updatedUser = storage.updateUser(user);
        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        storage.deleteUserById(id);
    }
}
