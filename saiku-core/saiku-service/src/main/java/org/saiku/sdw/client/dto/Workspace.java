package org.saiku.sdw.client.dto;

import java.util.Date;

public class Workspace {
	private String uuid;
	private String name;
	private String caption;
	private String description;
	private boolean visible;
	private boolean published;
	private Date creationTimestamp;
	private Date publishingTimestamp;
	private String status;
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
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
