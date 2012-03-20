package org.saiku.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.saiku.AbstractServiceUtils;
import org.saiku.olap.dto.SaikuConnection;
import org.saiku.olap.dto.SaikuCube;
import org.saiku.olap.dto.SaikuMember;
import org.saiku.olap.dto.SaikuQuery;
import org.saiku.olap.dto.resultset.AbstractBaseCell;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.util.exception.SaikuOlapException;

public class OlapQueryServiceTest extends ServiceTest{
	
	/*
	 * {HR Cube:}
	 * SELECT
		NON EMPTY {Hierarchize({[Measures].[Count]})} ON COLUMNS,
		NON EMPTY {Hierarchize({[Department].[Department Description].Members})} ON ROWS
		FROM [HR]
	 */
	
	private static final String mdxQuery = "SELECT NON EMPTY {Hierarchize({[Measures].[Count]})} ON COLUMNS, NON EMPTY {Hierarchize({[Department].[Department Description].Members})} ON ROWS FROM [HR]";
	
	/*
	 * {HR Cube:}
	 * SELECT
		NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS,
		NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS
		FROM [HR]
	 * */
	private static final String mdxQuery_2 = "SELECT NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS, NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS FROM [HR]";
	
	private static final String queryName = "test";
	private static final String cubeName = "HR";
	
	/**
	 * Get Cube from current connection 
	 * @return
	 */
	public SaikuCube getCube(){
		System.out.println("Start getCube for " + cubeName);
		
		List<SaikuCube> cubes = olapMetaExplorer.getCubes(connectionName); 
		for (SaikuCube saikuCube : cubes) {
			if(cubeName.equals(saikuCube.getName())){
				return saikuCube;
			}
		}
		
		
		return null;
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
		System.out.println("Start testing mds query");
		
		//Execute the MDX query
		CellDataSet cellDataSet = olapQueryService.executeMdx(queryName, mdxQuery_2);
		
		assertNotNull(cellDataSet);
		
		//Get the result
		AbstractBaseCell[][] abs = cellDataSet.getCellSetBody();
		
		assertNotNull(abs);
		
		int h = cellDataSet.getHeight();
		int w = cellDataSet.getWidth();
		
		System.out.println("Result Height "+h);
		System.out.println("Result Width "+w);
		
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
		
		System.out.println("Result Height "+h);
		System.out.println("Result Width "+w);
		
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
		
		List<SaikuMember> members = olapMetaExplorer.getAllMeasures(getCube());
		for (SaikuMember saikuMember : members) {
			boolean visible = saikuMember.getVisible();
			System.out.println("Member visible = " + visible);
			if(visible){
				Assert.assertTrue(visible);
			}else{
				Assert.assertFalse(visible);
			}
		}
	}
}
