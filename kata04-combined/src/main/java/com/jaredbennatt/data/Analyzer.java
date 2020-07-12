package com.jaredbennatt.data;

import java.io.InputStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This implementation of compareTo should accept null values and any non-value
 * null value should be "greater than" a null value (if both are null, it should
 * be less than--to imply inequality).
 * 
 * @author Jared F Bennatt
 *
 */
public interface Analyzer<T> {

	/**
	 * Compares two measures (retrieved from the measure method). This behaves the
	 * same as compare() in a Comparator, except a null value will always be "less
	 * than" any other value (including null itself).
	 * 
	 * This effectively does the comparison t1 >=? t2.
	 * 
	 * @param t1 The value to the left of the comparison
	 * @param t2 The value to the right of the comparison
	 * @return 0 if the two are equal, positive if t1 > t2, and negative if t1 < t2.
	 *         If t2 is null, this should return positive <em>unless</em> t1 is
	 *         <em>also</em> null, in which case it should return a negative.
	 *         Simarly, if t1 is null, this should always return negative.
	 */
	public int compareMeasure(final T t1, final T t2);

	/**
	 * Returns a Measure object from this record which can then be compared to other
	 * Measure objects.
	 * 
	 * @param record The Record to be measured
	 * @return Measure object representing some value for this record.
	 */
	public T measure(final Record record);

	/**
	 * Creates a message for a found "best record".
	 * 
	 * @param record Record object to be printed.
	 * @return the String containing the message (e.g. "The Yankees had the most
	 *         wins with 88").
	 */
	public String printBestRecord(final Record record);

	public static <T> void analyze(final InputStream input, final DataParser parser, final Analyzer<T> analyzer) {
		final Table table = Table.readTable(input, parser);

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

}
