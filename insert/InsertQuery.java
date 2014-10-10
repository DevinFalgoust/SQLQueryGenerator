package devinfalgoust.sqlquerygenerator.insert;

import java.util.ArrayList;
import java.util.List;

import devinfalgoust.sqlquerygenerator.Query;

/**
 * The InsertQuery class is used by the InsertQueryGenerator.
 * It implements the Query interface so that it can be stored
 * in a Queries object. It signifies a single Query in that list.
 * -
 * It has a tableName, and a list of field names and values
 * 
 * @author Devin Falgoust
 */
public class InsertQuery implements Query {

	private String tableName;
	private List<String> fieldNames;
	private List<String> fieldValues;

	/**
	 * Constructor setting the tableName and initializing the
	 * name and value lists
	 * 
	 * @param tableName
	 */
	public InsertQuery(String tableName) {
		this.tableName = tableName;
		fieldNames = new ArrayList<String>();
		fieldValues = new ArrayList<String>();
	}

	/**
	 * This function implements the generate function of the Query
	 * interface. It prints out an insert statement with the given
	 * table name and fields
	 * 
	 * @return
	 */
	@Override
	public String generate() {
		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO ").append(tableName).append(" (");
		for (int i = 0; i < fieldNames.size(); i++) {
			query.append(fieldNames.get(i));
			if (i < fieldNames.size() - 1) {
				query.append(", ");
			}
		}
		query.append(") VALUES (");
		for (int i = 0; i < fieldValues.size(); i++) {
			query.append("'").append(fieldValues.get(i)).append("'");
			if (i < fieldValues.size() - 1) {
				query.append(", ");
			}
		}
		query.append(");");
		return query.toString();
	}

	/**
	 * This function allows you to add a field via name-value pair
	 * 
	 * @param fieldName
	 * @param fieldValue
	 */
	public void addField(String fieldName, String fieldValue) {
		fieldNames.add(fieldName);
		fieldValues.add(fieldValue);
	}

	/*
	 * Getter and setter for tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
}
