package DTO;

public class FoodInfoDTO {

	private int food_code;
	private String food_name;
	private String food_company;
	private int food_amount;
	private int food_calory;
	
	// 积己磊
	public FoodInfoDTO(int food_code, String food_name, String food_company, int food_amount, int food_calory) {
		this.food_code = food_code;
		this.food_name = food_name;
		this.food_company = food_company;
		this.food_amount = food_amount;
		this.food_calory = food_calory;
	}

	// 内靛, 漠肺府
	public FoodInfoDTO(int food_code, int food_calory) {
		this.food_code = food_code;
		this.food_calory = food_calory;
	}
	
	public int getFood_code() {
		return food_code;
	}
	public void setFood_code(int food_code) {
		this.food_code = food_code;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_company() {
		return food_company;
	}
	public void setFood_company(String food_company) {
		this.food_company = food_company;
	}
	public int getFood_amount() {
		return food_amount;
	}
	public void setFood_amount(int food_amount) {
		this.food_amount = food_amount;
	}
	public int getFood_calory() {
		return food_calory;
	}
	public void setFood_calory(int food_calory) {
		this.food_calory = food_calory;
	}
	
	
	
}
