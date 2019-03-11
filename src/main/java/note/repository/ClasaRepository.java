package note.repository;

import note.utils.ClasaException;
import note.model.Clasa;
import note.model.Elev;
import note.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClasaRepository implements Repository<Clasa> {
    private List<Clasa> clase;

    public ClasaRepository() {
        clase = new ArrayList<Clasa>();
    }



//    public void afiseazaClasa() {
//        for (Elev elev : clasa.keySet()) {
//            System.out.println(elev);
//            for (String materie : clasa.get(elev).keySet()) {
//                System.out.println(materie);
//                for (double nota : clasa.get(elev).get(materie))
//                    System.out.print(nota + " ");
//            }
//        }
//    }

    @Override
    public void add(Clasa elem) {
        this.clase.add(elem);
    }

    @Override
    public Clasa getById(int id) {
        for (Clasa clasa : clase) {
            if (clasa.getId() == id)
                return clasa;
        }
        return null;
    }

    @Override
    public List<Clasa> getAll() {
        return clase;
    }
}
