package com.jaredbennatt.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Record implements Iterable<Field> {
	private final Map<String, Field> fields;

	public Record(final int size) {
		this.fields = new HashMap<>(size);
	}

	@Override
	public Iterator<Field> iterator() {
		return fields.values().iterator();
	}

	public void addDatum(final String label, final Field datum) {
		fields.put(label, datum);
	}

	public Field getField(final String label) {
		return fields.get(label);
	}
}
