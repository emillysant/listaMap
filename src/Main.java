import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        1) Criar um Map Simples:
        Map<String, Student> student = new HashMap<>();

        student.put("Luke", new Student("Luke Skywalker", 85.0));
        student.put("Leia", new Student("Leia Organa", 90.0));
        student.put("Han", new Student("Han Solo", 75.0));
        student.put("Darth", new Student("Darth Vader", 80.0));
        student.put("Yoda", new Student("Yoda", 95.0));

        student.forEach((chave, valor) -> {
            System.out.println("Nome: " + valor.getName() + ", nota: " + valor.getGrade());
        });

//        2) Acessar Valores no Map:

        System.out.println(getGradeByName(student, "Luke"));
        System.out.println("Alunos com notas maiores que 8: " );
        getStudentGradeThen(student, 80.0);

//        3) Remover Alunos do Map:
        System.out.println("Remover student: ");
        Iterator<Map.Entry<String, Student>> iterator = student.entrySet().iterator();
        System.out.println(removeStudentGradeLess(iterator, 80.0));

//        4) Classificar Alunos por Nota:

        Map<String, Student> sortedStudent = sortStudentsByGradeDescending(student);
        System.out.println(sortedStudent);

//        5) Agrupar Alunos por Faixa de Nota:
        Map<String, List<Student>> groupedAlunos = groupStudentsByGradeRange(student);
        System.out.println(groupedAlunos);
    }


    public static String getGradeByName(Map<String, Student> students, String name) {
        Student student = students.get(name);
        System.out.println("Debbug"+students.get(name));
        if (student != null) {
            return "Nota do aluno " + name + ": " + student.getName();
        } else {
            return "Aluno " + name + " não encontrado.";
        }
    }

    public static void getStudentGradeThen(Map<String, Student> students, Double grade){
        students.entrySet().stream()
                .filter(entry -> entry.getValue().getGrade() > grade)
                .forEach(entry -> System.out.println("Alunos: "+entry.getValue().getName()));
    }

    public static  List<Student> removeStudentGradeLess(Iterator<Map.Entry<String, Student>> iterator, Double grade){
        List<Student> removedStudents = new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<String, Student> entry = iterator.next();
            if (entry.getValue().getGrade() < grade){
                removedStudents.add(entry.getValue());
                iterator.remove();
            }
        }
        return removedStudents;
    }


    public static Map<String, Student> sortStudentsByGradeDescending(Map<String, Student> students) {
        List<Map.Entry<String, Student>> list = new ArrayList<>(students.entrySet());
        list.sort((entry1, entry2) -> Double.compare(entry2.getValue().getGrade(), entry1.getValue().getGrade()));
        Map<String, Student> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Student> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static Map<String, List<Student>> groupStudentsByGradeRange(Map<String, Student> students) {
        Map<String, List<Student>> groupedAlunos = new HashMap<>();
        for (Student student : students.values()) {
            String gradesRange = getGradesRange(student.getGrade());
            groupedAlunos.computeIfAbsent(gradesRange, k -> new ArrayList<>()).add(student);
        }
        return groupedAlunos;
    }

    public static String getGradesRange(double nota) {
        if (nota >= 90) {
            return "A";
        } else if (nota >= 80) {
            return "B";
        } else if (nota >= 70) {
            return "C";
        } else if (nota >= 60) {
            return "D";
        } else {
            return "F";
        }
    }


}