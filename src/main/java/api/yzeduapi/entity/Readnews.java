package api.yzeduapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(ConsumerGroupMapPK4.class)
public class Readnews implements Serializable {
    @Id
    private int news;

    @Id
    private int reader;

    @Id
    private String readertype;

    @Column
    private int isread;



}
