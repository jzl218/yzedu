package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
@Entity
@IdClass(ConsumerGroupMapPK3.class)
@Data
public class Studentchapter implements Serializable {
    @Id
    private int student;

    @Id
    private int chapter;

    @Column
    private int iscomplete;
}
