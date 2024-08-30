package it.tino.postgres.database;

/**
 * Used to apply custom criteria in the WHERE clause of queries.
 */
public class Criteria {

	/**
	 * The name of the column to operate on.
	 */
	private String field;

	/**
	 * E.g. "=", ">", "LIKE"...
	 */
	private String operator;

	private Object value;
	
	public Criteria(String field, String operator, Object value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
}
