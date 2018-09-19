package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private String name;

    @Column
    private String tecnum;

    @Column
    private String loginpass;

    @Column
    private String email;

    @Column
    private int school;

    @Column
    private int isenabled;

}
