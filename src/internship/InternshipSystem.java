
package internship;

import java.util.ArrayList;
import java.util.Scanner;

public class InternshipSystem {

static Scanner input = new Scanner(System.in);
static ArrayList<Student> students = new ArrayList<>();
static ArrayList<TrainingCenter> centers = new ArrayList<>();

public static void main(String[] args) {

    addCenters();
    int choice = 0;

    while (choice != 8) {

        System.out.println("\n===== BSc Internship Matching System =====");
        System.out.println("1. Add New Student");
        System.out.println("2. Register Courses");
        System.out.println("3. Register Graduation Project");
        System.out.println("4. Find Matching Training Centers");
        System.out.println("5. Assign Internship");
        System.out.println("6. Search Student");
        System.out.println("7. Display All Students");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");

        try {
            choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:addNewStudent(); // Call method to add a new student
                break;
               
                case 2:registerCourses(); // Call method to register courses
                break;
                
                case 3:registerProject(); // Call method to register graduation project
                break;
               
                case 4:findMatchingCenters(); // Call method to find matching training centers
                break;
               
                case 5:ssignInternship(); // Call method to assign internship and manage seats
                break;
                   
                case 6:
                    searchStudent();
                    break;

                case 7:
                    displayStudents();
                    break;

                case 8:
                    System.out.println("Thank you for using the BSc Internship Matching System.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Please enter a number between 1 and 8.");
        }
    }
}


private static void ssignInternship() {
	// TODO Auto-generated method stub
	
}


// Add the five training centers
static void addCenters() {

    centers.add(new TrainingCenter(
        "Asir Software House", "Abha",
        "Software Development",
        new String[]{"Java Programming", "Data Structures",
            "Web Development", "Database Systems"}, 3));

    centers.add(new TrainingCenter(
        "Smart Vision AI Lab", "Riyadh",
        "Artificial Intelligence",
        new String[]{"Artificial Intelligence",
            "Machine Learning", "Python Programming"}, 2));

    centers.add(new TrainingCenter(
        "Secure Gate Cyber Center", "Jeddah",
        "Cybersecurity",
        new String[]{"Network Security", "Cryptography",
            "Operating Systems"}, 2));

    centers.add(new TrainingCenter(
        "DataWave Analytics", "Khamis Mushait",
        "Data Science",
        new String[]{"Database Systems", "Statistics",
            "Python Programming", "Machine Learning"}, 3));

    centers.add(new TrainingCenter(
        "NetLink Telecom", "Dammam",
        "Networking",
        new String[]{"Computer Networks", "Operating Systems",
            "Network Security"}, 2));
}

// Find a student using KKU ID
public static Student findStudent(String id) {

    for (Student s : students) {
        if (s.getKkuId().equals(id))
            return s;
    }
    return null;
}

	// Option 1: Add New Student
	private static void addNewStudent() {
	if (students.size() >= 10) {
	System.out.println("The system has reached the maximum number of students (10).");
	return;
	}
	System.out.print("Enter Name: ");
	String name = input.nextLine().trim();
	if (name.length() < 2) {
	System.out.println("Invalid Name. Must be at least 2 characters.");
	return;
	}

	System.out.print("Enter KKU ID: ");
	String id = input.nextLine().trim();
	if (!id.matches("^4\\d{8}$")) {
	System.out.println("Invalid KKU ID. Must contain 9 digits and start with 4.");
	return;
	}
	if (findStudent(id) != null) {
	System.out.println("Invalid KKU ID. ID already registered.");
	return;
	}

	System.out.print("Enter Major: ");
	String major = input.nextLine().trim();

	students.add(new Student(name, id, major));
	System.out.println("Student added successfully.");
	}

