/* This class for creating Projects. */

import java.util.ArrayList;
import java.util.Calendar;

public class Project {

	private String projectName = "";
	private Calendar startDate;
	private boolean state;
	
	public Project (String pName, Calendar startDate, String state) {
		setProjectName(pName);
		setStartDate(startDate);
		setState(state);
	}
	
	// Checks if the project exist or not in given array list. Created for make easier exception handling.
	public static boolean isProjectExist(ArrayList<Project> projects, String p) {
		for(Project project: projects) {
			if(project.getProjectName().equals(p))
				return true;
		}
		return false;
	}
	
	public boolean isNotValidProject() {
		if (projectName.equals(""))
			return true;
		else return false;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		try {
			if (projectName.length() >= 3)
				this.projectName = projectName;
			else throw new Exception("Invalid project name.");
		} catch (Exception ex) {
			System.out.println("Invalid project name: " + projectName);
		}
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public String getState() {
		if (state)
			return "Open";
		else
			return "Close";
	}

	public void setState(String state) {
		try {
			if (state.equals("Open"))
				this.state = true;
			else if (state.equals("Close"))
				this.state = false;
			else throw new Exception("Invalid state.");
		} catch (Exception ex) {
			System.out.println("Invalid project state: " + state);
		}
	}
	
	public void close() {
		try {
			if (state)
				setState("Close");
			else throw new Exception("Already closed.");
		} catch (Exception ex) {
			System.out.println(getProjectName() + " was already closed.");
			setState("Close");
		}
	}

	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", startDate=" + Test.calendarToString(startDate) + ", state=" + state + "]";
	}
	
}
