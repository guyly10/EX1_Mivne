package Main;

import Utils.Param;

public class Student {
	
	//General ID Counter
	public static Integer id = Param.FIRST_ID_STUDENT;
	//Private class members
	private int studentID;
	private int studentStrength;
	
	public Student(Integer strength)
	{
		this.studentID = Student.generateID();
		this.studentStrength = strength;
	}

    /**
	 * Generates a new User ID
	 * @return

	 */
	public static Integer generateID()
	{
		return Student.id++;
	}
	
	/**
	 * Getters & Setters
	 */
    public int getStudentID() {
        return studentID;
    }

    public int getStudentStrength() {
        return studentStrength;
    }

    @Override
	public String toString() {
		return "[id: " + this.getStudentID() + " strength: " + this.getStudentStrength() + "]";
	}
	
}
