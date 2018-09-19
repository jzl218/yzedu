package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private int school;

    @Column
    private String name;

    @Column
    private int teacher;

    @Column
    private long createtime;

    @Column
    private String description;

    @Column
    private long lastupdate;

    @Column
    private String imgurl;

    @Column
    private int isenabled;

    @Column
    private String type;

}
