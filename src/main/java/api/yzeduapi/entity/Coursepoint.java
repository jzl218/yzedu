package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(ConsumerGroupMapPK2.class)
@Data
public class Coursepoint implements Serializable {
    @Id
    private int course;

    @Id
    private int point;

}
