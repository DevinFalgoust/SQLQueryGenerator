package devinfalgoust.sqlquerygenerator;

/**
 * A Generic Query Interface with a generate function. This is here so that every
 * indivitual QueryGenerator class can use the Queries class instead of maintaining
 * a list of Query objects.
 * 
 * @author Devin Falgoust
 */
public interface Query {

	/**
	 * This function, when overridden, should generate the query statement and
	 * return it as a String so it may be printed.
	 * 
	 * @return
	 * @throws QueryException
	 */
	public String generate() throws QueryException;

}
