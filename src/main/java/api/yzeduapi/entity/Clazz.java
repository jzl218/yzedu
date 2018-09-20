package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "table")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int branch;

    @Column
    private String name;
}
