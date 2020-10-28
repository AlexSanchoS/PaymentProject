package ozamkovyi.db.entity;

/**
 * Currency entity.
 *
 * @author O.Zamkovyi
 *
 */

public class Currency extends Entity{
    protected int id;
    protected String name;
    protected float course;

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

}
