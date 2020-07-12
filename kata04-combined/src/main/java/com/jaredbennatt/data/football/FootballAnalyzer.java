package com.jaredbennatt.data.football;

import com.jaredbennatt.data.Analyzer;
import com.jaredbennatt.data.Record;

public class FootballAnalyzer implements Analyzer<Integer> {

	@Override
	public int compareMeasure(Integer goalsDiff1, Integer goalsDiff2) {
		if (goalsDiff2 == null) {
			return 1;
		} else if (goalsDiff1 == null) {
			return -1;
		} else {
			// here we want the minimum, so we need to reverse the natural ordering (without
			// this, we'll find the maximum difference).
			return -Integer.compare(goalsDiff1, goalsDiff2);
		}
	}

	@Override
	public Integer measure(Record record) {
		final int goalsFor = Integer.parseInt(record.getField(FootballDataParser.GOALS_FOR_LABEL).getValue());
		final int goalsAgainst = Integer.parseInt(record.getField(FootballDataParser.GOALS_AGAINST_LABEL).getValue());

		return (int) Math.abs(goalsFor - goalsAgainst);
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
