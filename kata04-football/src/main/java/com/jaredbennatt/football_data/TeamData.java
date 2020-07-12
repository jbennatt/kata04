package com.jaredbennatt.football_data;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TeamData {
	// delimiting pattern for parsing data, this will remove all white space,
	// periods, and hyphens. The or pattern allows these to come in any order and
	// any number of times (but at least once).
	private static final Pattern DELIMITER_PATTERN = Pattern.compile("(\\s|\\.|-)+");

	private final int rank;
	private String name;
	private int gamesPlayed;
	private int wins;
	private int losses;
	private int draws;
	private int goalsFor;
	private int goalsAgainst;
	private int points;

	private TeamData(final int rank) {
		super();
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public String getName() {
		return name;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getDraws() {
		return draws;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public int getPoints() {
		return points;
	}

	/**
	 * This is the only way to get a TeamData object. It parses a line of data and
	 * creates (and returns) the object representation of that data.
	 * 
	 * @param line The line of data to be parsed.
	 * @return A TeamData object representation of this data or null if this line
	 *         does not represent data.
	 */
	public static TeamData parseLine(final String line) {
		try (final Scanner scanner = new Scanner(line)) {
			// take out more than just white space (also periods and hyphens)
			scanner.useDelimiter(DELIMITER_PATTERN);

			// lines of data start with the rank (an integer)
			if (scanner.hasNextInt()) {
				return parseDataLine(scanner);
			}
			// else this wasn't a data line, return null
			return null;
		}
	}

	/**
	 * At this point we know this is a line of data, and we've already parsed the
	 * rank, the scanner is now in the position to read the name of the team.
	 * 
	 * @param rank    rank for this team
	 * @param scanner Scanner object that's scanning the input line.
	 * @return
	 */
	private static TeamData parseDataLine(Scanner scanner) {
		final TeamData teamData = new TeamData(scanner.nextInt());

		final String name = getTeamName(scanner);
		teamData.name = name;

		teamData.gamesPlayed = scanner.nextInt();
		teamData.wins = scanner.nextInt();
		teamData.losses = scanner.nextInt();
		teamData.draws = scanner.nextInt();
		teamData.goalsFor = scanner.nextInt();
		teamData.goalsAgainst = scanner.nextInt();
		teamData.points = scanner.nextInt();

		return teamData;
	}

	/**
	 * The given data does not appear to have spaces in the names, but that seems
	 * like a reasonable requirement, so this will advance the scanner until it
	 * finds an integer, and then return the result of concatenating the tokens that
	 * make up the team name (with spaces in between).
	 * 
	 * Because of the delimiter I'm using, hyphens would be removed from team names.
	 * 
	 * @param scanner scanner who's current token is the first string of the team
	 *                name
	 * @return The String representing the name of this team. Afterwards the
	 *         scanner's next token will be an integer (the games played)
	 */
	private static String getTeamName(Scanner scanner) {
		String name = scanner.next();

		// keep going until next token is an int. This way, the current "next" WILL be
		// that integer
		while (!scanner.hasNextInt()) {
			name += " " + scanner.next();
		}

		return name;
	}

}
