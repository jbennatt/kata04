package com.jaredbennatt.data.fields;

import java.util.Scanner;

import com.jaredbennatt.data.Field;

public class DoubleField extends Field {

	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNextDouble()) {
			this.setValue(scanner.next());
		}
	}

}
