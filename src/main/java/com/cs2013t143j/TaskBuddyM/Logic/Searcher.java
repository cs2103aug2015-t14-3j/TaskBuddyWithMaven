package com.cs2013t143j.TaskBuddyM.Logic;
import java.util.Map;
import com.cs2013t143j.TaskBuddyM.Storage.Storage;
import com.cs2013t143j.TaskBuddyM.Storage.Task;
import java.util.ArrayList;

public class Searcher {

	private Storage storage;
	
	private final String SEARCH_HEADER = "Search returned %d result(s):\n";
	private final String DISPLAY_FORMAT = "%d.%s\n";
	private final String NO_RESUTS = "Search returned no results\n\n";
	
	private ArrayList<Task> result = new ArrayList<Task>();
	
	private final String SEARCH_KEY = "searchKey";
	
	public Searcher(Storage storage) {
		this.storage = storage;
	}
	
	public String search(Map<String, String> parsedCommand) {
		String searchKey = parsedCommand.get(SEARCH_KEY);
		
		ArrayList<Task> allTasks = storage.display();
		result.clear();
		
		int i;
		
		for (i=0; i<allTasks.size(); ++i) {
			Task task = allTasks.get(i);
			
			if (task.getDescription().contains(searchKey)) {
				result.add(task);
			}
		}
		
		if (result.size() == 0) {
			return NO_RESUTS;
		} 
		
		String output;
		
		output = String.format(SEARCH_HEADER, result.size());
		int index = 1;
		
		for (i = 0; i < result.size(); ++i) {
			Task task = result.get(i);
			
			String description = task.getDescription();

			output += String.format(DISPLAY_FORMAT, index, description);			
			++index;
		}
		
		output += "\n";
		return output;
	}
}
