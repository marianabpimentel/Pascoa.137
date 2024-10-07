package unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCalculadora {
    @Test
    public void testeSomarDoisNumeros(){

        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 12;


        double resultadoAtual = Calculadora.somarDoisNumeros(num1,num2);


        assertEquals(resultadoAtual,resultadoEsperado);
    }


   //Configura
   @ParameterizedTest
    @CsvSource(value = {
            "7,5,12.0",
            "56,44,100.0",
            "10,0,10.0",
            "15, -5, 10.0",
            "-8, -6, -14.0"
    }, delimiter = ',')
    public void testeSomarDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){

        //Configura - Os dados de entrada e o resultado esperado vem da lista

        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1),Integer.valueOf(txtNum2));


        assertEquals(Double.valueOf(resultadoEsperado),Double.valueOf(resultadoAtual));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaSomar.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeSomarDoisNumerosLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado){
        //Configura
        // Os dados de entrada e o resultado esperado vem da lista

        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1),Integer.valueOf(txtNum2));


        assertEquals(Double.valueOf(resultadoEsperado),Double.valueOf(resultadoAtual));
    }



    @Test
    public void testeSubstrairDoisNumeros(){
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 2;
        double resultadoAtual = Calculadora.subtrairDoisNumeros(num1,num2);
        assertEquals (resultadoAtual,resultadoEsperado);
    }
    @Test
    public void testeMultiplicarDoisNumeros(){
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 35;
        double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);
        assertEquals (resultadoAtual,resultadoEsperado);
    }

    @Test
    public void testeDividirDoisNumeros(){
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 1.4;
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);
        assertEquals (resultadoAtual, resultadoEsperado);

    }

    @Test
    public void testeDividirDoisNumerosInteiros(){
        int numA = 8;
        int numB = 0;
        String resultadoEsperado = "Não é possível dividir por zero";

        String resultadoAtual = Calculadora.dividirDoisNumerosInteiros(numA, numB);

        assertEquals(resultadoEsperado,resultadoAtual);
        System.out.println(numA + " / " + numB + " = " + resultadoAtual);
        System.out.println("O resultado esperado:" + resultadoEsperado);
    }


}
