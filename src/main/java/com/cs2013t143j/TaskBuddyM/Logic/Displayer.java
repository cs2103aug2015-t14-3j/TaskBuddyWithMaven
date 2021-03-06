package com.cs2013t143j.TaskBuddyM.Logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import com.cs2013t143j.TaskBuddyM.Storage.Storage;
import com.cs2013t143j.TaskBuddyM.Storage.Task;
public class Displayer {
	
	private Storage storage;
	private ArrayList<Task> tasks;
	String output;
	
	private final String DISPLAY_SUB = "subCommand";
	private final String DISPLAY_DATE = "date";
	
	private final String DISPLAY_HEADER_DATE = "Here is your schedule for %s:\n\nDescription         Start Date          End Date\n";
	private final String DISPLAY_HEADER_ALL = "Here is your entire schedule:\n\nDescription         Start Date          End Date\n";
	private final String DISPLAY_FORMAT = "%d.%-18s%-20s%s\n";
	private final String FREE_DAY = "Looks like there is nothing on your schedule. Enjoy your day!!!\n\n";
	
	private final String INVALID_DISPLAY_SUB = "Invalid display subcommand specified\n";

	public Displayer(Storage storage) {
		this.storage = storage;
		tasks = new ArrayList<Task>();
	}
	
	public String display(Map<String, String> parsedCommand) {
		output = new String();
		String subCommand = parsedCommand.get(DISPLAY_SUB);
		String date = parsedCommand.get(DISPLAY_DATE);

		ArrayList<Task> allTasks = storage.display();
		
		if (subCommand == null) {
			//Display All
			tasks = extractAll(allTasks);
		} else {
			switch (subCommand) {
			case "on":
				tasks = extractOn(allTasks, date);
				break;
			case "from":
//				tasks = extractFrom(allTasks, date);
				break;
			case "after":
//				tasks = extractAfter(allTasks, date);
				break;
			case "due":
//				tasks = extractDue(allTasks, date);
				break;
			case "incomplete":
//				tasks = extractIncomplete(allTasks);
				break;
			case "floating":
//				tasks = extractFloating(allTasks);
				break;
			default:
				return INVALID_DISPLAY_SUB;
			}
		}		
		
		if (tasks.size() == 0) {
			output = FREE_DAY;
			return output;
		}
		
		Collections.sort(tasks, new TaskSorter());
		
		int index = 1;
		
		int i = 0;
		
		for (i = 0; i < tasks.size(); ++i) {
			Task task = tasks.get(i);
			
			String description = task.getDescription();
			String start = task.getStartDateTimeInString();
			String end = task.getEndDateTimeInString();
			
			output += String.format(DISPLAY_FORMAT, index, description, start, end);			
			++index;
		}
		
		output += "\n";
		return output;

		
//		return "command: display " + "sub: " + subCommand + " date: " + date + "\n";
	}
	
	public ArrayList<Task> getLastDisplay() {
		return tasks;
	}
	
	private ArrayList<Task> extractOn(ArrayList<Task> allTasks, String _date) {
		ArrayList<Task> result = new ArrayList<Task>();
		
		String date = convertDate(_date);
		output = String.format(DISPLAY_HEADER_DATE, _date);
		
		int i;
		String startDate;
		String endDate;
		
		for (i=0; i<allTasks.size(); ++i) {
			
			Task task = allTasks.get(i);
			
			startDate = task.getStartDateTimeInString();
			endDate = task.getEndDateTimeInString();
			
			if (startDate != null && startDate.contains(date)) {
				result.add(task);
			} else if (endDate != null && endDate.contains(date)) {
				result.add(task);
			} 
		}
		
		return result;
	}
	
	private String convertDate(String date) {
		String[] splitDate = date.split("/");
		
		return splitDate[0] + "-" + splitDate[1] + "-" + splitDate[2];
	}
	
	private ArrayList<Task> extractAll(ArrayList<Task> allTasks) {
		output = DISPLAY_HEADER_ALL;
		return allTasks;
	}

}
