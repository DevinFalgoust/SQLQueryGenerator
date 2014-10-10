package devinfalgoust.sqlquerygenerator.insert;

import java.util.ArrayList;
import java.util.List;

/**
 * The InsertQueryField Class houses the information for a given field
 * within a given query. Each field has a name, a type, and a list
 * of choices (that may sometimes be empty)
 * 
 * @author Devin Falgoust
 */
public class InsertQueryField {

	private String name;
	private InsertQueryFieldType type;
	private List<String> options;

	/**
	 * Constructor initiating the name, type, and any options
	 * 
	 * @param queryName - name of the query
	 * @param type - type of query
	 * @param options - options to choose text from
	 */
	public InsertQueryField(String queryName, InsertQueryFieldType type, String... options) {
		name = queryName;
		this.type = type;
		this.options = new ArrayList<String>();
		for (String s : options) {
			this.options.add(s);
		}
	}

	/**
	 * Returns True if the type is ID, and false otherwise
	 * 
	 * @return
	 */
	public Boolean isID() {
		return type.equals(InsertQueryFieldType.ID);
	}

	/**
	 * Returns True if the type is EMAIL, and false otherwise
	 * 
	 * @return
	 */
	public Boolean isEmail() {
		return type.equals(InsertQueryFieldType.EMAIL);
	}

	/**
	 * Returns True if the type is NAME, and false otherwise
	 * 
	 * @return
	 */
	public Boolean isName() {
		return type.equals(InsertQueryFieldType.NAME);
	}

	/**
	 * Returns True if
	 * 1) the type is TEXT
	 * 2) there are no options in the list
	 * Returns false otherwise
	 * 
	 * @return
	 */
	public Boolean isRandomText() {
		Boolean random = options.size() == 1 && "".equals(getOption(0));
		return type.equals(InsertQueryFieldType.TEXT) && random;
	}

	/**
	 * Returns True if
	 * 1) the type is TEXT
	 * 2) there is at least one option in the list
	 * Returns false otherwise
	 * 
	 * @return
	 */
	public Boolean isTextFromChoices() {
		Boolean random = options.size() == 1 && "".equals(getOption(0));
		return type.equals(InsertQueryFieldType.TEXT) && !random;
	}

	/**
	 * Getter for name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the option at the specified index
	 * 
	 * @param index
	 * @return
	 */
	public String getOption(int index) {
		return options.get(index);
	}

	/**
	 * Returns the list of options
	 * 
	 * @return
	 */
	public List<String> getOptions() {
		return options;
	}

}
