package api.yzeduapi.utils;//package com.wiseweblab.yunzhiedu.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//
//
//public class YzeduUtils {
//
//	public static List<Map<String, String>> handleValidErrorAsList(BindingResult result) {
//		List<Map<String, String>> errors = new ArrayList<>();
//		result.getAllErrors().stream().forEach(item -> {
//			Map<String, String> errorMap = new HashMap<>();
//			FieldError error = (FieldError) item;
//			errorMap.put("msg", error.getDefaultMessage());
//			errorMap.put("code", error.getCode());
//			errorMap.put("field", error.getField());
//			errors.add(errorMap);
//		});
//		return errors;
//	}
//
//}
