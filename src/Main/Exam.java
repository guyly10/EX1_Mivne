package Main;

import Utils.Param;

public class Exam implements Comparable<Exam> {
	
	//Static counter for Exam ID
	public static Integer id = Param.FIRST_ID_EXAM;
	
	/**
	 * Private class members
	 */
	private Integer examID;
	private Integer examGrade;
	private Student student;
	
	/**
	 * Public constructor
	 * @param stu
	 */
	public Exam(Student student)
	{
		this.examID = Exam.generateId();
		this.student = student;
	}
	
	/**
	 * Generate ID
	 * @return
	 */
	public static Integer generateId()
	{
		return Exam.id++;
	}

    public void setGrade(int examGrade) {
        this.examGrade = examGrade;
    }

    public Student getStudent() {
        return student;
    }

    public Integer getExamGrade() {
        return examGrade;
    }

    @Override
	public String toString() {
		return "[Exam: " + this.examID + " Student: " + this.student + " Grade: " + this.examGrade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examID == null) ? 0 : examID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (examID == null) {
			if (other.examID != null)
				return false;
		} else if (!examID.equals(other.examID))
			return false;
		return true;
	}

    @Override
    public int compareTo(Exam o) {
        return o.getExamGrade().compareTo(this.examGrade);
    }
}
