package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private int teacher;

    @Column
    private long createtime;

    @Column
    private String type;

    @Column
    private String enclosureurl;

    @Column
    private String classes;

}
