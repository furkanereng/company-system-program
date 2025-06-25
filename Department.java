/* This class for creating Department. */

public class Department {

	private int departmentId;
	private String departmentName = "";
	
	public Department(int departmentId, String departmentName) {
		setDepartmentId(departmentId);
		setDepartmentName(departmentName);
	}
	
	public boolean isNotValidDepartment() {
		if (departmentId == 0 || departmentName.equals(""))
			return true;
		else return false;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		try {
			if (departmentId > 0)
				this.departmentId = departmentId;
			else throw new Exception("Invalid department id.");
		} catch (Exception ex) {
			System.out.println("Invalid id: " + departmentId);
		}
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		try {
			if (departmentName.length() >= 3)
				this.departmentName = departmentName;
			else throw new Exception("Invalid department name.");
		} catch (Exception ex) {
			System.out.println("Invalid department name: " + departmentName);
		}
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}
	
}
