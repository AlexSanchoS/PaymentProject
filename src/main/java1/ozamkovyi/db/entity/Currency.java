package ozamkovyi.db.entity;

public class Currency extends Entity{
    private int id;
    private String name;
    private float course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCourse() {
        return course;
    }

    public void setCourse(float course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", course=" + course +
                '}';
    }
}
