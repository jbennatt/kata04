package com.jaredbennatt.data.fields;

import java.util.Scanner;

import com.jaredbennatt.data.Field;

public class NameField extends Field {
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
