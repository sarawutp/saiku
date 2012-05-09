package org.saiku.sdw.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.saiku.datasources.connection.ISaikuConnection;
import org.saiku.datasources.datasource.SaikuDatasource;
import org.saiku.sdw.client.SDWMetadataClient;
import org.saiku.sdw.client.dto.Catalog;
import org.saiku.sdw.client.dto.Catalogs;
import org.saiku.sdw.client.dto.Connection;
import org.saiku.sdw.client.dto.Schema;
import org.saiku.sdw.client.dto.Schemas;
import org.saiku.sdw.client.dto.Workspace;
import org.saiku.sdw.client.dto.Workspaces;
import org.saiku.service.datasource.IDatasourceManager;
import org.saiku.service.util.exception.SaikuServiceException;

public class SDWDatasourceManager implements IDatasourceManager{
	
	private static Logger log = Logger.getLogger(SDWDatasourceManager.class);
	
	
	private Map<String,SaikuDatasource> datasources = Collections.synchronizedMap(new HashMap<String,SaikuDatasource>());
	private SDWMetadataClient sdwMetadataClient;
	
	public SDWDatasourceManager(SDWMetadataClient sdwMetadataClient){
		this.sdwMetadataClient = sdwMetadataClient;
	}
	
	public void setSdwMetadataClient(SDWMetadataClient sdwMetadataClient) {
		this.sdwMetadataClient = sdwMetadataClient;
	}

	protected void createDatasource(Workspace workspace) throws Exception {
		String workspaceName = workspace.getName();
		log.info("create datasource for workspaceName="+workspaceName);
		
		Catalogs catalogs = sdwMetadataClient.retrieveCatalogs(workspaceName);
		List<Catalog> listCatalog = catalogs.getCatalog();
		
		log.debug(workspaceName + " size = " + listCatalog.size());
		
		if(!listCatalog.isEmpty()){
			for (Catalog catalog : listCatalog) {
				String catalogName = catalog.getName();
				if(catalogName != null){
					Schemas schemas = sdwMetadataClient.retrieveSchemas(workspaceName, catalogName);
					List<Schema> listSchema = schemas.getSchema();
					for (Schema schema : listSchema) {
						String schemaName = schema.getName();
						String connectionname = schema.getConnectionName();
						if(schemaName != null && connectionname != null){
							Connection connection = sdwMetadataClient.retrieveConnection(workspaceName,connectionname );
							String mondrianSchemaXML = sdwMetadataClient.retrieveMondrainSchemasXML(workspaceName, catalogName, schemaName);
							if(mondrianSchemaXML != null && connection != null){
								
								 log.info("create datasource for workspaceName="+workspaceName + ", catalogName=" + catalogName + ", schemaName=" + schemaName);
								
								 //Add the database connection info.
								 Properties props = new Properties();
								 props.setProperty(ISaikuConnection.USERNAME_KEY, connection.getUsername());
								 props.setProperty(ISaikuConnection.PASSWORD_KEY, connection.getPassword());
								 props.setProperty(ISaikuConnection.DRIVER_KEY, "mondrian.olap4j.MondrianOlap4jDriver");
								 
								 //Add the database URL		
								 StringBuffer buffer = new StringBuffer();
								 buffer.append("jdbc:mondrian:Jdbc=");
								 buffer.append(connection.getUrl());
								 
								 //Add mondrain schema for each connection
								 buffer.append(";CatalogContent="+mondrianSchemaXML);
								 buffer.append(";JdbcDrivers=");
								 buffer.append(connection.getDriver());
									
								 props.setProperty(ISaikuConnection.URL_KEY, buffer.toString());
								 
								 String datasourceName = schema.getName();
								 //String datasourceName = workspaceName + " | " + catalogName + " | " + schemaName + " | " + connectionname;
								 SaikuDatasource ds = new SaikuDatasource(datasourceName,SaikuDatasource.Type.OLAP,props);
								 datasources.put(datasourceName, ds);
							}
						}else{
							log.error("Schema || connectionname not found for workspaceName="+workspaceName + ", catalogName="+catalogName);
						}
					}
				}else{
					log.error("Catalog not found for workspaceName="+workspaceName);
				}
			}
		}else{
			log.error("Catalog not found for workspaceName="+workspaceName);
		}
	}
	
	public void load() {
		
		log.info("load datasource");
		datasources.clear();
		try{
			
			Workspaces ws = sdwMetadataClient.retrieveWorkspaces();
			
			List<Workspace> workspaces = ws.getWorkspace();
			for (Workspace workspace : workspaces) {
				log.debug(workspace.getName());
				createDatasource(workspace);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new SaikuServiceException(e.getMessage(),e);
		}
	}

	public SaikuDatasource addDatasource(SaikuDatasource datasource) {
		log.info("add datasource | datasource="+datasource);
		datasources.put(datasource.getName(), datasource);
		return datasource;
	}

	public SaikuDatasource setDatasource(SaikuDatasource datasource) {
		log.info("set datasource | datasource="+datasource);
		return addDatasource(datasource);
	}

	public List<SaikuDatasource> addDatasources(List<SaikuDatasource> datasources) {
		log.info("add list of datasource | datasources="+datasources);
		for (SaikuDatasource ds : datasources) {
			addDatasource(ds);
		}
		return datasources;
	}

	public boolean removeDatasource(String datasourceName) {
		log.info("remove datasource");
		datasources.remove(datasourceName);
		return true;
	}

	public Map<String, SaikuDatasource> getDatasources() {
		log.info("get list of datasource");
		if(datasources.isEmpty())load();
		return datasources;
	}

	public SaikuDatasource getDatasource(String datasourceName) {
		log.info("get datasource | datasourceName="+datasourceName);
		return datasources.get(datasourceName);
	}
	
}
