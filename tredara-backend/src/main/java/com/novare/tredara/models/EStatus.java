package com.novare.tredara.models;

public enum EStatus {
	ACTIVE(1), INACTIVE(2);

	private Integer status;

	private EStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}
}
