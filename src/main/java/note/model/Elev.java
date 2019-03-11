package note.model;

public class Elev {
    private int nrmatricol;
    private String nume;
    private int idClasa;

    public Elev(int nrmatricol, String nume, int idClasa) {
        this.nrmatricol = nrmatricol;
        this.nume = nume;
        this.idClasa = idClasa;
    }

    public int getNrmatricol() {
        return nrmatricol;
    }

    public void setNrmatricol(int nrmatricol) {
        this.nrmatricol = nrmatricol;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(int idClasa) {
        this.idClasa = idClasa;
    }
}
