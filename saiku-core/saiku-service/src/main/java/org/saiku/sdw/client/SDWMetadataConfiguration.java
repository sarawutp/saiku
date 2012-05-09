package org.saiku.sdw.client;

public class SDWMetadataConfiguration {
	
	private String host;
	private String workspaceUri;
	private String connectionUri;
	private String catalogUri;
	private String schemaUri;
	private String mondrainSchemaUri;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getWorkspaceUri() {
		return workspaceUri;
	}
	public void setWorkspaceUri(String workspaceUri) {
		this.workspaceUri = workspaceUri;
	}
	public String getConnectionUri() {
		return connectionUri;
	}
	public void setConnectionUri(String connectionUri) {
		this.connectionUri = connectionUri;
	}
	public String getCatalogUri() {
		return catalogUri;
	}
	public void setCatalogUri(String catalogUri) {
		this.catalogUri = catalogUri;
	}
	public String getSchemaUri() {
		return schemaUri;
	}
	public void setSchemaUri(String schemaUri) {
		this.schemaUri = schemaUri;
	}
	public String getMondrainSchemaUri() {
		return mondrainSchemaUri;
	}
	public void setMondrainSchemaUri(String mondrainSchemaUri) {
		this.mondrainSchemaUri = mondrainSchemaUri;
	}
	
}
