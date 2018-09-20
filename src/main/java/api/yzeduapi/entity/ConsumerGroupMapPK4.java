package api.yzeduapi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsumerGroupMapPK4 implements Serializable{

    private int news;//电话号码

    private int reader;//(10)标签组id

    private String readertype;
}