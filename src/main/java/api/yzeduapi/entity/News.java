package api.yzeduapi.entity;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

import javax.persistence.*;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private long createtime;

    @Column
    private int publisher;

    @Column
    private String publishertype;

}
