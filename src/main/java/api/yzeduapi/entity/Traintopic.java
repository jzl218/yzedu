package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Traintopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int train;

    @Column
    private long createtime;

    @Column
    private int teacher;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int isclosed;

}
