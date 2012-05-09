package org.saiku.sdw.client.dto;

import java.util.Date;

public class Schema {
	private String uuid;
	private String name;
	private String defaultDescription;
	private String defaultLanguage;
	private int version;
	private boolean visible;
	private boolean published;
	private Date creationTimestamp;
	private Date publishingTimestamp;
	private String fileName;
	private String status;
	private String connectionName;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefaultDescription() {
		return defaultDescription;
	}
	public void setDefaultDescription(String defaultDescription) {
		this.defaultDescription = defaultDescription;
	}
	public String getDefaultLanguage() {
		return defaultLanguage;
	}
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public Date getPublishingTimestamp() {
		return publishingTimestamp;
	}
	public void setPublishingTimestamp(Date publishingTimestamp) {
		this.publishingTimestamp = publishingTimestamp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	
}
