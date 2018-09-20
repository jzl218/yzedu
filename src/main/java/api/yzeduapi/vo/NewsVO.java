package api.yzeduapi.vo;


import lombok.Data;

@Data
public class NewsVO {
    private Integer id;


    private String title;


    private String content;


    private long createtime;


    private int isread;


}
