package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int course;

    @Column
    private String title;

    @Column
    private String videourl;

    @Column
    private String wareurl;

    @Column
    private int idx;

    @Column
    private int iscomplete;

}
