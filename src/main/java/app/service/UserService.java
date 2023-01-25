package app.service;

import app.dto.MessageRequest;
import app.model.Message;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> searchAll(){
        return userRepository.findAll();
    }


    public User create(User user, String rawPassword) {
        Date now = new Date();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        user.setCreateDate(now);
        return userRepository.save(user);
    }


}
