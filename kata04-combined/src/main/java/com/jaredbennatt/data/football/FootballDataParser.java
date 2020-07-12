package com.jaredbennatt.data.football;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.jaredbennatt.data.DataParser;
import com.jaredbennatt.data.Record;
import com.jaredbennatt.data.fields.DoubleField;
import com.jaredbennatt.data.fields.IntegerField;
import com.jaredbennatt.data.fields.StringField;

public class FootballDataParser extends DataParser {
	// delimiting pattern for parsing data, this will remove all white space,
	// periods, and hyphens. The or pattern allows these to come in any order and
	// any number of times (but at least once).
	private static final Pattern DELIMITER_PATTERN = Pattern.compile("(\\s|\\.|-)+");

	private static final int NUM_TEAMS = 20;

	@Override
	public int getPreferredTableSize() {
		return NUM_TEAMS;
	}

	@Override
	protected Record generateNewRecord(Scanner scanner) {
		if (!scanner.hasNextInt()) {
			return null;
		}
		// else this line contains data, so generate a weather record
		final Record record = new Record(LABELS.size());

		// go through and add each value
		record.addDatum("Rank", new IntegerField());
		record.addDatum(TEAM_LABEL, new StringField());
		record.addDatum("P", new IntegerField());
		record.addDatum("W", new IntegerField());
		record.addDatum("L", new IntegerField());
		record.addDatum("D", new IntegerField());
		record.addDatum(GOALS_FOR_LABEL, new DoubleField());
		record.addDatum(GOALS_AGAINST_LABEL, new IntegerField());
		record.addDatum("Pts", new IntegerField());

		return record;
	}

	public static final String TEAM_LABEL = "Team";
	public static final String GOALS_FOR_LABEL = "F";
	public static final String GOALS_AGAINST_LABEL = "A";

	private static final List<String> LABELS = Collections.unmodifiableList(Arrays.asList(
			new String[] { "Rank", TEAM_LABEL, "P", "W", "L", "D", GOALS_FOR_LABEL, GOALS_AGAINST_LABEL, "Pts" }));

	@Override
	protected List<String> getLabels() {
		return LABELS;
	}

	@Override
	protected Pattern getDelimiter() {
		return DELIMITER_PATTERN;
	}

}
