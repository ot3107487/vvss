package note.main;

import note.controller.Controller;
import note.model.Elev;
import note.repository.ClasaRepository;
import note.repository.ElevRepository;
import note.repository.NotaRepository;
import note.service.SuperService;
import note.utils.ClasaException;
import note.validator.NotaValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

//functionalitati
//F01.	 adaugarea unei note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat şi alfabetic dupa nume.


public class StartApp {

    /**
     * @param args
     * @throws ClasaException
     */
    public static void main(String[] args) throws ClasaException {
        // TODO Auto-generated method stub
        ClasaRepository clasaRepository = new ClasaRepository();
        ElevRepository elevRepository = new ElevRepository();
        elevRepository.readFromFile(args[0]);
        NotaValidator validator = new NotaValidator();
        NotaRepository notaRepository = new NotaRepository(validator);
        notaRepository.readNote(args[1]);
        SuperService superService = new SuperService(elevRepository, notaRepository, clasaRepository);
        Controller ctrl = new Controller(superService);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Iesire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int option = Integer.parseInt(br.readLine());
                switch (option) {
                    case 1:
                        System.out.println("Nu s-a implementat");
                        break;
                    case 2:
                        HashMap<Elev, Double> medii = ctrl.calculeazaMedii();
                        for (Elev elev : medii.keySet())
                            System.out.println(elev + " " + medii.get(elev));
                        break;
                    case 3:
                        List<Elev> corigenti = ctrl.getCorigenti();
                        for (Elev corigent : corigenti)
                            System.out.println(corigent);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Introduceti o optiune valida!");
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
