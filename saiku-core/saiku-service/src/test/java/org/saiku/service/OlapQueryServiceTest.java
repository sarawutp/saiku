package org.saiku.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.saiku.olap.dto.SaikuCube;
import org.saiku.olap.dto.SaikuMember;
import org.saiku.olap.dto.SaikuQuery;
import org.saiku.olap.dto.resultset.AbstractBaseCell;
import org.saiku.olap.dto.resultset.CellDataSet;

public class OlapQueryServiceTest extends ServiceTest{
	
	private static Logger log = Logger.getLogger(OlapQueryServiceTest.class);
	
	/**
	 * Default query name for testing
	 */
	private static final String queryName = "test";
	
	/**
	 * This cube name will use for testing MDX query
	 */
	private static final String cubeName = "HR";
	
	/**
	 * Cube name: HR 
	 * <br/>-------------------<br/>
	 * SELECT
		NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS,
		NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS
		FROM [HR]
	 */
	private static final String mdxQuery_2 = "SELECT NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS, NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS FROM [HR]";
	
	/**
	 * Cube name: HR 
	 * <br/>-------------------<br/>
	 * SELECT
		NON EMPTY {Hierarchize({[Measures].[Count]})} ON COLUMNS,
		NON EMPTY {Hierarchize({[Department].[Department Description].Members})} ON ROWS
		FROM [HR]
	 */
	private static final String mdxQuery = "SELECT NON EMPTY {Hierarchize({[Measures].[Count]})} ON COLUMNS, NON EMPTY {Hierarchize({[Department].[Department Description].Members})} ON ROWS FROM [HR]";
	
	/**
	 * This cube name will use for testing visible variable in SaikuMember object.
	 */
	private static final String cubeName_2 = "Sales 2";
	
	/**
	 * This is the index number for testing a true value value in the getVisible()
	 */
	private static final int memberIndex_1 = 0;
	
	/**
	 * This is the index number for testing a false value value in the getVisible()
	 */
	private static final int memberIndex_2 = 2;
	
	/**
	 * Get Cube by name.
	 * @param name
	 * @return
	 */
	private SaikuCube getExistingCubeByName(String name){
		List<SaikuCube> cubes = olapMetaExplorer.getCubes(connectionName); 
		for (SaikuCube saikuCube : cubes) {
			if(name.equals(saikuCube.getName())){
				return saikuCube;
			}
		}
		return null;
	}
	
	/**
	 * Get default Cube. 
	 * @return
	 */
	public SaikuCube getCube(){
		System.out.println("Start getCube for " + cubeName);
		return getExistingCubeByName(cubeName);
	}
	
	/**
	 * Get Cube by name.
	 * @param name
	 * @return
	 */
	public SaikuCube getCubeByName(String name){
		return getExistingCubeByName(name);
	}
	
	/**
	 * This method attempt to check number of cube existing in the system.
	 */
	@Test
	public final void testCubeExisting(){
		List<SaikuCube> cubes = olapMetaExplorer.getCubes(connectionName);
		
		//Just list all the existing cube 
		for (SaikuCube saikuCube : cubes) {
			System.out.println("Cube name: " + saikuCube.getName());
			
		}
		//Expect number of existing cube more than zero.
		boolean condition = cubes.size() > 0;
		Assert.assertTrue(condition);
	}
	
	/**
	 * This method attempt to create a new Olap query.
	 */
	@Test
	public final void testCreateNewQuery(){
		System.out.println("Start testing create new query");
		
		//Create new olap query for testing
		SaikuQuery query = olapQueryService.createNewOlapQuery(queryName, getCube());
		
		System.out.println("Query "+query + " was created");
		
		assertNotNull(query);
		assertEquals(queryName, query.getName());
		
	}
	
	/**
	 * This method attempt execute the MDX query and get the result from resultSet.
	 * @throws Exception
	 */
	@Test
	public final void testMDXQuery() throws Exception{
		log.info("Start testing mds query");
		
		//Execute the MDX query
		CellDataSet cellDataSet = olapQueryService.executeMdx(queryName, mdxQuery_2);
		
		assertNotNull(cellDataSet);
		
		//Get the result
		AbstractBaseCell[][] abs = cellDataSet.getCellSetBody();
		
		assertNotNull(abs);
		
		int h = cellDataSet.getHeight();
		int w = cellDataSet.getWidth();
		
		System.out.println("Result Height "+h + " | Result Width "+w);
		
		System.out.println("Result value of [5][1] = "+abs[5][1].getFormattedValue());
		
	}
	
	/**
	 * This method attempt to get the non-numerical values for Measure in ResultSet.
	 * <br/>
	 * <br/>
	 * Note: This test case base on JIRA SDW-38
	 */
	@Test
	public final void testNonNumericalValues(){
		System.out.println("Start testing testNonNumericalValues");
		
		//Execute the MDX query
		CellDataSet cellDataSet = olapQueryService.executeMdx(queryName, mdxQuery_2);
		
		assertNotNull(cellDataSet);
		
		//Get the result
		AbstractBaseCell[][] abs = cellDataSet.getCellSetBody();
		
		assertNotNull(abs);
		
		int h = cellDataSet.getHeight();
		int w = cellDataSet.getWidth();
		
		System.out.println("Result Height "+h + " | Result Width "+w);
		
		String value = abs[5][1].getFormattedValue();
		
		System.out.println("Result value of [5][1] = " + value );
		
		assertNotNull(value);
		
	}
	
	/**
	 * This method attempt to check the attribute visible on measures.
	 * <br/>
	 * <br/>
	 * Note: This test case base on JIRA SDW-39
	 * 
	 * @throws Exception 
	 */
	@Test
	public final void testAttributeVisible() throws Exception{
		System.out.println("Start testing testNonNumericalValues");
		
		List<SaikuMember> members = olapMetaExplorer.getAllMeasures(getCubeByName(cubeName_2));
		
		assertNotNull(members);
		
		for (SaikuMember saikuMember : members) {
			System.out.println("Member " + saikuMember.getName() + " : Visible=" + saikuMember.getVisible());
		}
		
		
	}
	
	/**
	 * This method attempt to check the attribute visible on measures, and assert a true value.  
	 * @throws Exception 
	 */
	@Test
	public final void testAttributeVisibleTrue() throws Exception{
		System.out.println("start testAttributeVisibleTrue");
		
		List<SaikuMember> members = olapMetaExplorer.getAllMeasures(getCubeByName(cubeName_2));
		
		//Expect the result is false for this member index.
		Assert.assertTrue(members.get(memberIndex_1).getVisible());
	}
	
	/**
	 * This method attempt to check the attribute visible on measures, and assert a false value.
	 * @throws Exception 
	 */
	@Test
	public final void testAttributeVisibleFalse() throws Exception{
		System.out.println("start testAttributeVisibleFalse");
		
		List<SaikuMember> members = olapMetaExplorer.getAllMeasures(getCubeByName(cubeName_2));
		
		//Expect the result is false for this member index.
		Assert.assertFalse(members.get(memberIndex_2).getVisible());
	}
}
