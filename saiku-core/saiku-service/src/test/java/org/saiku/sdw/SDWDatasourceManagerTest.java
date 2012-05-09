package org.saiku.sdw;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.saiku.datasources.datasource.SaikuDatasource;
import org.saiku.sdw.client.SDWMetadataClient;
import org.saiku.sdw.client.SDWMetadataClientManager;
import org.saiku.sdw.client.SDWMetadataConfiguration;
import org.saiku.sdw.datasource.SDWDatasourceManager;
import org.saiku.service.datasource.IDatasourceManager;

public class SDWDatasourceManagerTest {
	
	public static IDatasourceManager ds;
	
	@BeforeClass
	public static void setup() throws IOException {
		SDWMetadataConfiguration config = new SDWMetadataConfiguration();
		config.setHost("http://cio049-vm1.hq.un.fao.org:8080");
		config.setCatalogUri("/techcdr-sdw/services/resources/catalogs/{workspaceName}");
		config.setConnectionUri("/techcdr-sdw/services/resources/connections/{workspaceName}?catalogName={catalogName}");
		config.setMondrainSchemaUri("/techcdr-sdw/services/resources/schemas/{workspaceName}/{catalogName}.{schemaName}/xml");
		config.setSchemaUri("/techcdr-sdw/services/resources/schemas/{workspaceName}?catalogName={catalogName}");
		config.setWorkspaceUri("/techcdr-sdw/services/resources/workspaces");
		SDWMetadataClient client = new SDWMetadataClientManager(config);
		ds = new SDWDatasourceManager(client);
		
	}
	
	/**
	 * This method attempts to test load all the connection from SDW metadata service and store in saiku datasources 
	 */
	@Test
	public void testLoadDatasource(){
		ds.load();
		Map<String,SaikuDatasource> datasources = ds.getDatasources();
		Assert.assertTrue(!datasources.isEmpty());
	}
	
	/**
	 * This method attempts to test get the existing datasource.
	 */
	@Test
	public void testGetDatasource(){
		Map<String,SaikuDatasource> datasources = ds.getDatasources();
		Iterator<String> keys = datasources.keySet().iterator();
		while (keys.hasNext()) {
			String string = (String) keys.next();
			SaikuDatasource saikuDatasource = datasources.get(string);
			if(saikuDatasource != null){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false);
			}
			break;
		}
	}
	
	/**
	 * This method attempts to test get all datasources.
	 */
	@Test
	public void testGetDatasources(){
		Map<String,SaikuDatasource> datasources = ds.getDatasources();
		Assert.assertFalse(datasources.isEmpty());
	}
	
	/**
	 * This method attempts to test add new datasources.
	 */
	@Test
	public void testAddDatasource(){
		
		SaikuDatasource datasource = new SaikuDatasource("test",SaikuDatasource.Type.OLAP,new Properties());
		ds.addDatasource(datasource);
		
		datasource = ds.getDatasource("test");
		Assert.assertNotNull(datasource);
	}
	
	/**
	 * This method attempts to remove add new datasources.
	 */
	@Test
	public void testRemoveDatasource(){
		
		SaikuDatasource datasource = new SaikuDatasource("test",SaikuDatasource.Type.OLAP,new Properties());
		ds.addDatasource(datasource);
		
		datasource = ds.getDatasource("test");
		Assert.assertNotNull(datasource);
		
		ds.removeDatasource("test");
		datasource = ds.getDatasource("test");
		Assert.assertNull(datasource);
	}
	
	
}
