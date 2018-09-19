package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int course;

    @Column
    private String content;

    @Column
    private long createtime;

    @Column
    private long deadline;
}
