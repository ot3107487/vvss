package note.model;

public class Nota {

    private int nrmatricol;
    private Materie materie;
    private int nota;

    public Nota(int nrmatricol, Materie materie, int nota) {
        this.nrmatricol = nrmatricol;
        this.materie = materie;
        this.nota = nota;
    }

    public int getNrmatricol() {
        return nrmatricol;
    }

    public void setNrmatricol(int nrmatricol) {
        this.nrmatricol = nrmatricol;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
