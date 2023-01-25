package app.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserForm implements Serializable {
    private String name;
    private String password;
}
