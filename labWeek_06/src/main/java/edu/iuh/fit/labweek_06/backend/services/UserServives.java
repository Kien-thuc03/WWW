package edu.iuh.fit.labweek_06.backend.services;

import edu.iuh.fit.labweek_06.backend.models.User;
import edu.iuh.fit.labweek_06.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServives {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

}
