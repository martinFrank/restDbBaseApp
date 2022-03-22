package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.User;
import com.github.martinfrank.baseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> userCandidate = userRepository.findById(user.getId());

        if (userCandidate.isPresent()) {
            User userToUpdate = userCandidate.get();
            userToUpdate.setId(user.getId());
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userRepository.save(userToUpdate);
            return userToUpdate;
        } else {
            throw new ResourceNotFoundException("User Record not found with id : " + user.getId());
        }
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getById(long userId) {
        Optional<User> candidate = this.userRepository.findById(userId);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new ResourceNotFoundException("User Record not found with id : " + userId);
        }
    }

    @Override
    public void delete(long userId) {
        Optional<User> candidate = this.userRepository.findById(userId);
        if (candidate.isPresent()) {
            this.userRepository.delete(candidate.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + userId);
        }
    }
}
