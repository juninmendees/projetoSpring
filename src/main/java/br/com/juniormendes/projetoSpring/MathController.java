package br.com.juniormendes.projetoSpring;

import br.com.juniormendes.projetoSpring.exceptions.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();
     @RequestMapping (value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)

    public Double sum (
            @PathVariable (value = "numberOne") String numberOne,
            @PathVariable (value = "numberTwo") String numberTwo
            ) throws  Exception{
         if(!isNumeric (numberOne) || !isNumeric (numberTwo)){
             throw  new UnsuportedMathOperationException("Valor deve ser inteiro");
         }

         return convertToDouble (numberOne) + convertToDouble (numberTwo);
     }

    private Double convertToDouble(String strNumber) {
         if (strNumber == null){
             throw new RuntimeException("Valor informado  é null");
         }

         String number = strNumber.replaceAll("," , ".");

         if (isNumeric(number)){
             return  Double.parseDouble(number);
         }
         return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null){
            throw new UnsuportedMathOperationException("Valor informado é null");
        }

        String number = strNumber.replaceAll("," , ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
