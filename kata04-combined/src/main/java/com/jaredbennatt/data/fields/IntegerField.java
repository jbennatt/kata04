package com.jaredbennatt.data.fields;

import java.util.Scanner;

import com.jaredbennatt.data.Field;

public class IntegerField extends Field {

	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNextInt()) {
			this.setValue(scanner.next());
		}
	}

}
