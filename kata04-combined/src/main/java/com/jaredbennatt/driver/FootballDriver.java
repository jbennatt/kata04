package com.jaredbennatt.driver;

import com.jaredbennatt.data.Analyzer;
import com.jaredbennatt.data.football.FootballAnalyzer;
import com.jaredbennatt.data.football.FootballDataParser;

public class FootballDriver {
	public static void main(String[] args) {
		Analyzer.analyze(System.in, new FootballDataParser(), new FootballAnalyzer());
	}
}
