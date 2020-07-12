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

		// go through and add each value only day, max, and min temp are required fields
		record.addField(DAY_LABEL, new Field.IntegerField(true));
		record.addField(MAX_TEMP_LABEL, new Field.IntegerField(true));
		record.addField(MIN_TEMP_LABEL, new Field.IntegerField(true));
		record.addField("AvT", new Field.IntegerField(false));
		record.addField("HDDay", new Field.IntegerField(false));
		record.addField("AvDP", new Field.DoubleField(false));
		record.addField("TPcpn", new Field.DoubleField(false));
		record.addField("WxType", new Field.StringField(false));
		record.addField("PDir", new Field.IntegerField(false));
		record.addField("AvSp", new Field.DoubleField(false));
		record.addField("Dir", new Field.IntegerField(false));
		record.addField("MxS", new Field.IntegerField(false));
		record.addField("SkyC", new Field.DoubleField(false));
		record.addField("MxR", new Field.IntegerField(false));
		record.addField("MnR", new Field.IntegerField(false));
		record.addField("AvSLP", new Field.DoubleField(false));

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