	// Option 2: Register Courses
	private static void registerCourses() {
	System.out.print("Enter KKU ID: ");
	String id = input.nextLine().trim();
	Student student = findStudent(id);
	if (student == null) {
	System.out.println("Student not found.");
	return;
	}

	System.out.print("Enter number of courses (1 to 5): ");
	try {
	int numCourses = Integer.parseInt(input.nextLine());
	if (numCourses < 1 || numCourses > 5) {
	System.out.println("Invalid number of courses. Must be between 1 and 5.");
	return;
	}

	for (int i = 1; i <= numCourses; i++) {
	System.out.print("Enter course " + i + " name: ");
	String courseName = input.nextLine().trim();

	if (student.hasCourse(courseName)) {
	System.out.println("Course already exists. Please enter a different course.");
	i--; 
	} else {
	student.addCourse(courseName);
	}
	}
	System.out.println("Courses registered successfully.");
	} catch (NumberFormatException e) {
	System.out.println("Invalid input format for number of courses.");
	}
	}

	// Option 3: Register Graduation Project
	private static void registerProject() {
	System.out.print("Enter KKU ID: ");
	String id = input.nextLine().trim();
	Student student = findStudent(id);
	if (student == null) {
	System.out.println("Student not found.");
	return;
	}

	System.out.print("Enter Project Title: ");
	String title = input.nextLine().trim();
	if (title.isEmpty()) {
	System.out.println("Project title cannot be empty.");
	return;
	}

	System.out.print("Enter Field: ");
	String field = input.nextLine().trim();

	student.setProject(title, field);
	System.out.println("Graduation project registered successfully.");
	}

	// Option 4: Find Matching Training Centers
	private static void findMatchingCenters() {
	System.out.print("Enter KKU ID: ");
	String id = input.nextLine().trim();
	Student student = findStudent(id);
	if (student == null) {
	System.out.println("Student not found.");
	return;
	}

	System.out.println("===== MATCHING TRAINING CENTERS =====");
	System.out.printf("%-25s | %-15s | %-25s | %-5s%n", "Center", "City", "Field", "Score");
	System.out.println("----------------------------------------------------------------------------------");

	for (TrainingCenter tc : centers) {
	int score = tc.calculateScore(student);
	System.out.printf("%-25s | %-15s | %-25s | %d%%%n", tc.getName(), tc.getCity(), tc.getField(), score);
	}
	}

	// Option 5: Assign Internship
	private static void assignInternship() {
	System.out.print("Enter KKU ID: ");
	String id = input.nextLine().trim();
	Student student = findStudent(id);
	if (student == null) {
	System.out.println("Student not found.");
	return;
	}

	if (student.hasInternship()) {
	System.out.println("This student is already assigned to a training center.");

	
	return;
	}

	TrainingCenter bestCenter = null;
	int maxScore = -1;
	for (TrainingCenter tc : centers) {
	int score = tc.calculateScore(student);
	if (score > maxScore) {
	maxScore = score;
	bestCenter = tc;
	}
	}

	if (bestCenter != null && bestCenter.hasSeats()) {
	bestCenter.takeSeat();
	student.setInternship(bestCenter);
	System.out.println("Internship assigned successfully to " + bestCenter.getName() + " with score " + maxScore + "%");
	} else {
	System.out.println("Assignment failed. No available seats in the best matching training center.");
	}
	
// Option 6: Search Student
static void searchStudent() {

    System.out.print("Enter KKU ID: ");
    String id = input.nextLine().trim();

    if (!id.matches("4[0-9]{8}")) {
        System.out.println("Invalid KKU ID. The ID must contain 9 digits and start with 4.");
        return;
    }

    Student s = findStudent(id);

    if (s == null) {
        System.out.println("Student not found.");
    } else {
        System.out.println("Student Found!");
        s.displayInfo();
    }
}

// Option 7: Display All Students
static void displayStudents() {

    int assigned = 0;

    System.out.println("\n===== ALL STUDENTS =====");
    System.out.printf("%-20s %-12s %-25s %s%n",
            "Name", "KKU ID", "Major", "Internship");
    for (Student s : students) {

        String status = "Not Assigned";

        if (s.hasInternship()) {
            status = "Assigned";
            assigned++;
        }

        System.out.printf("%-20s %-12s %-25s %s%n",
                s.getName(), s.getKkuId(),
                s.getMajor(), status);
    }

    System.out.println("Total Students: " + students.size());
    System.out.println("Assigned: " + assigned);
    System.out.println("Not Assigned: "
            + (students.size() - assigned));
}
	}
