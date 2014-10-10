package devinfalgoust.sqlquerygenerator.insert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import devinfalgoust.sqlquerygenerator.Queries;
import devinfalgoust.sqlquerygenerator.QueryException;

/**
 * This is the class that generates the Insert Queries. a list of InsertQueryFields,
 * the tableName, and the start and end variables.
 * 
 * The start and end variables are used to calculate 1) the IDs of each
 * record and 2) the number of records to generate.
 * 
 * This class includes the org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
 * function. You can either go download this and add it to your Build Path, or
 * write your own random string generation function.
 * 
 * @author Devin Falgoust
 */
public class InsertQueryGenerator {

	private List<InsertQueryField> fields;
	private Integer start;
	private Integer end;
	private String tableName;

	/**
	 * Constructor to create the InsertQueryGenerator
	 * 
	 * @param tableName
	 *            - table that you wish to insert a record into
	 * @param start
	 *            - starting index
	 * @param end
	 *            - ending index
	 */
	public InsertQueryGenerator(String tableName, int start, int end) {
		this.tableName = tableName;
		this.start = start;
		this.end = end;
		fields = new ArrayList<InsertQueryField>();
	}

	/**
	 * This function generates a String housing all of the Queries from
	 * start to end with all of the given fields for the given tableName
	 * 
	 * @return
	 */
	public String generate() {
		Queries queries = new Queries();

		for (Integer i = start; i <= end; i++) {
			InsertQuery query = new InsertQuery(tableName);
			for (InsertQueryField field : fields) {
				String value = "";

				if (field.isID()) {
					value = i.toString();
				} else if (field.isEmail()) {
					value = RandomStringUtils.randomAlphabetic(8) + i.toString() + "@test.com";
				} else if (field.isName()) {
					value = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(7).toLowerCase();
				} else if (field.isRandomText()) {
					value = RandomStringUtils.randomAlphabetic(8);
				} else if (field.isTextFromChoices()) {
					Double rand = Math.random() * 1000;
					Integer index = rand.intValue() % field.getOptions().size();
					value = field.getOption(index);
				} else {
					// Should never reach here
				}

				query.addField(field.getName(), value);
			}
			queries.addQuery(query);
		}

		return queries.generateAll();
	}

	/**
	 * The addField function takes these parameters:
	 * 1) the name of the field
	 * 2) the type of field
	 * 3...) any options for the field. Currently these are only used when type = TEXT
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
	 * 
	 * @param name - name of the field
	 * @param type - type of field
	 * @param options - options for choosing text
	 */
	public void addField(String name, InsertQueryFieldType type, String... options) {
		InsertQueryField field = null;

		if (options != null && options.length > 0) {
			field = new InsertQueryField(name, type, options);
		} else {
			field = new InsertQueryField(name, type, new String[] { "" });
		}

		fields.add(field);
	}

	/*
	 * Getters and Setters for:
	 * start
	 * end
	 * tableName
	 */

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
