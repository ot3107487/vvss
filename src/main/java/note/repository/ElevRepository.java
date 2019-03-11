package note.repository;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import note.model.Elev;

public class ElevRepository implements Repository<Elev> {

    private List<Elev> elevi;

    public ElevRepository() {
        elevi = new ArrayList<Elev>();
    }


    public void readFromFile(String fisier) {
        try {
            FileInputStream fstream = new FileInputStream(fisier);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Elev elev = new Elev(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]));
                elevi.add(elev);
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

    @Override
    public void add(Elev elem) {
        this.elevi.add(elem);
    }

    @Override
    public Elev getById(int id) {
        for (Elev elev : elevi) {
            if (elev.getNrmatricol() == id)
                return elev;
        }
        return null;
    }

    @Override
    public List<Elev> getAll() {
        return elevi;
    }
}
