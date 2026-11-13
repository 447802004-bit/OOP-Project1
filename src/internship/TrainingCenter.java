package internship;

public class TrainingCenter extends SystemEntity {
    private String city;
    private String field;
    private String[] relatedCourses;
    private int seats;

    public TrainingCenter(String name, String city, String field, String[] relatedCourses, int seats) {
        super(name);
        this.city = city;
        this.field = field;
        this.relatedCourses = relatedCourses;
        this.seats = seats;
    }

    public int calculateScore(Student student) {
        int score = 0;

        if (student.hasProject() && student.getProjectField().equalsIgnoreCase(this.field)) {
            score += 60;
        }

        int coursePoints = 0;
        if (relatedCourses != null) {
            for (String related : relatedCourses) {
                if (student.hasCourse(related)) {
                    coursePoints += 20;
                }
            }
        }
        if (coursePoints > 40) {
            coursePoints = 40;
        }

        return score + coursePoints;
    }

    public boolean hasSeats() {
        return seats > 0;
    }

    public void takeSeat() {
        if (seats > 0) {
            seats--;
        }
    }

    public String getCity() {
        return city;
    }

    public String getField() {
        return field;
    }

    public String[] getRelatedCourses() {
        return relatedCourses;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String getSummary() {
        return getName() + " | " + city + " | " + field + " (Seats: " + seats + ")";
    }

    @Override
    public void displayInfo() {
        System.out.println(getSummary());
    }
}