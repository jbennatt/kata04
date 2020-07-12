package com.jaredbennatt.football_data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LeagueData implements Iterable<TeamData> {
	public static final int NUM_TEAMS = 20;

	// I'm assuming the number of teams doesn't change, but if so, it can be changed
	// globally with NUM_TEAMS
	private final ArrayList<TeamData> teams = new ArrayList<>(NUM_TEAMS);

	/**
	 * This is private so you must use static method to get an instance
	 */
	private LeagueData() {
		super();
	}

	@Override
	public Iterator<TeamData> iterator() {
		return teams.iterator();
	}

	public static LeagueData readLeagueData(final InputStream input) {
		final LeagueData leagueData = new LeagueData();

		try (final Scanner scanner = new Scanner(input)) {
			while (scanner.hasNextLine()) {
				final TeamData teamData = TeamData.parseLine(scanner.nextLine());
				if (teamData != null) {
					leagueData.teams.add(teamData);
				}
			}
		}

		return leagueData;
	}
}
