package com.jaredbennatt.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Table implements Iterable<Record> {

	private final List<Record> records;

	private Table(final int size) {
		super();
		this.records = new ArrayList<Record>(size);
	}

	@Override
	public Iterator<Record> iterator() {
		return records.iterator();
	}

	public static Table readTable(final InputStream input, final DataParser parser) {
		final Table table = new Table(parser.getPreferredTableSize());

		try (final Scanner scanner = new Scanner(input)) {
			// read all lines of input
			while (scanner.hasNextLine()) {
				final Record record = parser.parseLine(scanner.nextLine());

				// if the line is successfully parsed, add it to the table
				if (record != null) {
					table.records.add(record);
				}
			}
		}

		return table;
	}

}
