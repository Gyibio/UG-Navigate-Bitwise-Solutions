package model;

public class Node {
    private int id;
    private String name;
    private int accessibilityRating; // 1-5 scale

    public Node(int id, String name) {
        this(id, name, 3); // default rating
    }

    public Node(int id, String name, int accessibilityRating) {
        this.id = id;
        this.name = name;
        this.accessibilityRating = accessibilityRating;
    }

    public int getId() {
        return id;
    }
    public int getAccessibilityRating() {
        return accessibilityRating;
    }
    public void setAccessibilityRating(int rating) {
        this.accessibilityRating = rating;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
