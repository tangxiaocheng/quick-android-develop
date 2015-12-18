package test.javas.make;

public class Student {

	private int age;

	private String father;

	private String mother;

	private boolean stop;

	public Student(int age, String father, String mother, boolean stop) {
		super();
		this.age = age;
		this.father = father;
		this.mother = mother;
		this.stop = stop;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public String toString() {
		return "Student ["+"age="+age+","+ "father="+father+","+ "mother="+mother+","+ "stop="+stop+"+]";
	}
}