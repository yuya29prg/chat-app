package app.service;

import app.dto.MessageRequest;
import app.model.Message;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repository.UserRepository;

import java.util.Date;
import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> searchAll(){
        return userRepository.findAll();
    }


}
