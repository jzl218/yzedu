package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class School {
    @Id
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column
    private String loginpass;

    @Column
    private String homepage;

    @Column
    private String phonenum;

    @Column
    private String sensitivewords;

    @Column
    private int isenabled;
}
