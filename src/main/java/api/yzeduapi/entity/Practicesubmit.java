package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Practicesubmit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int score=0;

    @Column
    private int student;

    @Column
    private int practice;

    @Column
    private String content;

}
