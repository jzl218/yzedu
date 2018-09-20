package api.yzeduapi.vo;

import lombok.Data;

@Data
public class TopicReplyVO {
    private Integer id;

    private String name;

    private String content;

    private long createtime;

    private String img;
}
