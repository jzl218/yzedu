package api.yzeduapi.entity;

import lombok.Data;
import org.jsets.shiro.model.Account;

import javax.persistence.*;
import javax.persistence.EmbeddedId;
@Entity
@Data

public class Student implements Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private String name;

    @Column
    private String stunum;

    @Column
    private String loginpass;

    @Column(unique = true)
    private String email;

    @Column(name = "class")
    private int clazz;

    @Column
    private int school;

    @Column
    private int isenabled;


    @Override
    public String getAccount() {
        return stunum;
    }

    @Override
    public String getPassword() {
        return loginpass;
    }
}
