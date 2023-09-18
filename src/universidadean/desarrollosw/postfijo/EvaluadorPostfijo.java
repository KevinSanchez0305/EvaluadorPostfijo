/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

/**
     * Esta clase representa una clase que evalúa expresiones en notación polaca o
     * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    Stack <String> expresion =  new Stack<> (); //"{[()]()}";

    /**
     * Permite saber si la expresión en la lista está balanceada
     * o no. Cada elemento de la lista es un elemento. DEBE OBlIGATORIAMENTE
     * USARSE EL ALGORITMO QUE ESTÁ EN EL ENUNCIADO.
     */
    static boolean estaBalanceada(List<String> expresion) {


        Stack<String> delimitadores = new Stack<>();
        String delimitadorDeCierre = "";
        for (int i = 0; i < expresion.size(); i++) {
            String caracter =  expresion.get(i);
            if (caracter.equals('{')  ||  caracter.equals('[') || caracter.equals('(')){
                delimitadores.push(caracter);
            }else if ( caracter.equals('}') || caracter.equals(']') || caracter.equals(')')){
                    delimitadorDeCierre = expresion.get(i);
                    if (delimitadores.isEmpty()){
                        return false;

                    }
                     // encuentra y elima el delimitador que se encuentra en el tope
                    String delimitadorAbierto = delimitadores.pop();
                    if (delimitadorAbierto.equals('{') && delimitadorDeCierre.equals('}') || delimitadorAbierto.equals('[') && delimitadorDeCierre.equals(']') || delimitadorAbierto.equals('(') && delimitadorDeCierre.equals(')') ){
                        return false;
                    }


            }


        }

        if (!delimitadores.isEmpty()){
            return false;
        }else {
            return true;
        }
        // TODO: Escriba el algoritmo del enunciado aquí

    }

    /**
     * Transforma la expresión, cambiando los símbolos de agrupación
     * de corchetes ([]) y llaves ({}) por paréntesis ()
     */
    static void reemplazarDelimitadores(List<String> expresion) {
        for (int i = 0; i < expresion.size(); i++) {
            String delimitador = expresion.get(i);
                delimitador = delimitador.replace("{", "(");
                delimitador = delimitador.replace("[", "(");
                delimitador = delimitador.replace("}", ")");
                delimitador = delimitador.replace("]", ")");
                expresion.set(i,delimitador);


        }

        // TODO: Escriba el algoritmo aquí


    }

    /**
     * Realiza la conversión de la notación infija a postfija
     * @return la expresión convertida a postfija
     * OJO: Debe usarse el algoritmo que está en el enunciado OBLIGATORIAMENTE
     */
    static List<String> convertirAPostfijo(List<String> expresion) {
        Stack<String> pila = new Stack<>();
        List<String> salida = new ArrayList<>();

        for (int i = 0; i < expresion.size(); i++) {
            String operador = expresion.get(i);
            if (operador.equals('+') || operador.equals('-') || operador.equals('*') || operador.equals('/') || operador.equals('%') ){
                pila.push(operador);
            }
            if (operador.equals('(')){
                continue;
            }
            if (operador.equals(')')){
                pila.pop();
                salida.add(operador);
            }
            salida.add(operador);
        }

        // TODO: Escriba el algoritmo aquí

        return salida;
    }

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();
        String termino = "";


        for (int i = 0; i < expresion.size(); i++) {
                termino = expresion.get(i);
                if (Character.isDigit(termino.charAt(0))){

                    pila.push(Integer.parseInt(termino));
                }
                if (termino.equals('+')){

                    int PrimerOperando = pila.pop();
                    int SegundoOperando= pila.pop();
                    int resultado = PrimerOperando + SegundoOperando;
                    pila.push(resultado);
                }
                if (termino.equals('-')){

                    int PrimerOperando = pila.pop();
                    int SegundoOperando= pila.pop();
                    int resultado = PrimerOperando - SegundoOperando;
                    pila.push(resultado);
                }
                if (termino.equals('*')){

                    int PrimerOperando = pila.pop();
                    int SegundoOperando= pila.pop();
                    int resultado = PrimerOperando * SegundoOperando;
                    pila.push(resultado);
                }
                if (termino.equals('/')){

                    int PrimerOperando = pila.pop();
                    int SegundoOperando= pila.pop();
                    int resultado = PrimerOperando / SegundoOperando;
                    pila.push(resultado);
                }
                if (termino.equals('%')){

                    int PrimerOperando = pila.pop();
                    int SegundoOperando= pila.pop();
                    int resultado = PrimerOperando % SegundoOperando;
                    pila.push(resultado);
                }



            }


        // TODO: Realiza la evaluación de la expresión en formato postfijo

        return pila.pop();
    }

}
