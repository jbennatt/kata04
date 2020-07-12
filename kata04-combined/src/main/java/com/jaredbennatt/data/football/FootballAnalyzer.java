package com.jaredbennatt.data.football;

import com.jaredbennatt.data.Analyzer;
import com.jaredbennatt.data.Record;

public class FootballAnalyzer implements Analyzer<Integer> {

	@Override
	public int compareMeasure(Integer goalsDiff1, Integer goalsDiff2) {
		// the default method handles null values
		final int cmp = Analyzer.super.compareMeasure(goalsDiff1, goalsDiff2);

		// one (or both) of the arguments are null
		if (cmp != 0) {
			return cmp;
		}
		// else neither argument is null and they can be compared as normal

		// here we want the minimum, so we need to reverse the natural ordering (without
		// this, we'll find the maximum difference).
		return -Integer.compare(goalsDiff1, goalsDiff2);
	}

	@Override
	public Integer measure(Record record) {
		try {
			final int goalsFor = Integer.parseInt(record.getField(FootballDataParser.GOALS_FOR_LABEL).getValue());
			final int goalsAgainst = Integer
					.parseInt(record.getField(FootballDataParser.GOALS_AGAINST_LABEL).getValue());

			return (int) Math.abs(goalsFor - goalsAgainst);
		} catch (final NumberFormatException nfe) {
			// the only way for a number format exception to occur is if one (or both) of
			// the fields are null, so return null for the measure (meaning the measurement
			// wasn't possible)
			return null;
		}
	}

	@Override
	public String printBestRecord(Record record) {
		final String team = record.getField(FootballDataParser.TEAM_LABEL).getValue();
		final String goalsFor = record.getField(FootballDataParser.GOALS_FOR_LABEL).getValue();
		final String goalsAgainst = record.getField(FootballDataParser.GOALS_AGAINST_LABEL).getValue();

		return String.format("%s had the smallest difference (%d) with %s goals for and %s goals against", team,
				measure(record), goalsFor, goalsAgainst);
	}

}
