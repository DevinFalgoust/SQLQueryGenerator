import devinfalgoust.sqlquerygenerator.QueryException;
import devinfalgoust.sqlquerygenerator.insert.InsertQueryFieldType;
import devinfalgoust.sqlquerygenerator.insert.InsertQueryGenerator;
import devinfalgoust.sqlquerygenerator.select.SelectQueryFieldOperator;
import devinfalgoust.sqlquerygenerator.select.SelectQueryGenerator;

/**
 * This is the Driver class, containing the main function
 * and sample code for how each Query Generator is used.
 * 
 * @author Devin Falgoust
 */
public class Driver {

	public static void main(String args[]) {
		//demoInsertQueryGenerator();
		demoSelectQueryGenerator();
	}

	/**
	 * This function provides sample code for the InsertQueryGenerator
	 */
	public static void demoInsertQueryGenerator() {
		/*
		 * Create a new InsertQueryGenerator with these parameters:
		 * tableName = "TEST_TABLE"
		 * start = 1
		 * end = 5
		 * 
		 * The tableName is the table that you wish to insert a record into.
		 * The start and end variables are used to calculate 1) the IDs of each
		 * record and 2) the number of records to generate.
		 */
		InsertQueryGenerator generator = new InsertQueryGenerator("TEST_TABLE", 1, 5);

		/*
		 * The addField function takes these parameters:
		 * 1) the name of the field
		 * 2) the type of field
		 * 3...) any options for the field
		 * 
		 * The valid types are:
		 * ID - generates an ID starting at "start" and ending at "end"
		 * NAME - generates a random string of 8 characters where the first is capitalized
		 * EMAIL - generates a random email address. This is created using 8 random
		 *    characters, then the ID, then @test.com
		 * TEXT - adds a random text of the options given
		 *    If there are no options, it generates a random string of 8 characters
		 *    If one option is provided, it adds that text to every record
		 *    If more than one option is provided, it chooses a random option from these
		 */

		// First add the ID
		generator.addField("ID", InsertQueryFieldType.ID);

		// Then add first and last name
		generator.addField("FIRST_NAME", InsertQueryFieldType.NAME);
		generator.addField("LAST_NAME", InsertQueryFieldType.NAME);

		// Then add the email
		generator.addField("EMAIL", InsertQueryFieldType.EMAIL);

		// Then add a boolean variable
		generator.addField("BOOLEAN", InsertQueryFieldType.TEXT, "true", "false");

		// Then add a date
		generator.addField("DATE", InsertQueryFieldType.TEXT, "10032014");

		// Then add a string with two options
		generator.addField("SOURCE", InsertQueryFieldType.TEXT, "WEB", "STORE");

		// Then add a string with no options (randomly generates)
		generator.addField("DATA", InsertQueryFieldType.TEXT);

		// Then print the list of queries
		System.out.println(generator.generate());
	}

	/**
	 * This function provides sample code for the SelectQueryGenerator
	 */
	public static void demoSelectQueryGenerator() {
		try {
			// Generate Simple Select Query
			System.out.println("-- Generate Simple Select Query");
			SelectQueryGenerator generator = new SelectQueryGenerator("TEST_TABLE");
			System.out.println(generator.generate());

			// Generate Query with One Select Field
			System.out.println("\n-- Generate Query with One Select Field");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addSelectField("orderId");
			System.out.println(generator.generate());

			// Generate Query with Three Select Field
			System.out.println("\n-- Generate Query with Three Select Field");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addSelectField("orderId");
			generator.addSelectField("firstName");
			generator.addSelectField("lastName");
			System.out.println(generator.generate());

			// Generate Distinct Query
			System.out.println("\n-- Generate Distinct Query");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addSelectField("lastName");
			generator.makeDistinct();
			System.out.println(generator.generate());

			// Generate Query with One Where Clause
			System.out.println("\n-- Generate Query with One Where Clause");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addWhereField("orderId", SelectQueryFieldOperator.EQUAL, "4815162342");
			System.out.println(generator.generate());

			// Generate Query with Three Where Clauses
			System.out.println("\n-- Generate Query with Three Where Clauses");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addWhereField("firstName", SelectQueryFieldOperator.EQUAL, "John");
			generator.addWhereField("lastName", SelectQueryFieldOperator.EQUAL, "Doe");
			generator.addWhereField("birthday", SelectQueryFieldOperator.EQUAL, "0424");
			System.out.println(generator.generate());

			// Generate Query with One OrderBy
			System.out.println("\n-- Generate Query with One OrderBy");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addOrderByField("lastName");
			System.out.println(generator.generate());

			// Generate Query with Three OrderBys
			System.out.println("\n-- Generate Query with Three OrderBys");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addOrderByField("lastName");
			generator.addOrderByField("firstName");
			generator.addOrderByField("email");
			System.out.println(generator.generate());

			// Generate Query with One OrderBy Ascending
			System.out.println("\n-- Generate Query with One OrderBy");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addOrderByField("lastName");
			generator.makeOrderByAscending();
			System.out.println(generator.generate());

			// Generate Query with One OrderBy Descending
			System.out.println("\n-- Generate Query with One OrderBy");
			generator = new SelectQueryGenerator("TEST_TABLE");
			generator.addOrderByField("lastName");
			generator.makeOrderByDescending();
			System.out.println(generator.generate());
		} catch (QueryException e) {
			System.err.println("-- I Should not get here");
		}

		try {
			// Try to Generate Distinct Query without Select Fields
			System.out.println("\n-- Try to Generate Distinct Query without Select Fields");
			SelectQueryGenerator generator = new SelectQueryGenerator("TEST_TABLE");
			generator.makeDistinct();
			System.out.println(generator.generate());
			System.err.println("-- I Should not get here");

		} catch (QueryException e) {
			System.err.println("-- This failed because Distinct was selected without any Select Fields");
		}
	}

}
