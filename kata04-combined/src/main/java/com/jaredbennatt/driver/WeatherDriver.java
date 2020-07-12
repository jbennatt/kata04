package com.jaredbennatt.driver;

import com.jaredbennatt.data.Analyzer;
import com.jaredbennatt.data.weather.WeatherAnalyzer;
import com.jaredbennatt.data.weather.WeatherDataParser;

public class WeatherDriver {
	public static void main(String[] args) {
		Analyzer.analyze(System.in, new WeatherDataParser(), new WeatherAnalyzer());
	}
}
