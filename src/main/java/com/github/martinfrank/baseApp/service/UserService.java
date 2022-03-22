package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    List<User> getAll();

    User getById(long userId);

    void delete(long id);

}
