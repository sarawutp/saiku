package org.saiku.service;

/**
 * 
 * All the params in this class has been used with OlapQueryServicetest.class and OlapMetaExplorer.class
 *
 */
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
	 * The index number of cube name in the list
	 */
	public static int cubeNameIndex;
	
	/**
	 * Total number of cube from schema
	 */
	public static int totalNumberOfCubes;
	
	
	public static String catalogName;
	
	public static String schemaName;
	
	public static String[] cubesInOrder;
	
	/**
	 * Total number of dimension in cube. This variable is relate to variable "cubeName"
	 */
	public static int numberOfDimension;
	
	/**
	 * Dimension name in cube. This variable is relate to variable "cubeName"
	 */
	public static String dimName;
	
	/**
	 * Total number of Hierarchy in Cube.  This variable is relate to variable "cubeName"
	 */
	public static int numberOfHierarchy;
	
	/**
	 * Hierarchy name  in Cube.  This variable is relate to variable "cubeName"
	 */
	public static String hierarchyName;
	
	public static String uniqueHierarchyName;
	
	public static int level;
	
	public static String levelName;
	
	public static String uniqueLevelName;
	
	public static int totalMember;
	
	public static String uniqueMemberName;
	
	public static int totalNumberMemeberChildren;
	
	public static int totalNumberMeasure;
	public static int measureName;
	public static int uniqueMeasureName;
	
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
		cubeNameIndex = 0;
		totalNumberOfCubes = 8;
		catalogName = "FoodMart";
		schemaName = "FoodMart";
		
		String[] names = { "HR","Sales 2","Sales Ragged","Sales Scenario","Sales","Store","Warehouse and Sales","Warehouse"};
		
		cubesInOrder = names;
		
		numberOfDimension = 7;
		
		dimName = "Department";
		
		numberOfHierarchy = 8;
		
		hierarchyName = "Department";
		
		level = 2;
		
		uniqueHierarchyName = "[Department]";
		
		levelName = "Department Description";
		
		uniqueLevelName = "[Department].[Department Description]";
		
		totalMember = 12;
		
		uniqueMemberName = "[Department].[All Departments]";
		
		totalNumberMemeberChildren = 12;
		
		totalNumberMeasure = 4;
		
		mdxQuery = "SELECT NON EMPTY {Hierarchize({[Measures].[Count], [Measures].[Number of Employees], [Measures].[Org Salary], [Measures].[Avg Salary]})} ON COLUMNS, NON EMPTY {Hierarchize({[Store Type].[Store Type].Members})} ON ROWS FROM [HR]";
		cubeName_2 = "Sales 2";
		memberIndex_1 = 0;
		memberIndex_2 = 2;
	}
	private static void setupFaoStatDataTest(){
		queryName = "test";
		cubeName = "CROPS";
		cubeNameIndex = 1;
		totalNumberOfCubes = 7;
		catalogName = "Faostat Production - en";
		schemaName = "Faostat Production - en";
		
		String[] names = { "CROPSPROCESSED","CROPS","LIVEANIMALS","LIVESTOCKPRIMARY","LIVESTOCKPROCESSED","PIN","PRODUCTIONVALUE" };
		
		cubesInOrder = names;
		
		numberOfDimension = 3;
		
		dimName = "Year";
		
		numberOfHierarchy = 5;
		
		hierarchyName = "Year.Year";
		
		level = 2;
		
		uniqueHierarchyName = "[Year]";
		
		levelName = "Year";
		
		uniqueLevelName = "[Year].[Year]";
		
		totalMember = 111;
		
		uniqueMemberName = "[Year].[2000]";
		
		totalNumberMemeberChildren = 0;
		
		totalNumberMeasure = 9;
		
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
