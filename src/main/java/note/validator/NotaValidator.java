package note.validator;

import note.model.Nota;
import note.utils.ClasaException;
import note.utils.Constants;

public class NotaValidator {
    public boolean isValid(Nota nota) throws ClasaException {
        // TODO Auto-generated method stub
        if (nota.getMaterie().toString().length() < 5 || nota.getMaterie().toString().length() > 20)
            throw new ClasaException(Constants.invalidMateria);
        if (nota.getNrmatricol() < Constants.minNrmatricol || nota.getNrmatricol() > Constants.maxNrmatricol)
            throw new ClasaException(Constants.invalidNrmatricol);
        if (nota.getNota() < Constants.minNota || nota.getNota() > Constants.maxNota)
            throw new ClasaException(Constants.invalidNota);
        if (nota.getNota() != (int) nota.getNota())
            throw new ClasaException(Constants.invalidNota);
        if (nota.getNrmatricol() != (int) nota.getNrmatricol())
            throw new ClasaException(Constants.invalidNrmatricol);
        return true;
    }
}
