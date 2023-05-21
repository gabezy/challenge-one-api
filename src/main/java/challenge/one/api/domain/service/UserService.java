package challenge.one.api.domain.service;

import challenge.one.api.domain.repository.UserRepository;
import challenge.one.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean usernameAlreadyExists (String username) {
        return userRepository.findByUsername(username) != null;
    }
}
