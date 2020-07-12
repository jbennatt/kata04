package com.jaredbennatt.data;

import java.util.Scanner;

public class IntegerField extends Field {

	@Override
	public void readData(final Scanner scanner) {
		if (scanner.hasNextInt()) {
			this.setValue(scanner.next());
		}
	}

}
