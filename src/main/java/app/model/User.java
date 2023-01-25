package app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "user")
@Entity
public class User{
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
    private String name;

   @Column(name = "create_date")
    private Date createDate;

   @Column(name = "password")
    private String password;

}

//public class User implements Serializable {