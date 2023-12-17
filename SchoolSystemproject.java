// Importing Packages
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
// Creating public class
public class SchoolSystemproject {
	// Defing the class as Student
    public static class Student {
    	// Defing Attributes
        String firstName;
        String Initial;
        int reg_No;
        int grades;
        int pursuing_Year;
        
     // Constructing Constructors
        public Student() {
            firstName = "";
            Initial = "";
            reg_No = 0;
            grades = 0;
            pursuing_Year = 0;
        }
        
     // Constructors Constructing with all Parameters
        public Student(String firstName, String Initial, int reg_No, int grades, int pursuing_Year) {
            this.firstName = firstName;
            this.Initial = Initial;
            this.reg_No = reg_No;
            this.grades = grades;
            this.pursuing_Year = pursuing_Year;
        }
        
     // Constructors with assigning values and Default values
        public Student(String firstName, String Initial, int reg_No) {
            this.firstName = firstName;
            this.Initial = Initial;
            this.reg_No = reg_No;
            grades = 0;
            pursuing_Year = 0;
        }

     // Constructing the methods
        public void printFullName() {
            System.out.println(firstName + " " + Initial);
        }
        
     // Checking the grade value of the student 60% above or not
        public boolean isApproved() {
            return grades >= 60;
        }

      //Method if student is approve increase the year
        public void changeYearIfApproved() {
            if (isApproved()) {
            	pursuing_Year++;
                System.out.println("Congratulations! You have been promoted to the next Year.");
            }
        }
    }

	// Defining class Course
    public static class Course {
        String Course_Name;
        String Professor_Name;
        int pursuing_Year;
        ArrayList<Student> students;

      //Constructing Constructors
        public Course(String Course_Name, String Professor_Name, int pursuing_Year) {
            this.Course_Name = Course_Name;
            this.Professor_Name = Professor_Name;
            this.pursuing_Year = pursuing_Year;
         // Initializing Arraylist to the store students
            students = new ArrayList<>();
        }

     // Method to enroll a student to the course
        public void enroll(Student student) {
            students.add(student);
        }

    	// Method to unenroll a student to the course
        public void unenroll(Student student) {
            students.remove(student);
        }

     // Method to count the number of students in the course
        public int countStudents() {
            return students.size();
        }

    	//finding the best grade among enrolled students
        public int bestGrade() {
            int best = 0;
            for (Student student : students) {
                if (student.grades > best) {
                    best = student.grades;
                }
            }
            return best;
        }

     // calculating the average grade of students in the course
        public double averageGrade() {
            double sum = 0;
            for (Student student : students) {
                sum += student.grades;
            }
            return sum / countStudents();
        }

     // Display the ranking of students based on their grades
     // Method to sort and print a ranking of students based on their grades
        public void ranking() {
            Collections.sort(students, Comparator.comparingInt(student -> -student.grades));
            System.out.println("Ranking of " + Course_Name + ":");
            for (int i = 0; i < countStudents(); i++) {
                Student student = students.get(i);
                System.out.println((i + 1) + ". " + student.firstName + " " + student.Initial + " - " + student.grades);
            }
        }

     // comparing each student's grade
        public void compareAverage() {
        	// Method to compare each student's grade to the course average
            double average = averageGrade();
            System.out.println("Comparison of " + Course_Name + " average:");
            for (Student student : students) {
                System.out.print(student.firstName + " " + student.Initial + " - ");
                if (student.grades > average) {
                    System.out.println("Above average");
                } else if (student.grades < average) {
                    System.out.println("Below average");
                } else {
                    System.out.println("Equal to average");
                }
            }
        }
    }

    public static void main(String[] args) {
    	// Scanner object for new user input
        Scanner input = new Scanner(System.in);

     // Getting course details from the user
        System.out.println("Enter course name:");
        String Course_Name = input.nextLine();

        System.out.println("Enter professor name:");
        String Professor_Name = input.nextLine();

        System.out.println("Enter course year:");
        int pursuing_Year = input.nextInt();
        input.nextLine();

     // Creating a Course object with user-input details
        Course c1 = new Course(Course_Name, Professor_Name, pursuing_Year);

     // Enrolling students to the course based on user input
        System.out.println("How many students do you want to enroll?");
        int numStudents = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student " + (i + 1) + " details (firstName Initial reg_No grades pursuing_Year):");
            String[] studentDetails = input.nextLine().split(" ");

            if (studentDetails.length != 5) {
                System.out.println("Invalid input. Please enter all details for the student.");
                continue;
            }

            try {
            	// Creating a Student object with input details and enrolling them to the course
                Student student = new Student(studentDetails[0], studentDetails[1],
                        Integer.parseInt(studentDetails[2]),
                        Integer.parseInt(studentDetails[3]),
                        Integer.parseInt(studentDetails[4]));

                c1.enroll(student);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numeric values for reg_No, grades, and pursuing_Year.");
            }
        }

        input.close();

     // Displaying various details and performing operations in the course
        System.out.println("Number of students in " + c1.Course_Name + ": " + c1.countStudents());
        System.out.println("Best grade in " + c1.Course_Name + ": " + c1.bestGrade());
        System.out.println("Average grade in " + c1.Course_Name + ": " + c1.averageGrade());
        c1.ranking();
        c1.compareAverage();
    }
}

