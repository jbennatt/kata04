package com.jaredbennatt.data.weather;

import com.jaredbennatt.data.Analyzer;
import com.jaredbennatt.data.Record;

public class WeatherAnalyzer implements Analyzer<Integer> {

	@Override
	public int compareMeasure(Integer maxDiff1, Integer maxDiff2) {
		// call default compareMeasure to handle null values
		final int cmp = Analyzer.super.compareMeasure(maxDiff1, maxDiff2);

		// one (or both) arguments are null and the default method can handle that
		if (cmp != 0) {
			return cmp;
		}

		// neither are null, just compare them as normal, I want the maximum which is
		// what this gives me (a positive indicates greater than).
		return Integer.compare(maxDiff1, maxDiff2);
	}

	@Override
	public Integer measure(Record record) {
		// find the max and min temperature then subtract
		final int maxTemp = Integer.parseInt(record.getField(WeatherDataParser.MAX_TEMP_LABEL).getValue());
		final int minTemp = Integer.parseInt(record.getField(WeatherDataParser.MIN_TEMP_LABEL).getValue());

		return maxTemp - minTemp;
	}

	@Override
	public String printBestRecord(Record record) {
		final String day = record.getField(WeatherDataParser.DAY_LABEL).getValue();
		final String maxTemp = record.getField(WeatherDataParser.MAX_TEMP_LABEL).getValue();
		final String minTemp = record.getField(WeatherDataParser.MIN_TEMP_LABEL).getValue();
		return String.format("Day %s had the largest spread (%d) with a high of %s and low of %s", day, measure(record),
				maxTemp, minTemp);
	}

}
