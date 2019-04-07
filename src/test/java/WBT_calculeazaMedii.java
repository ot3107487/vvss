import note.model.Elev;
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

import java.util.HashMap;

public class WBT_calculeazaMedii {
    private SuperService service;

    @Before
    public void init() {
        ElevRepository elevRepository = new ElevRepository();
        elevRepository.add(new Elev(1,"Bogdan",10));
        NotaRepository notaRepository = new NotaRepository(new NotaValidator());
        ClasaRepository clasaRepository = new ClasaRepository();
        service = new SuperService(elevRepository, notaRepository, clasaRepository);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    //invalid
    @Test
    public void TC1() {
        try {
            service.calculeazaMedii();
            assert false;
        } catch (ClasaException c) {
            assert (c.getMessage().equals(Constants.emptyRepository));
        }
    }

    //valid
    @Test
    public void TC2() {
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
    public void TC3() {
        try {

            service.addNota(1, "Romana", 10);
            service.addNota(1, "Istorie", 8);
            HashMap<Elev, Double> medii = service.calculeazaMedii();
            assert medii.size() == 1;
            assert medii.get(new Elev(1, "x", 2)) == 9.0;
        } catch (ClasaException c) {
            assert false;
        }
    }


}
