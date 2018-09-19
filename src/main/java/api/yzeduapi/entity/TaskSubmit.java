package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tasksubmit")
public class TaskSubmit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer task;

    @Column
    private Integer student;

    @Column
    private String text;
}
