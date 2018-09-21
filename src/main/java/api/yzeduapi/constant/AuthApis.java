package api.yzeduapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

//表示需要认证的api
@AllArgsConstructor
@Getter
@ToString

public enum AuthApis {
	GET_CHOOSEN_COURSE(0,"/api/course/getstudentcourse"),
	GET_COURSE_DETAIL(1,"/api/course/getcourse"),
	GET_COURSE_CHAPTER(2,"/api/course/getchapter"),
	GET_CHAPTER_VIDEO(3,"/api/course/getvideo"),
	GET_CHAPTER_WARE(4,"/api/course/getware"),
	SET_LEARNING(5,"/api/course/setlearned"),
	GET_COURSE_PRACTICE(6,"/api/course/getpractice"),
	POST_PRACTICE(7,"/api/course/practicesubmit"),
	GET_SUBMITTED_PRACTICE(8,"/api/course/getsubmitedpractice"),
	GET_WRONG_QUESTIONS(9,"/api/course/getwrongquestions"),
	GET_CHOESCOURSE(10,"/api/course/getchosecourse"),
	CHOSE_COURSE(11,"/api/course/chosecourse"),


	SEND_EMAIL_CODE(12,"/api/info/emailcode"),
	UPDATE_PASS(13,"/api/info/updatepass"),
	SET_EAMIL(14,"/api/info/setemail"),


	GET_NEWS(15,"/api/news/getnews"),
		READ_NEWS(17,"/api/news/readnews"),

	GET_TASK(16,"/api/task/gettask"),
	GET_ENCLOSURE(17,"/api/task/getenclosure"),
	SUBMITTASK(18,"/api/task/submittask"),

	GET_TRAIN(19,"/api/train/gettrain"),
	GET_TRAIN_TOPIC(20,"/api/train/gettraintopic"),
	GET_TOPIC_REPLY(21,"/api/train/gettopicreply"),
	SEND_REPLY(22,"/api/train/sendreply")


	;

	/** ?~? 表示教师角色权限*/

	private Integer id;
	private String url;
}
