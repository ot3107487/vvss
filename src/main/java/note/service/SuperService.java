package note.service;

import note.model.Elev;
import note.model.Materie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ElevRepository;
import note.repository.NotaRepository;
import note.utils.ClasaException;
import note.utils.Constants;

import java.util.*;

/***
 * It's called SuperService because he's responsable with everything
 */
public class SuperService {
    private ElevRepository elevRepository;
    private NotaRepository noteRepository;
    private ClasaRepository clasaRepository;

    public SuperService(ElevRepository elevRepository, NotaRepository noteRepository, ClasaRepository clasaRepository) {
        this.elevRepository = elevRepository;
        this.noteRepository = noteRepository;
        this.clasaRepository = clasaRepository;
    }

    public HashMap<Elev, Double> calculeazaMedii() throws ClasaException {
        // TODO Auto-generated method stub
        HashMap<Elev, Double> medii = new HashMap<Elev, Double>();
        List<Nota> note = noteRepository.getAll();
        if (note.size() == 0)
            throw new ClasaException(Constants.emptyRepository);
        HashMap<Elev, HashMap<Materie, Nota>> situatie = new HashMap<Elev, HashMap<Materie, Nota>>();
        for (Nota nota : note) {
            Elev elev = elevRepository.getById(nota.getNrmatricol());
            if (situatie.containsKey(elev)) {
                situatie.get(elev).put(nota.getMaterie(), nota);
            } else {
                HashMap<Materie, Nota> tmp = new HashMap<Materie, Nota>();
                tmp.put(nota.getMaterie(), nota);
                situatie.put(elev, tmp);
            }
        }
        for (Elev elev : situatie.keySet()) {
            Collection<Nota> noteElev = situatie.get(elev).values();
            double medie = this.calculateMediaForElev(noteElev);
            medii.put(elev, medie);
        }
        return medii;
    }

    private double calculateMediaForElev(Collection<Nota> noteElev) {
        double suma = 0;
        for (Nota nota : noteElev)
            suma += nota.getNota();
        return suma / noteElev.size();
    }

    public HashMap<Elev, List<Nota>> getSituatieCorigenti() throws ClasaException {
        List<Nota> note = noteRepository.getAll();
        if (note.size() == 0)
            throw new ClasaException(Constants.emptyRepository);
        HashMap<Elev, List<Nota>> situatie = new HashMap<Elev, List<Nota>>();

        for (Nota nota : note) {
            if (nota.getNota() < 5) {
                Elev elev = elevRepository.getById(nota.getNrmatricol());
                if (situatie.containsKey(elev)) {
                    situatie.get(elev).add(nota);
                } else {
                    ArrayList<Nota> tmp = new ArrayList<Nota>();
                    tmp.add(nota);
                    situatie.put(elev, tmp);
                }
            }
        }
        return situatie;
    }

    public List<Elev> getCorigentiSortati() throws ClasaException {
        final HashMap<Elev, List<Nota>> situatia = this.getSituatieCorigenti();
        List<Elev> corigenti = new ArrayList<>(situatia.keySet());
        Comparator<Elev> comparator = (Elev e1, Elev e2) -> {
            int diff = situatia.get(e1).size() - situatia.get(e2).size();
            if (diff == 0) {
                return -e1.getNume().compareTo(e2.getNume());
            } else {
                return -diff;
            }
        };
        Collections.sort(corigenti, comparator);
        return corigenti;
    }

    public void addNota(int nrMatricol, String materie, int nota) throws ClasaException {
        Nota note = new Nota(nrMatricol, Materie.valueOf(materie), nota);
        this.noteRepository.add(note);
    }

    public List<Nota> getAllNote() {
        return this.noteRepository.getAll();
    }
}
