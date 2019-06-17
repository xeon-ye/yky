package com.deloitte.platform.basic.bpmservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * activiti configuration from properties mapped  file
 * @author bixie
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.activiti")
public class ActivitiProcessProperties {

	private String enableSchemaUpdate;

	private String databaseType;
	
	private String fontName;
	
	private String annotationFontName;
	
	private String labelFontName;
	
	private String enableRunAllTasks;

	public String getEnableSchemaUpdate() {
		return enableSchemaUpdate;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public String getFontName() {
		return fontName;
	}

	public String getAnnotationFontName() {
		return annotationFontName;
	}

	public String getLabelFontName() {
		return labelFontName;
	}

	public void setEnableSchemaUpdate(String enableSchemaUpdate) {
		this.enableSchemaUpdate = enableSchemaUpdate;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public void setAnnotationFontName(String annotationFontName) {
		this.annotationFontName = annotationFontName;
	}

	public void setLabelFontName(String labelFontName) {
		this.labelFontName = labelFontName;
	}

	public String getEnableRunAllTasks() {
		return enableRunAllTasks;
	}

	public void setEnableRunAllTasks(String enableRunAllTasks) {
		this.enableRunAllTasks = enableRunAllTasks;
	}

	
	


}