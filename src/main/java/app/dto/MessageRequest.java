package app.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageRequest implements Serializable {
    private String text;
    private Date createDate;
    private int userId;
}
