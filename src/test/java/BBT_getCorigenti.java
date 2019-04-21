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
import java.util.List;

public class BBT_getCorigenti {
    private SuperService service;

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
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    //invalid
    @Test
    public void TC1() {
        try {
            service.getCorigentiSortati();
            assert false;
        } catch (ClasaException c) {
            assert (c.getMessage().equals(Constants.emptyRepository));
        }
    }

    //valid
    @Test
    public void TC2() {
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
}
