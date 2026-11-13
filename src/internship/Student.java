package internship;

import java.util.ArrayList;

public class Student extends SystemEntity {
    private String kkuId;
    private String major;
    private ArrayList<String> courses;
    private String projectTitle;
    private String projectField;
    private TrainingCenter internship;

    public Student(String name, String kkuId, String major) {
        super(name);
        this.kkuId = kkuId;
        this.major = major;
        this.courses = new ArrayList<>();
        this.projectTitle = null;
        this.projectField = null;
        this.internship = null;
    }

    public void addCourse(String course) {
        if (!hasCourse(course)) {
            courses.add(course);
        }
    }

    public boolean hasCourse(String course) {
        for (String c : courses) {
            if (c.equalsIgnoreCase(course.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCourses() {
        return !courses.isEmpty();
    }

    public void setProject(String title, String field) {
        this.projectTitle = title;
        this.projectField = field;
    }

    public boolean hasProject() {
        return projectTitle != null && !projectTitle.isEmpty()
            && projectField != null && !projectField.isEmpty();
    }

    public void setInternship(TrainingCenter center) {
        this.internship = center;
    }

    public boolean hasInternship() {
        return internship != null;
    }

    // Getters
    public String getKkuId() {
        return kkuId;
    }

    public String getMajor() {
        return major;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectField() {
        return projectField;
    }

    public TrainingCenter getInternship() {
        return internship;
    }

    @Override
    public String getSummary() {
        return getName() + " (" + kkuId + ") - " + major;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("KKU ID: " + kkuId);
        System.out.println("Major: " + major);
        System.out.println("Courses: " + (courses.isEmpty() ? "None" : String.join(", ", courses)));
        System.out.println("Project: " + (hasProject() ? projectTitle + " (" + projectField + ")" : "Not registered"));
        System.out.println("Internship: " + (hasInternship() ? internship.getName() + " - " + internship.getCity() : "Not assigned"));
    }
}