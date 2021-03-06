package com.cs2013t143j.TaskBuddyM.Logic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.cs2013t143j.TaskBuddyM.Storage.Storage;
import com.cs2013t143j.TaskBuddyM.Storage.Task;


public class Adder {
	
	private Storage storage;
	
	private final String ADD_DESCRIPTION = "description";
	private final String ADD_START = "startDate";
	private final String ADD_END = "endDate";
	
	private final String DATE_FORMAT1 = "HH dd/MM/yyyy";
	private final String DATE_FORMAT2 = "HH dd/M/yyyy";
	
	private final String ADD_OUTPUT = "Added new Task to TaskBuddy\n\n";
	
	public Adder(Storage storage) {
			this.storage = storage;
	}
	
	public String add(Map<String, String> parsedCommand) {
		String description = parsedCommand.get(ADD_DESCRIPTION);
		String startDate = parsedCommand.get(ADD_START);
		String endDate = parsedCommand.get(ADD_END);
		
		LocalDateTime start = convertDateTime(startDate);
		LocalDateTime end = convertDateTime(endDate);
		
		Task task;
		
		if (endDate == null) {
			task = new Task(description);
			//Floating Task
		} else if (startDate == null) {
			task = new Task(description, end);
			//DeadLine
		} else {
			task = new Task(description, start, end);
			//Event
		}
		
		storage.add(task);
		
		return ADD_OUTPUT;
	}
	
	private LocalDateTime convertDateTime(String dateTime) {
		
		if (dateTime == null) {
			return null;
		}
		String[] splitDateTime = dateTime.split(" ");
		DateTimeFormatter formatter;
		LocalDateTime dt;
		
		if (splitDateTime.length == 1) {
			//No time specified
			try {
				formatter = DateTimeFormatter.ofPattern(DATE_FORMAT1);
				dt = LocalDateTime.parse("2359 " + dateTime, formatter);
			} catch (Exception e) {
				formatter = DateTimeFormatter.ofPattern(DATE_FORMAT2);
				dt = LocalDateTime.parse("2359 " + dateTime, formatter);
			}
			
			
			return dt;
		} else {
			//Time specified
			try {
				formatter = DateTimeFormatter.ofPattern(DATE_FORMAT1);
				dt = LocalDateTime.parse(dateTime, formatter);
			} catch (Exception e) {
				formatter = DateTimeFormatter.ofPattern(DATE_FORMAT2);
				dt = LocalDateTime.parse(dateTime, formatter);
			}
			
			
			return dt;
		}
	
	}
}
