package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data

@IdClass(ConsumerGroupMapPK1.class)
public class Studentcourse implements Serializable {

    @Id
    private int student;

    @Id
    private int course;

    @Column
    private int score;

    @Column
    private int tecscore;

    @Column
    private int process;

    @Column
    private int isclosed;

}
