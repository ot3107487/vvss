package note.controller;

import note.model.Elev;
import note.service.SuperService;
import note.utils.ClasaException;

import java.util.HashMap;
import java.util.List;

public class Controller {
    private SuperService superService;

    public Controller(SuperService superService) {
        this.superService = superService;
    }

    public void addNota(int nrMatricol, String materie, int valoare) throws ClasaException {
        this.superService.addNota(nrMatricol, materie, valoare);
    }

    public HashMap<Elev, Double> calculeazaMedii() throws ClasaException {
        return this.superService.calculeazaMedii();

    }

    public List<Elev> getCorigenti() throws ClasaException {
        return this.superService.getCorigentiSortati();
    }
}
