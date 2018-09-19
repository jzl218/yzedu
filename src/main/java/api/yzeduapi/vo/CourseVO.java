package api.yzeduapi.vo;

import api.yzeduapi.entity.Point;
import lombok.Data;

import java.util.HashMap;
import java.util.List;


@Data
public class CourseVO {
    private Integer id;

    private String schoolname;


    private long createtime;


    private String description;


    private long lastupdate;


    private String img;


    private TeacherVO teacherVO;


    private List<Point> points;


    private double learningrate;


    private String type;
}
