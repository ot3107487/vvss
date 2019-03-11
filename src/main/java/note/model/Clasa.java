package note.model;

public class Clasa {
    private int id;
    private String nume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Clasa(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }
}
