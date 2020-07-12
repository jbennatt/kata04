package com.jaredbennatt.data;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class DataParser {
	/**
	 * The default delimiter is whitespace
	 */
	private static final Pattern DEFAULT_DELIMITER = Pattern.compile("\\s+");

	/**
	 * Returns the expected number of records for this data
	 * 
	 * @return the number of records expected for this data
	 */
	public abstract int getPreferredTableSize();

	/**
	 * Generates a new record to be used by the method parseLine. <em>This does not
	 * fill the record with data</em>.This also is responsible for deciding whether
	 * or not the scanner contains data, if not, this will return null.
	 * 
	 * @param scanner Scanner that's being used to read the line of data into a
	 *                Record.
	 * @return A new Record object (does NOT fill with data) or null if this scanner
	 *         (the input line) does not contain data.
	 */
	protected abstract Record generateNewRecord(final Scanner scanner);

	/**
	 * This method gives the list of labels for this data set. It's best to create a
	 * static (preferrably unmodifiable) resource so that the same list is always
	 * returned
	 * 
	 * @return The list of labels that can be traversed in order to retrieve the
	 *         fields in the Record object in a specific order.
	 */
	protected abstract List<String> getLabels();

	/**
	 * Gets the delimiter pattern for parsing this data. The default is white space.
	 * If a different delimiter is needed, the extending class should overwrite this
	 * method.
	 * 
	 * @return The Pattern object to be used for the scanner's delimiter
	 */
	protected Pattern getDelimiter() {
		return DEFAULT_DELIMITER;
	}

	/**
	 * Parses this line of data into a Record object
	 * 
	 * @param line Line of data to parse
	 * @return Returns this line of data as a Record or null if this line does not
	 *         represent data.
	 */
	private Record parseLine(String line) {
		try (final Scanner scanner = new Scanner(line)) {
			scanner.useDelimiter(getDelimiter());
			final Record record = generateNewRecord(scanner);

			// check if a new record was successfully created, if not, return null,
			// otherwise parse the data
			if (record == null) {
				return null;
			}

			for (final String label : getLabels()) {
				final Field field = record.getField(label);
				field.readData(scanner);
			}

			return record;
		}
	}

	/**
	 * Reads a table of records into a Table object from input.
	 * 
	 * @param input InputStream, each line is assumed to represent a record.
	 * @return The Table object representing the data in this input stream.
	 */
	public Table readInTable(final InputStream input) {
		final Table table = new Table(this.getPreferredTableSize());

		try (final Scanner scanner = new Scanner(input)) {
			// read all lines of input
			while (scanner.hasNextLine()) {
				final Record record = this.parseLine(scanner.nextLine());

				// if the line is successfully parsed, add it to the table
				if (record != null) {
					table.addRecord(record);
				}
			}
		}

		return table;
	}

}
