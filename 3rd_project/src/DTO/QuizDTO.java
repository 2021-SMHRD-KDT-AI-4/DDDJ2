package DTO;

public class QuizDTO {

	private int quiz_code;
	private String quiz_content;
	private int quiz_point;
	
	
	// 생성자(코드, 퀴즈내용, 포인트)
	public QuizDTO(int quiz_code, String quiz_content, int quiz_point) {
		this.quiz_code = quiz_code;
		this.quiz_content = quiz_content;
		this.quiz_point = quiz_point;
	}
	
	// 퀴즈 생성(퀴즈내용, 포인트)
	public QuizDTO(String quiz_content, int quiz_point) {
		this.quiz_content = quiz_content;
		this.quiz_point = quiz_point;
	}

	public int getQuiz_code() {
		return quiz_code;
	}
	public void setQuiz_code(int quiz_code) {
		this.quiz_code = quiz_code;
	}
	public String getQuiz_content() {
		return quiz_content;
	}
	public void setQuiz_content(String quiz_content) {
		this.quiz_content = quiz_content;
	}
	public int getQuiz_point() {
		return quiz_point;
	}
	public void setQuiz_point(int quiz_point) {
		this.quiz_point = quiz_point;
	}
	
	
	
	
}
