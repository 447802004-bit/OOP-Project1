package internship;

public abstract class SystemEntity implements Displayable {
    private String name;

    public SystemEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getSummary();
}
