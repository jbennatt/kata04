package com.jaredbennatt.data.weather;

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
		record.addDatum(DAY_LABEL, new IntegerField());
		record.addDatum(MAX_TEMP_LABEL, new IntegerField());
		record.addDatum(MIN_TEMP_LABEL, new IntegerField());
		record.addDatum("AvT", new IntegerField());
		record.addDatum("HDDay", new IntegerField());
		record.addDatum("AvDP", new DoubleField());
		record.addDatum("TPcpn", new DoubleField());
		record.addDatum("WxType", new StringField());
		record.addDatum("PDir", new IntegerField());
		record.addDatum("AvSp", new DoubleField());
		record.addDatum("Dir", new IntegerField());
		record.addDatum("MxS", new IntegerField());
		record.addDatum("SkyC", new DoubleField());
		record.addDatum("MxR", new IntegerField());
		record.addDatum("MnR", new IntegerField());
		record.addDatum("AvSLP", new DoubleField());

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
