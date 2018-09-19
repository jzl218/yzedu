package api.yzeduapi.utils;


import api.yzeduapi.exception.BaseException;
import api.yzeduapi.vo.Result;

public class ResultUtil  {

    public static Result Success(Object data){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result Success(){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    public static Result Error(BaseException e){
        return e.getResult();
    }

}
