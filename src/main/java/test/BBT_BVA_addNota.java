package test;

import note.model.Materie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ElevRepository;
import note.repository.NotaRepository;
import note.service.SuperService;
import note.utils.ClasaException;
import note.utils.Constants;
import note.validator.NotaValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BBT_BVA_addNota {
    private SuperService service;

    @Before
    public void init() {
        ElevRepository elevRepository = new ElevRepository();
        NotaRepository notaRepository = new NotaRepository(new NotaValidator());
        ClasaRepository clasaRepository = new ClasaRepository();
        service = new SuperService(elevRepository, notaRepository, clasaRepository);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    //invalid
    @Test
    public void TC2_BVA() {
        try {
            service.addNota(0, "Romana", 10);
            assert false;
        } catch (ClasaException c) {
            assert (c.getMessage().equals(Constants.invalidNrmatricol));
        }
    }

    // valid
    @Test
    public void TC4_BVA() {
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

    // invalid
    @Test
    public void TC6_BVA() throws ClasaException {
        try {
            service.addNota(1001, "Chimie", 5);
            assert false;
        } catch (ClasaException e) {
            assert e.getMessage().equals(Constants.invalidNrmatricol);
        }
    }

    // valid
    @Test
    public void TC14_BVA() throws ClasaException {
        try {
            service.addNota(22, "Desen", 10);
            boolean found = false;
            for (Nota nota : service.getAllNote()) {
                if (nota.getNota() == 10 && nota.getNrmatricol() == 22 && nota.getMaterie().equals(Materie.Desen))
                    found = true;
            }
            assert found == true;
        } catch (ClasaException e) {
            assert false;
        }
    }

}
