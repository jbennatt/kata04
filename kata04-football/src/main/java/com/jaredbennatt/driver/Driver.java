package com.jaredbennatt.driver;

import java.util.LinkedList;
import java.util.List;

import com.jaredbennatt.football_data.LeagueData;
import com.jaredbennatt.football_data.TeamData;

public class Driver {
	public static void main(String[] args) {
		final LeagueData leagueData = LeagueData.readLeagueData(System.in);

		int minDiff = Integer.MAX_VALUE;
		final List<TeamData> minTeams = new LinkedList<>();

		for (final TeamData team : leagueData) {
			final int goalDiff = (int) Math.abs(team.getGoalsAgainst() - team.getGoalsFor());

			if (goalDiff < minDiff) {
				minTeams.clear();
				minTeams.add(team);
				minDiff = goalDiff;
			} else if (goalDiff == minDiff) {
				minTeams.add(team);
			}
		}

		for (final TeamData team : minTeams) {
			System.out.printf("%s had the smallest difference (%d) with %d goals for and %d goals against",
					team.getName(), minDiff, team.getGoalsFor(), team.getGoalsAgainst());
			System.out.println();
		}
	}
}
