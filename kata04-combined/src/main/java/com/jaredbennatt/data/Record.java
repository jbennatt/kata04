package com.jaredbennatt.data;

import java.util.HashMap;
import java.util.Map;

public class Record {
	private final Map<String, Field> fields;

	public Record(final int size) {
		this.fields = new HashMap<>(size);
	}

	public void addField(final String label, final Field field) {
		fields.put(label, field);
	}

	public Field getField(final String label) {
		return fields.get(label);
	}
}
