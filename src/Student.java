public class Student {
    private String name;
    private Double grade;

    public String getName() {
        return name;
    }

    public Double getGrade() {
        return grade;
    }

    public Student(String name, Double grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
