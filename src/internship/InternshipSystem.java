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
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    // Other team members will add these operations.
                    System.out.println("Option not connected yet.");
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