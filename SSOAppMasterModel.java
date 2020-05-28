package com.dgh.model;

public class SSOAppMasterModel   {
	
	
	private Integer id;
	private String appShortName;
	private String appHeadingName;
	private String appLongName;
	private Integer isActive;
	private String formAction;
	private String formId;
	private String bgClass;
	private String textColorClass;
	private String apiURL;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppShortName() {
		return appShortName;
	}
	public void setAppShortName(String appShortName) {
		this.appShortName = appShortName;
	}
	public String getAppLongName() {
		return appLongName;
	}
	public void setAppLongName(String appLongName) {
		this.appLongName = appLongName;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getFormAction() {
		return formAction;
	}
	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getBgClass() {
		return bgClass;
	}
	public void setBgClass(String bgClass) {
		this.bgClass = bgClass;
	}
	public String getTextColorClass() {
		return textColorClass;
	}
	public void setTextColorClass(String textColorClass) {
		this.textColorClass = textColorClass;
	}
	public String getApiURL() {
		return apiURL;
	}
	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}
	public String getAppHeadingName() {
		return appHeadingName;
	}
	public void setAppHeadingName(String appHeadingName) {
		this.appHeadingName = appHeadingName;
	}

}