package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Topicreply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int student;

    @Column
    private int topic;

    @Column
    private String content;

    @Column
    private long createtime;

    @Column
    private String imgurl;
}
