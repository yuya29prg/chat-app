package app.service;

import app.dto.MessageRequest;
import app.model.Message;
import app.model.User;
import app.repository.MessageRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> searchAll(){
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
    }

    public void create(MessageRequest messageRequest){
        Date now = new Date();
        Message message = new Message();
        message.setCreateDate(now);
        message.setUserId(String.valueOf(1));
        message.setText(messageRequest.getText());
        messageRepository.save(message);
    }
}
