package org.saiku.sdw.client;

import org.saiku.sdw.client.dto.Catalogs;
import org.saiku.sdw.client.dto.Connections;
import org.saiku.sdw.client.dto.Schemas;
import org.saiku.sdw.client.dto.Workspaces;

public interface SDWMetadataClient {
	
	
	public Workspaces retrieveWorkspaces();
	
	public Catalogs retrieveCatalogs(String workspaceName);
	
	public Connections retrieveConnections(String workspaceName,String catalogName);
	
	public Schemas retrieveSchemas(String workspaceName,String catalogName);
	
	public String retrieveMondrainSchemasXML(String workspaceName,String catalogName,String schemaName);
}
