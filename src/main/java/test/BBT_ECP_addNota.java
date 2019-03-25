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

public class BBT_ECP_addNota {
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
    public void TC3_BVA() {
        try {
            service.addNota(-1, "Mate", 8);
            assert false;
        } catch (ClasaException c) {
            assert (c.getMessage().equals(Constants.invalidNrmatricol));
        }
    }

    // valid
    @Test
    public void TC1_BVA() {
        try {
            service.addNota(1, "Romana", 10);
            boolean found = false;
            for (Nota nota : service.getAllNote()) {
                if (nota.getNota() == 10 && nota.getNrmatricol() == 1 && nota.getMaterie().equals(Materie.Romana))
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
            service.addNota(40, "Mate", 5);
            assert false;
        } catch (ClasaException e) {
            assert e.getMessage().equals(Constants.invalidMateria);
        }
    }

    // invalid
    @Test
    public void TC10_BVA() throws ClasaException {
        try {
            service.addNota(90, "Fizica", 11);
            assert false;
        } catch (ClasaException e) {
            assert e.getMessage().equals(Constants.invalidNota);
        }

    }

}
