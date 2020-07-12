package com.jaredbennatt.data;

import java.util.Scanner;

public abstract class Field {

	public static class DoubleField extends Field {

		public DoubleField(final boolean required) {
			super(required);
		}

		@Override
		public void readData(Scanner scanner) {
			if (scanner.hasNextDouble()) {
				this.setValue(scanner.next());
			}
		}
	}

	public static class IntegerField extends Field {

		public IntegerField(final boolean required) {
			super(required);
		}

		@Override
		public void readData(final Scanner scanner) {
			if (scanner.hasNextInt()) {
				this.setValue(scanner.next());
			}
		}
	}

	public static class NameField extends Field {

		public NameField(final boolean required) {
			super(required);
		}

		@Override
		public void readData(final Scanner scanner) {
			if (scanner.hasNext() && !scanner.hasNextDouble()) {
				String name = scanner.next();
				while (scanner.hasNext() && !scanner.hasNextDouble()) {
					name += " " + scanner.next();
				}

				this.setValue(name);
			}
		}
	}

	public static class StringField extends Field {

		public StringField(final boolean required) {
			super(required);
		}

		@Override
		public void readData(final Scanner scanner) {
			if (scanner.hasNext() && !scanner.hasNextDouble()) {
				this.setValue(scanner.next());
			}
		}
	}

	private String value;
	private final boolean required;

	protected Field(final boolean required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isNull() {
		return value == null;
	}

	/**
	 * This reads the scanner, from it's current location, determines if the next
	 * token is valid for this field type, if so, it reads the next token into this
	 * field value and advances the scanner to the next token. If the token is
	 * invalid, the internal value will remain null (indicating it was blank).
	 * 
	 * @param scanner Scanner object that is being used to read a line of data.
	 */
	public abstract void readData(final Scanner scanner);
}
