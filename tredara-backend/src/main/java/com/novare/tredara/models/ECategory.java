package com.novare.tredara.models;

public enum ECategory {
	MOBILES(1), ACCESSORIES(2), CLOTHES(3), BOOKS(4), BEAUTYCARE(5);

	private Integer value;

	private ECategory(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	public static ECategory get(Integer value) {
		for(ECategory category:values()) {
			if(category.getValue()==value) {
				return category;
			}
		}
		return null;
	}

}
