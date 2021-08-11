package DTO;

public class ChallengeDTO {

	private int challenge_code;
	private String challenge_content;
	private int challenge_point;
	
	// 생성자
	public ChallengeDTO(int challenge_code, String challenge_content, int challenge_point) {
		this.challenge_code = challenge_code;
		this.challenge_content = challenge_content;
		this.challenge_point = challenge_point;
	}
	// 챌린지 생성(챌린지내용, 포인트)
	public ChallengeDTO(String challenge_content, int challenge_point) {
		this.challenge_content = challenge_content;
		this.challenge_point = challenge_point;
	}

	public int getChallenge_code() {
		return challenge_code;
	}
	public void setChallenge_code(int challenge_code) {
		this.challenge_code = challenge_code;
	}
	public String getChallenge_content() {
		return challenge_content;
	}
	public void setChallenge_content(String challenge_content) {
		this.challenge_content = challenge_content;
	}
	public int getChallenge_point() {
		return challenge_point;
	}
	public void setChallenge_point(int challenge_point) {
		this.challenge_point = challenge_point;
	}
	
	
	
}
