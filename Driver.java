import devinfalgoust.sqlquerygenerator.insert.InsertQueryFieldType;
import devinfalgoust.sqlquerygenerator.insert.InsertQueryGenerator;

/**
 * This is the Driver class, containing the main function
 * and sample code for how each Query Generator is used.
 * 
 * @author Devin Falgoust
 */
public class Driver {

	public static void main(String args[]) {
		demoInsertQueryGenerator();
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

}
