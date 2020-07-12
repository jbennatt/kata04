package com.jaredbennatt.data;

import java.util.Scanner;

public abstract class Field {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * This reads the scanner, from it's current location, determines if the next
	 * token is valid for this field type, if so, it reads the next token into this
	 * Datum value and advances the scanner to the next token. If the token is
	 * invalid, the internal value will remain null (indicating it was blank).
	 * 
	 * @param scanner Scanner object that is being used to read a line of data.
	 */
	public abstract void readData(final Scanner scanner);
}
