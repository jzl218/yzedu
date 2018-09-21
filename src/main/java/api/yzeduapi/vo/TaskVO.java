package api.yzeduapi.vo;

import lombok.Data;

@Data
public class TaskVO {
    private Integer id;


    private String title;


    private String content;


    private String teachername;


    private long createtime;


    private long deadline;


    private String type;


    private int state;

}
