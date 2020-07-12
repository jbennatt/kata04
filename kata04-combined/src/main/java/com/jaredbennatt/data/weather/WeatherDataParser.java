package com.jaredbennatt.data.weather;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.jaredbennatt.data.DataParser;
import com.jaredbennatt.data.Field;
import com.jaredbennatt.data.Record;

public class WeatherDataParser extends DataParser {
	// for this delimiter I'm taking out the asterisks up front
	private static final Pattern DELIMITER_PATTERN = Pattern.compile("(\\s|\\*)+");

	public WeatherDataParser() {
		super();
	}

	@Override
	public Pattern getDelimiter() {
		return DELIMITER_PATTERN;
	}

	@Override
	public int getPreferredTableSize() {
		// there are 31 days in a month (at most)
		return 31;
	}

	@Override
	protected Record generateNewRecord(Scanner scanner) {
		if (!scanner.hasNextInt()) {
			return null;
		}
		// else this line contains data, so generate a weather record
		final Record record = new Record(LABELS.size());

		// go through and add each value
		record.addDatum(DAY_LABEL, new Field.IntegerField());
		record.addDatum(MAX_TEMP_LABEL, new Field.IntegerField());
		record.addDatum(MIN_TEMP_LABEL, new Field.IntegerField());
		record.addDatum("AvT", new Field.IntegerField());
		record.addDatum("HDDay", new Field.IntegerField());
		record.addDatum("AvDP", new Field.DoubleField());
		record.addDatum("TPcpn", new Field.DoubleField());
		record.addDatum("WxType", new Field.StringField());
		record.addDatum("PDir", new Field.IntegerField());
		record.addDatum("AvSp", new Field.DoubleField());
		record.addDatum("Dir", new Field.IntegerField());
		record.addDatum("MxS", new Field.IntegerField());
		record.addDatum("SkyC", new Field.DoubleField());
		record.addDatum("MxR", new Field.IntegerField());
		record.addDatum("MnR", new Field.IntegerField());
		record.addDatum("AvSLP", new Field.DoubleField());

		return record;
	}

	public static final String DAY_LABEL = "Dy";
	public static final String MAX_TEMP_LABEL = "MxT";
	public static final String MIN_TEMP_LABEL = "MnT";

	private static final List<String> LABELS = Collections
			.unmodifiableList(Arrays.asList(new String[] { DAY_LABEL, MAX_TEMP_LABEL, MIN_TEMP_LABEL, "AvT", "HDDay",
					"AvDP", "TPcpn", "WxType", "PDir", "AvSp", "Dir", "MxS", "SkyC", "MxR", "MnR", "AvSLP" }));

	@Override
	protected List<String> getLabels() {
		return LABELS;
	}

}
