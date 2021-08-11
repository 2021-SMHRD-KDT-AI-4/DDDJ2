package DTO;

public class DiaryDTO {

	private int myfood_code;
	private int food_code;
	private String user_id;
	private String eaten_date;
	private String eaten_time;
	private float total_carbohydrate;
	private float total_protein;
	private float total_fat;
	private float total_sugar;
	private float total_natrum;
	private int total_calory;
	
	// »ý¼ºÀÚ
	public DiaryDTO(int myfood_code, int food_code, String user_id, String eaten_date, String eaten_time,
			float total_carbohydrate, float total_protein, float total_fat, float total_sugar, float total_natrum,
			int total_calory) {
		this.myfood_code = myfood_code;
		this.food_code = food_code;
		this.user_id = user_id;
		this.eaten_date = eaten_date;
		this.eaten_time = eaten_time;
		this.total_carbohydrate = total_carbohydrate;
		this.total_protein = total_protein;
		this.total_fat = total_fat;
		this.total_sugar = total_sugar;
		this.total_natrum = total_natrum;
		this.total_calory = total_calory;
	}
	
	// ÃÑ ¼·Ãë ¿µ¾ç¼ººÐ (Åº,´Ü,Áö,´ç,¿°,Ä®·Î¸®)
	public DiaryDTO(float total_carbohydrate, float total_protein, float total_fat, float total_sugar,
			float total_natrum, int total_calory) {
		this.total_carbohydrate = total_carbohydrate;
		this.total_protein = total_protein;
		this.total_fat = total_fat;
		this.total_sugar = total_sugar;
		this.total_natrum = total_natrum;
		this.total_calory = total_calory;
	}

	public int getMyfood_code() {
		return myfood_code;
	}
	public void setMyfood_code(int myfood_code) {
		this.myfood_code = myfood_code;
	}
	public int getFood_code() {
		return food_code;
	}
	public void setFood_code(int food_code) {
		this.food_code = food_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEaten_date() {
		return eaten_date;
	}
	public void setEaten_date(String eaten_date) {
		this.eaten_date = eaten_date;
	}
	public String getEaten_time() {
		return eaten_time;
	}
	public void setEaten_time(String eaten_time) {
		this.eaten_time = eaten_time;
	}
	public float getTotal_carbohydrate() {
		return total_carbohydrate;
	}
	public void setTotal_carbohydrate(float total_carbohydrate) {
		this.total_carbohydrate = total_carbohydrate;
	}
	public float getTotal_protein() {
		return total_protein;
	}
	public void setTotal_protein(float total_protein) {
		this.total_protein = total_protein;
	}
	public float getTotal_fat() {
		return total_fat;
	}
	public void setTotal_fat(float total_fat) {
		this.total_fat = total_fat;
	}
	public float getTotal_sugar() {
		return total_sugar;
	}
	public void setTotal_sugar(float total_sugar) {
		this.total_sugar = total_sugar;
	}
	public float getTotal_natrum() {
		return total_natrum;
	}
	public void setTotal_natrum(float total_natrum) {
		this.total_natrum = total_natrum;
	}
	public int getTotal_calory() {
		return total_calory;
	}
	public void setTotal_calory(int total_calory) {
		this.total_calory = total_calory;
	}
	
	
	
	
}
