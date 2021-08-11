package DTO;

public class NutritionDTO {

	private int nut_code;
	private int food_code;
	private float nut_protein;
	private float nut_fat;
	private float nut_carbohydrate;
	private float nut_sugar;
	private float nut_natrum;
	
	// 생성자
	public NutritionDTO(int nut_code, int food_code, float nut_protein, float nut_fat, float nut_carbohydrate,
			float nut_sugar, float nut_natrum) {
		this.nut_code = nut_code;
		this.food_code = food_code;
		this.nut_protein = nut_protein;
		this.nut_fat = nut_fat;
		this.nut_carbohydrate = nut_carbohydrate;
		this.nut_sugar = nut_sugar;
		this.nut_natrum = nut_natrum;
	}
	
	// 영양성분(코드,탄,단,지,당,염)
	public NutritionDTO(int nut_code, float nut_protein, float nut_fat, float nut_carbohydrate, float nut_sugar, float nut_natrum) {
		this.nut_code = nut_code;
		this.nut_protein = nut_protein;
		this.nut_fat = nut_fat;
		this.nut_carbohydrate = nut_carbohydrate;
		this.nut_sugar = nut_sugar;
		this.nut_natrum = nut_natrum;
	}
	
	public int getNut_code() {
		return nut_code;
	}
	public void setNut_code(int nut_code) {
		this.nut_code = nut_code;
	}
	public int getFood_code() {
		return food_code;
	}
	public void setFood_code(int food_code) {
		this.food_code = food_code;
	}
	public float getNut_protein() {
		return nut_protein;
	}
	public void setNut_protein(float nut_protein) {
		this.nut_protein = nut_protein;
	}
	public float getNut_fat() {
		return nut_fat;
	}
	public void setNut_fat(float nut_fat) {
		this.nut_fat = nut_fat;
	}
	public float getNut_carbohydrate() {
		return nut_carbohydrate;
	}
	public void setNut_carbohydrate(float nut_carbohydrate) {
		this.nut_carbohydrate = nut_carbohydrate;
	}
	public float getNut_sugar() {
		return nut_sugar;
	}
	public void setNut_sugar(float nut_sugar) {
		this.nut_sugar = nut_sugar;
	}
	public float getNut_natrum() {
		return nut_natrum;
	}
	public void setNut_natrum(float nut_natrum) {
		this.nut_natrum = nut_natrum;
	}
	
	
	
}
