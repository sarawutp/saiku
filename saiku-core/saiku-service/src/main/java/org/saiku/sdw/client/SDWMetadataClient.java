package org.saiku.sdw.client;

import org.saiku.sdw.client.dto.Catalogs;
import org.saiku.sdw.client.dto.Connection;
import org.saiku.sdw.client.dto.Connections;
import org.saiku.sdw.client.dto.SchemaLanguages;
import org.saiku.sdw.client.dto.Schemas;
import org.saiku.sdw.client.dto.Workspaces;

public interface SDWMetadataClient {
	
	public Workspaces retrieveWorkspaces();
	
	public Catalogs retrieveCatalogs(String workspaceName);
	
	public Connection retrieveConnection(String workspaceName,String connectionName);
	
	public Connections retrieveConnections(String workspaceName);
	
	public Schemas retrieveSchemas(String workspaceName,String catalogName);
	
	public String retrieveMondrainSchemasXML(String workspaceName,String catalogName,String schemaName);
	
	public SchemaLanguages retrieveSchemaLanguages(String workspaceName, String catalogName, String schemaName);
}
