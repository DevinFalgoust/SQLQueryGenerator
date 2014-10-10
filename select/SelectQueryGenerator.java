package devinfalgoust.sqlquerygenerator.select;

import java.util.ArrayList;
import java.util.List;

import devinfalgoust.sqlquerygenerator.QueryException;

public class SelectQueryGenerator {

	private String tableName;
	private Boolean distinct;
	private List<String> selectFields;
	private List<SelectQueryField> whereFields;
	private List<String> orderByFields;
	private Boolean ascending;

	public SelectQueryGenerator(String tableName) {
		this.tableName = tableName;
		distinct = Boolean.FALSE;
		selectFields = new ArrayList<String>();
		whereFields = new ArrayList<SelectQueryField>();
		orderByFields = new ArrayList<String>();
		ascending = null;
	}

	public String generate() throws QueryException {
		SelectQuery query = new SelectQuery(tableName);

		if (distinct) {
			if (selectFields.isEmpty()) {
				throw new QueryException();
			}
			query.setDistinct(distinct);
		}

		query.setSelectFields(selectFields);
		query.setWhereFields(whereFields);
		query.setOrderByFields(orderByFields);

		if (ascending != null) {
			query.setAscending(ascending);
		}

		return query.generate();
	}

	public void addSelectField(String field) {
		selectFields.add(field);
	}

	public void addWhereField(String name, SelectQueryFieldOperator operator, String value) {
		whereFields.add(new SelectQueryField(name, operator, value));
	}

	public void addOrderByField(String field) {
		orderByFields.add(field);
	}

	public void makeDistinct() {
		distinct = Boolean.TRUE;
	}

	public void makeOrderByAscending() {
		ascending = Boolean.TRUE;
	}

	public void makeOrderByDescending() {
		ascending = Boolean.FALSE;
	}

}
