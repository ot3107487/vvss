import note.controller.Controller;
import note.model.Elev;
import note.model.Materie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ElevRepository;
import note.repository.NotaRepository;
import note.service.SuperService;
import note.utils.ClasaException;
import note.validator.NotaValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;

public class IntT_Top_Down {
    private SuperService service;
    private Controller controller;

    @Before
    public void init() {
        ElevRepository elevRepository = new ElevRepository();
        elevRepository.add(new Elev(1, "Bogdan", 10));
        elevRepository.add(new Elev(2, "Bogdanel", 10));
        elevRepository.add(new Elev(3, "Bogdanoi", 10));
        elevRepository.add(new Elev(4, "Marian", 10));
        NotaRepository notaRepository = new NotaRepository(new NotaValidator());
        ClasaRepository clasaRepository = new ClasaRepository();
        service = new SuperService(elevRepository, notaRepository, clasaRepository);
        controller = new Controller(service);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void TC_F03() {
        try {

            service.addNota(1, "Romana", 4);
            service.addNota(1, "Istorie", 4);
            service.addNota(2, "Romana", 4);
            service.addNota(2, "Chimie", 4);
            service.addNota(2, "Istorie", 4);
            service.addNota(3, "Romana", 4);
            service.addNota(4, "Romana", 10);
            List<Elev> corigenti = this.service.getCorigentiSortati();
            assert corigenti.size() == 3;
            assert corigenti.get(0).getNrmatricol() == 2;
            assert corigenti.get(1).getNrmatricol() == 1;
            assert corigenti.get(2).getNrmatricol() == 3;
        } catch (ClasaException c) {
            assert false;
        }
    }

    @Test
    public void TC_F02() {
        try {

            service.addNota(1, "Romana", 10);
            HashMap<Elev, Double> medii = service.calculeazaMedii();
            assert medii.size() == 1;
            assert medii.get(new Elev(1, "x", 2)) == 10.0;
        } catch (ClasaException c) {
            assert false;
        }
    }

    @Test
    public void TC_F01() {
        try {
            service.addNota(999, "Fizica", 7);
            boolean found = false;
            for (Nota nota : service.getAllNote()) {
                if (nota.getNota() == 7 && nota.getNrmatricol() == 999 && nota.getMaterie().equals(Materie.Fizica))
                    found = true;
            }
            assert found == true;
        } catch (ClasaException e) {
            assert false;
        }
    }

    @Test
    public void TC_Int_F01() {
        try {
            //F01
            controller.addNota(1, "Romana", 4);
            controller.addNota(1, "Istorie", 4);
            controller.addNota(2, "Romana", 4);
            controller.addNota(2, "Chimie", 4);
            controller.addNota(2, "Istorie", 4);
            controller.addNota(3, "Romana", 4);
            controller.addNota(4, "Romana", 10);
            boolean found = false;
            for (Nota nota : service.getAllNote()) {
                if (nota.getNota() == 10 && nota.getNrmatricol() == 4 && nota.getMaterie().equals(Materie.Romana))
                    found = true;
            }
            assert found == true;
        } catch (ClasaException e) {
            assert false;
        }
    }

    @Test
    public void TC_Int_F01_F02() {
        try {
            controller.addNota(1, "Romana", 10);
            HashMap<Elev, Double> medii = controller.calculeazaMedii();
            assert medii.size() == 1;
            assert medii.get(new Elev(1, "x", 2)) == 10.0;

        } catch (ClasaException e) {
            assert false;
        }
    }

    @Test
    public void TC_Int() {
        try {
            //F01
            controller.addNota(1, "Romana", 4);
            controller.addNota(1, "Istorie", 4);
            controller.addNota(2, "Romana", 4);
            controller.addNota(2, "Chimie", 4);
            controller.addNota(2, "Istorie", 4);
            controller.addNota(3, "Romana", 4);
            controller.addNota(4, "Romana", 10);

            // F02
            HashMap<Elev, Double> medii = controller.calculeazaMedii();
            assert medii.size() == 4;
            assert medii.get(new Elev(4, "x", 2)) == 10.0;

            // F03
            List<Elev> corigenti = this.controller.getCorigenti();
            assert corigenti.size() == 3;
            assert corigenti.get(0).getNrmatricol() == 2;
            assert corigenti.get(1).getNrmatricol() == 1;
            assert corigenti.get(2).getNrmatricol() == 3;
        } catch (ClasaException e) {
            assert false;
        }
    }
}
