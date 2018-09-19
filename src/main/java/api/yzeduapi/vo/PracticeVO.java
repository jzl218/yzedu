package api.yzeduapi.vo;

import lombok.Data;


@Data
public class PracticeVO {
    private Integer id;

    private String content;

    private long createtime;

    private long deadline;

    private int is_submitted;
}
