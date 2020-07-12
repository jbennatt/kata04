package com.jaredbennatt.data;

import java.util.Scanner;

public class DoubleField extends Field {

	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNextDouble()) {
			this.setValue(scanner.next());
		}
	}

}
