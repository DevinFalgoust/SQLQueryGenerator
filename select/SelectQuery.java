package devinfalgoust.sqlquerygenerator.select;

import java.util.List;

import devinfalgoust.sqlquerygenerator.Query;

public class SelectQuery implements Query {

	private String tableName;
	private Boolean distinct;
	private List<String> selectFields;
	private List<SelectQueryField> whereFields;
	private List<String> orderByFields;
	private Boolean ascending;

	public SelectQuery(String tableName) {
		this.tableName = tableName;
		distinct = Boolean.FALSE;
		ascending = null;
	}

	@Override
	public String generate() {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT ");

		if (distinct) {
			queryBuilder.append("DISTINCT ");
		}

		if (selectFields.isEmpty()) {
			queryBuilder.append("*");
		} else {
			for (int i = 0; i < selectFields.size(); i++) {
				queryBuilder.append(selectFields.get(i));
				if (i < selectFields.size() - 1) {
					queryBuilder.append(", ");
				}
			}
		}

		queryBuilder.append(" FROM ").append(tableName);

		if (!whereFields.isEmpty()) {
			queryBuilder.append(" WHERE ");
			for (int i = 0; i < whereFields.size(); i++) {
				SelectQueryField field = whereFields.get(i);
				
				queryBuilder.append(field.getName());

				switch (whereFields.get(i).getOperator()) {
				case EQUAL:
					queryBuilder.append(" = ");
					break;

				case NOT_EQUAL:
					queryBuilder.append(" <> ");
					break;

				case GREATER_THAN:
					queryBuilder.append(" > ");
					break;

				case GREATER_THAN_OR_EQUAL:
					queryBuilder.append(" >= ");
					break;

				case LESS_THAN:
					queryBuilder.append(" < ");
					break;

				case LESS_THAN_OR_EQUAL:
					queryBuilder.append(" <= ");
					break;

				case LIKE:
					queryBuilder.append(" LIKE ");
					break;

				default:
					// Should never reach here
					break;
				}

				if(field.isString()) {
					queryBuilder.append("'").append(field.getStringValue()).append("'");
				} else {
					queryBuilder.append(field.getIntValue().toString());
				}

				if (i < whereFields.size() - 1) {
					queryBuilder.append(" AND ");
				}
			}
		}

		if (!orderByFields.isEmpty()) {
			queryBuilder.append(" ORDER BY ");
			for (int i = 0; i < orderByFields.size(); i++) {
				queryBuilder.append(orderByFields.get(i));
				if (i < orderByFields.size() - 1) {
					queryBuilder.append(", ");
				}
			}
		}

		if (ascending != null) {
			if (ascending) {
				queryBuilder.append(" ASC");
			} else {
				queryBuilder.append(" DESC");
			}
		}

		queryBuilder.append(";");

		return queryBuilder.toString();
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

	public void setSelectFields(List<String> selectFields) {
		this.selectFields = selectFields;
	}

	public void setWhereFields(List<SelectQueryField> whereFields) {
		this.whereFields = whereFields;
	}

	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}

}
