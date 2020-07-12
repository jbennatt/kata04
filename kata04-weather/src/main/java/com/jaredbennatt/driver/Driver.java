package com.jaredbennatt.driver;

import java.util.LinkedList;
import java.util.List;

import com.jaredbennatt.weather_data.WeatherDataDay;
import com.jaredbennatt.weather_data.WeatherDataMonth;

public class Driver {

	public static void main(String[] args) {
		// read from standard in
		final WeatherDataMonth weatherData = WeatherDataMonth.readData(System.in);

		// there may be a tie for greatest spread, so get all of them
		final List<WeatherDataDay> maxDays = new LinkedList<>();
		int maxSpread = -1;

		for (final WeatherDataDay dayData : weatherData) {
			final int spread = dayData.getMaxTemp() - dayData.getMinTemp();

			// this is the new max, clear the previous list, and place this day alone, in it
			if (spread > maxSpread) {
				maxDays.clear();
				maxDays.add(dayData);
				maxSpread = spread;
			} else if (spread == maxSpread) {
				// this is the same as the current maximum, so add this day to the list.
				maxDays.add(dayData);
			}
		}

		// print out all of the days
		for (final WeatherDataDay maxDay : maxDays) {
			System.out.printf("Day %d had the largest spread (%d) with a high of %d and low of %d", maxDay.getDay(),
					maxSpread, maxDay.getMaxTemp(), maxDay.getMinTemp());
			System.out.println();
		}
	}

}
