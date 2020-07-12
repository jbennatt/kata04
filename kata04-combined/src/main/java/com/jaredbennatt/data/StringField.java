package com.jaredbennatt.data;

import java.util.Scanner;

public class StringField extends Field {
	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNext() && !scanner.hasNextDouble()) {
			this.setValue(scanner.next());
		}
	}

}
