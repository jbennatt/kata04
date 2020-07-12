package com.jaredbennatt.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Iterable<Record> {

	private final List<Record> records;

	public Table(final int size) {
		super();
		this.records = new ArrayList<>(size);
	}

	@Override
	public Iterator<Record> iterator() {
		return records.iterator();
	}

	public void addRecord(final Record record) {
		this.records.add(record);
	}

	public boolean isEmpty() {
		return records.isEmpty();
	}
}
