package api.yzeduapi.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentDto {
    @NotEmpty(message = "请填写学号")
    private String stunum;
    @NotEmpty(message = "请填写密码")
    private String loginpass;
}
