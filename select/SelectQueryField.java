package devinfalgoust.sqlquerygenerator.select;

public class SelectQueryField {

	private String name;
	private SelectQueryFieldOperator operator;
	private String stringValue;
	private Integer intValue;

	public SelectQueryField(String name, SelectQueryFieldOperator operator, String value) {
		this.name = name;
		this.operator = operator;
		stringValue = value;
	}
	
	public SelectQueryField(String name, SelectQueryFieldOperator operator, Integer value) {
		this.name = name;
		this.operator = operator;
		intValue = value;
	}

	public String getName() {
		return name;
	}

	public SelectQueryFieldOperator getOperator() {
		return operator;
	}
	
	public Boolean isString() {
		if(intValue == null) {
			return true;
		} else {
			return false;
		}
	}

	public String getStringValue() {
		return stringValue;
	}
	
	public Integer getIntValue() {
		return intValue;
	}

}
