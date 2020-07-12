package com.jaredbennatt.data.football;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.jaredbennatt.data.DataParser;
import com.jaredbennatt.data.Field;
import com.jaredbennatt.data.Record;

public class FootballDataParser extends DataParser {
	// delimiting pattern for parsing data, this will remove all white space,
	// periods, and hyphens. The "or pattern" allows these to come in any order and
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

		// go through and add each value (all fields are required)
		record.addField("Rank", new Field.IntegerField(true));
		record.addField(TEAM_LABEL, new Field.StringField(true));
		record.addField("P", new Field.IntegerField(true));
		record.addField("W", new Field.IntegerField(true));
		record.addField("L", new Field.IntegerField(true));
		record.addField("D", new Field.IntegerField(true));
		record.addField(GOALS_FOR_LABEL, new Field.DoubleField(true));
		record.addField(GOALS_AGAINST_LABEL, new Field.IntegerField(true));
		record.addField("Pts", new Field.IntegerField(true));

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
