package org.abhishek.easysupport.dto.rest;

public class RequestWrapper implements Restful {
	private String userId = null;
	private String permission = null;
	private String fileName = null;
	private String filePath = null;
	private int fromLineNumber = 0;
	private int toLineNumber = 0;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFromLineNumber() {
		return fromLineNumber;
	}

	public void setFromLineNumber(int fromLineNumber) {
		this.fromLineNumber = fromLineNumber;
	}

	public int getToLineNumber() {
		return toLineNumber;
	}

	public void setToLineNumber(int toLineNumber) {
		this.toLineNumber = toLineNumber;
	}

	@Override
	public String toString() {
		return "RequestWrapper [userId="
			+ userId
			+ ", permission="
			+ permission
			+ ", fileName="
			+ fileName
			+ ", filePath="
			+ filePath
			+ ", fromLineNumber="
			+ fromLineNumber
			+ ", toLineNumber="
			+ toLineNumber
			+ "]";
	}
}
