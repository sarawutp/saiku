package org.saiku.service;

public class OlapTestParams {
	
	/**
	 * Default query name for testing
	 */
	public static String queryName;
	
	/**
	 * This cube name will use for testing MDX query
	 */
	public static String cubeName;
	
	/**
	 * MDX Query statement
	 */
	public static String mdxQuery;
	
	/**
	 * This cube name will use for testing visible variable in SaikuMember object.
	 */
	public static String cubeName_2;
	
	/**
	 * This is the index number for testing a true value value in the getVisible()
	 */
	public static int memberIndex_1;
	
	/**
	 * This is the index number for testing a false value value in the getVisible()
	 */
	public static int memberIndex_2;
	
	
	public static final String FAOSTAT_DATA = "faostat";
	public static final String FOODMART_DATA = "foodmart";
	
	private static void setupFoodMartDataTest(){
		queryName = "test";
		cubeName = "HR";
		mdxQuery = "SELECT NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS, NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS FROM [HR]";
		cubeName_2 = "Sales 2";
		memberIndex_1 = 0;
		memberIndex_2 = 2;
	}
	private static void setupFaoStatDataTest(){
		queryName = "test";
		cubeName = "CROPS";
		mdxQuery = "SELECT NON EMPTY {Hierarchize({[Measures].[f5510]})} ON COLUMNS, NON EMPTY {Hierarchize({[Area.Country].[Country].Members})} ON ROWS FROM [CROPS]";
		cubeName_2 = "CROPS";
		memberIndex_1 = 0;
		memberIndex_2 = 2;
	}
	
	public static void setupParams(String dataEnv){
		if(FOODMART_DATA.equals(dataEnv)){
			setupFoodMartDataTest();
		}else if(FAOSTAT_DATA.equals(dataEnv)){
			setupFaoStatDataTest();
		}
	}
	
}
