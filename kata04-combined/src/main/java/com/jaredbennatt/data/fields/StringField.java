package com.jaredbennatt.data.fields;

import java.util.Scanner;

import com.jaredbennatt.data.Field;

public class StringField extends Field {
	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNext() && !scanner.hasNextDouble()) {
			this.setValue(scanner.next());
		}
	}

}
