package note.repository;

import note.model.Materie;
import note.model.Nota;
import note.utils.ClasaException;
import note.utils.Constants;
import note.validator.NotaValidator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotaRepository implements Repository<Nota> {
    private List<Nota> note;
    private NotaValidator validator;

    public NotaRepository(NotaValidator validator) {
        this.validator = validator;
        note = new ArrayList<Nota>();
    }


    @Override
    public void add(Nota nota) throws ClasaException {
        // TODO Auto-generated method stub
        if (!validator.isValid(nota))
            return;
        note.add(nota);
    }


    public void readNote(String fisier) {
        try {
            FileInputStream fstream = new FileInputStream(fisier);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Nota nota = new Nota(Integer.parseInt(values[0]), Materie.valueOf(values[1]), Integer.parseInt(values[2]));
                note.add(nota);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /***
     * no need
     * @param id
     * @return
     */
    @Override
    public Nota getById(int id) {
        return null;
    }

    @Override
    public List<Nota> getAll() {
        return note;
    }
}
