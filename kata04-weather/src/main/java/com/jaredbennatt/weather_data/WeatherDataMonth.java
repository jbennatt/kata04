package com.jaredbennatt.weather_data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WeatherDataMonth implements Iterable<WeatherDataDay> {

	// just default to size 31, a month can never have more than 31 days
	private final ArrayList<WeatherDataDay> records = new ArrayList<>(31);

	/**
	 * Making this method private so that you must initialize through the static
	 * method.
	 */
	private WeatherDataMonth() {
		super();
	}

	@Override
	public Iterator<WeatherDataDay> iterator() {
		return records.iterator();
	}

	/**
	 * Reads data from text input and returns a WeatherDataMonth object.
	 * 
	 * @param input text data that contains data for this month
	 * @return the WeatherMonthData object.
	 */
	public static WeatherDataMonth readData(final InputStream input) {
		final WeatherDataMonth monthData = new WeatherDataMonth();

		// scan, line by line,
		try (final Scanner scanner = new Scanner(input)) {
			while (scanner.hasNextLine()) {
				// WeatherDayRecord will decide whether or not this is a line of data, blank,
				// headers, or the monthly aggregate
				final WeatherDataDay record = WeatherDataDay.parseLine(scanner.nextLine());

				// null indicates this was not a row of data for a day
				if (record != null) {
					monthData.records.add(record);
				}
			}
		}

		return monthData;
	}
}
