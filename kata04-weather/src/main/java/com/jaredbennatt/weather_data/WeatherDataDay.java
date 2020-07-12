package com.jaredbennatt.weather_data;

import java.util.Scanner;
import java.util.regex.Pattern;

public class WeatherDataDay {
	private final int day;

	private int minTemp;
	private int maxTemp;
	private int avgTemp;

	// this may be blank (so Integer makes more sense to allow null values)
	private Integer hdDay;

	private double avgDP;

	private double tpCPN;
	private String wxType;
	private int pDir;

	private double avgSP;
	private int dir;
	private int mxS;
	private double skyC;
	private int mxR;
	private int mnR;

	private double avgSLP;

	/**
	 * Making the only constructor private so that the static method below must be
	 * used
	 * 
	 * @param day Day of the month this record represents.
	 */
	private WeatherDataDay(final int day) {
		super();
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	public int getMinTemp() {
		return minTemp;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public int getAvgTemp() {
		return avgTemp;
	}

	public Integer getHdDay() {
		return hdDay;
	}

	public double getAvgDP() {
		return avgDP;
	}

	public double getTpCPN() {
		return tpCPN;
	}

	public String getWxType() {
		return wxType;
	}

	public int getpDir() {
		return pDir;
	}

	public double getAvgSP() {
		return avgSP;
	}

	public int getDir() {
		return dir;
	}

	public int getMxS() {
		return mxS;
	}

	public double getSkyC() {
		return skyC;
	}

	public int getMxR() {
		return mxR;
	}

	public int getMnR() {
		return mnR;
	}

	public double getAvgSLP() {
		return avgSLP;
	}

	@Override
	public String toString() {
		return "WeatherDataDay [day=" + day + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", avgTemp=" + avgTemp
				+ ", hdDay=" + hdDay + ", avgDP=" + avgDP + ", tpCPN=" + tpCPN + ", wxType=" + wxType + ", pDir=" + pDir
				+ ", avgSP=" + avgSP + ", dir=" + dir + ", mxS=" + mxS + ", skyC=" + skyC + ", mxR=" + mxR + ", mnR="
				+ mnR + ", avgSLP=" + avgSLP + "]";
	}

	/**
	 * This is the way to create a WeatherDataDay object. It takes a line and parses
	 * it, first checking whether or not this line contains data. If so, it returns
	 * WeatherDataDay object, if not it returns null.
	 * 
	 * @param line line of text to parse for data
	 * @return WeatherDataDay object this line represents or null if this line isn't
	 *         data.
	 */
	public static WeatherDataDay parseLine(String line) {

		try (final Scanner scanner = new Scanner(line)) {
			// using the default delimiter of whitespace for scanner

			// if there are no tokens, there are no "next ints" if there are tokens and the
			// first isn't an int, then this isn't a data line
			if (scanner.hasNextInt()) {
				return parseDataLine(scanner);
			}
			// else not a data line, return null
			return null;
		}
	}

	/**
	 * It has already been verified that this is a line of data.
	 * 
	 * @param scanner scanner which will generate tokens representing the data
	 * @return the newly created WeatherDataDay
	 */
	private static WeatherDataDay parseDataLine(final Scanner scanner) {
		final WeatherDataDay data = new WeatherDataDay(scanner.nextInt());

		// the min, max, and average are always present (and are integers)
		data.maxTemp = Integer.parseInt(removeAsterisk(scanner.next()));
		data.minTemp = Integer.parseInt(removeAsterisk(scanner.next()));
		data.avgTemp = Integer.parseInt(removeAsterisk(scanner.next()));

		// the next token may be HDDay (an integer) or average dew point (a decimal)
		if (scanner.hasNextInt()) {
			data.hdDay = scanner.nextInt();
		}
		// else there was no HDDay, so next value will be a double
		data.avgDP = scanner.nextDouble();

		data.tpCPN = Double.parseDouble(scanner.next());

		// next could be WxType (string) or could be PDir (integer)
		if (!scanner.hasNextInt()) {
			data.wxType = scanner.next();
		}
		// else if the next IS an int, there was no WxType, next value will be an
		// integer
		data.pDir = scanner.nextInt();

		// everything else should not be empty
		// the only thing left that appears to be able to have an asterisk is MxS)
		data.avgSP = scanner.nextDouble();
		data.dir = scanner.nextInt();
		data.mxS = Integer.parseInt(removeAsterisk(scanner.next()));
		data.skyC = scanner.nextDouble();
		data.mxR = scanner.nextInt();
		data.mnR = scanner.nextInt();
		data.avgSLP = scanner.nextDouble();

		return data;
	}

	/**
	 * Looks at the token and removes an asterisk is present (at the end).
	 * 
	 * @param token string to be modified
	 * @return If no asterisk is at the end, the original token, else the token with
	 *         the trailing asterisk stripped.
	 */
	private static String removeAsterisk(final String token) {
		// check last character to see if it's an asterisk, if so, remove the last
		// character
		if (token.charAt(token.length() - 1) == '*') {
			return token.substring(0, token.length() - 1);
		}
		// else there wasn't an asterisk, just return original token
		return token;
	}

}
