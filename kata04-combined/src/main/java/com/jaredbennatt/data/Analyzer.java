package com.jaredbennatt.data;

import java.io.InputStream;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public interface Analyzer<T> {

	/**
	 * Compares two measures (retrieved from the measure method). This behaves the
	 * same as compare() in a Comparator, except a null value will always be "less
	 * than" any other value (including null itself).
	 * 
	 * This effectively does the comparison t1 >=? t2, where being greater is the
	 * preferred value.
	 * 
	 * This method <em>should</em> be overriden, however the default implementation
	 * can be used to handle null cases. If the default method returns 0, then
	 * neither arguments are null.
	 * 
	 * @param t1 The value to the left of the comparison
	 * @param t2 The value to the right of the comparison
	 * @return 0 if the two are equal, positive if t1 > t2, and negative if t1 < t2.
	 *         If t1 is null, this should always return negative (we never want to
	 *         "accept" a null value), otherwise if t2 is null (and t1 is not), this
	 *         should return positive.
	 * 
	 *         The default method only handles null values, if the default method
	 *         returns 0, then neither argument is null.
	 */
	public default int compareMeasure(final T t1, final T t2) {
		if (t1 == null) {
			return -1;
		} else if (t2 == null) {
			return 1;
		}

		// else return 0, signaling neither t1 nor t2 are null
		return 0;
	}

	/**
	 * Returns an object representing the measure from this record which can then be
	 * compared to other objects of the same generic type in the compareMeasure(...)
	 * method.
	 * 
	 * @param record The Record to be measured
	 * @return An Object representing some value for this record.
	 */
	public T measure(final Record record);

	/**
	 * Creates a message for a found "best record".
	 * 
	 * @param record Record object to be printed.
	 * @return the String containing the message (e.g. "The Yankees had the most
	 *         wins with 88 games won").
	 */
	public String printBestRecord(final Record record);

	public static <T> void analyze(final Table table, final Analyzer<T> analyzer) {
		if (table.isEmpty()) {
			System.out.println("No records found for this data.");
			return;
		}

		// keep list of best records
		final List<Record> bestRecords = new LinkedList<>();
		T bestMeasure = null;

		// go through each record in the table
		for (final Record record : table) {
			final T measure = analyzer.measure(record);
			final int compared = analyzer.compareMeasure(measure, bestMeasure);

			// if it's greater, clear the list of best records and add just this one, update
			// best measure
			if (compared > 0) {
				bestRecords.clear();
				bestRecords.add(record);
				bestMeasure = measure;
			} else if (compared == 0) {
				// if they're equal just add this record to list of best records
				bestRecords.add(record);
			}
		}

		for (final Record record : bestRecords) {
			System.out.println(analyzer.printBestRecord(record));
		}
	}

	public static <T> void analyze(final InputStream input, final DataParser parser, final Analyzer<T> analyzer) {
		try {
			final Table table = parser.readInTable(input);

			Analyzer.analyze(table, analyzer);
		} catch (final ParseException pe) {
			System.out.println("On line " + pe.getErrorOffset() + ": " + pe.getMessage());
		}
	}

}
