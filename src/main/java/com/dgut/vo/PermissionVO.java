package com.dgut.vo;

import com.dgut.po.Permission;

public class PermissionVO extends Permission {

	private boolean possess;// 是否拥有

	public boolean isPossess() {
		return possess;
	}

	public void setPossess(boolean possess) {
		this.possess = possess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (possess ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissionVO other = (PermissionVO) obj;
		if (possess != other.possess)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PermissionVO [id=" + getId() + ", name=" + getName() + ", possess=" + possess + "]";
	}

}
