package note.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elev elev = (Elev) o;
        return nrmatricol == elev.nrmatricol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrmatricol);
    }

}
