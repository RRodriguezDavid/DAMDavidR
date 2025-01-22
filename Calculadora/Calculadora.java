package Calculadora;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int operacion = 1 ;

        System.out.println("Buenos días, ¿como se llama?");
        String nombre = teclado.nextLine();
        System.out.printf("%s estas son las posibles opciones que le ofrece esta calculadora: \n", nombre);

        do {
            double resultadoSuma = 0;
            double resultadoMulti = 1;
            double resultadoResta= 0;
            double resultadoDivision= 0;
            double resultadoPotencia = 0;
            double resultadoPorcentaje = 0;
            double resultadoRaizCuadrada = 0;
            double resultadoLogaritmo = 0;
            int vuelta=1;

            System.out.print("" +
                    "0 - SALIR\n" +
                    "1 - SUMA\n" + //hecho
                    "2 - RESTA\n" + //hecho
                    "3 - MULTIPLICACION\n" + //hecho
                    "4 - DIVISION\n" + //hecho
                    "5 - PORCENTAJE\n" + //hecho
                    "6 - POTENCIA\n" +//hecho
                    "7 - RAIZ CUADRADA\n" +//hecho
                    "8 - LOGARITMO\n"); //hecho
            System.out.print("Escoga una opción de las nombradas anteriormente: ");
            operacion = teclado.nextInt();

            if (operacion == 0) {

                break; //parar

            }else if (operacion == 1) { //suma

                //comienza la suma
                System.out.print("Introduzca la cantidad de números que se quieren sumar: ");
                int totalnumsuma = teclado.nextInt();
                double numerosumas[]= new double[totalnumsuma];
                for (int i = 0; i < totalnumsuma; i++) {
                    System.out.println("Introduzca el número "+ (i+1)+ " que quiere sumar: ");
                    numerosumas[i] = teclado.nextInt();
                }
                for (double numero: numerosumas) {
                    resultadoSuma = resultadoSuma + numero;
                }
                System.out.println("El resultado de la suma es : "+resultadoSuma);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();
                //terminar o no programa, si no vuelve a empezar

            }else if (operacion == 2) { //resta

                //comienza la resta
                System.out.print("Introduzca la cantidad de números que se quieren restar: ");
                int totalnumresta = teclado.nextInt();
                double numeroresta[]= new double[totalnumresta];
                for (int i = 0; i < totalnumresta; i++) {
                    System.out.println("Introduzca el número "+ (i+1)+ " que quiere restar: ");
                    numeroresta[i] = teclado.nextInt();
                }
                for (double numero: numeroresta) {
                    if (vuelta==1){
                        resultadoResta = numero;
                        vuelta++;
                    }else if (vuelta>=2){
                        resultadoResta -= numero;
                    }
                }
                System.out.println("El resultado de la resta es : "+resultadoResta);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();
                //terminar o no programa, sino vuelve a empezar

            }else if (operacion == 3) { //multiplicación

                System.out.print("Introduzca la cantidad de números que se quieren multiplicar: ");
                int totalnummulti = teclado.nextInt();
                double numerosmulti[]= new double[totalnummulti];
                for (int i = 0; i < totalnummulti; i++) {
                    System.out.println("Introduzca el número "+ (i+1)+ " que quiere multiplicar: ");
                    numerosmulti[i] = teclado.nextInt();
                }
                for (double numero: numerosmulti) {
                    resultadoMulti = resultadoMulti * numero;
                }
                System.out.println("El resultado de la multiplicación es : "+resultadoMulti);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();
                //terminar o no programa, sino vuelve a empezar

            }else if (operacion == 4) { //división

                System.out.print("Introduzca la cantidad de números que se quieren dividir: ");
                int totalnumduvision = teclado.nextInt();
                double numerodivison[]= new double[totalnumduvision];

                for (int i=0; i<totalnumduvision; i++) {
                    System.out.println("Introduzca los números entre los que se quiere dividir: ");
                    numerodivison[i] = teclado.nextInt();
                }
                for (double numero: numerodivison) {
                    if (vuelta==1){
                        resultadoDivision = numero;
                    }else {
                        if (numero==0){
                            System.out.println("La división no tiene solución");
                            return;
                        }else {
                            resultadoDivision = resultadoDivision / numero;
                        }
                    }
                    vuelta++;
                }
                System.out.println("El resultado de la division es : "+resultadoDivision);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();

            }else if (operacion == 5) { //porcentaje

                //obtener los porcentajes
                System.out.print("introduzca el número del que se quiere obtener el porcentaje :");
                double numeroporcentaje = teclado.nextInt();
                System.out.print("introduzca el porcentaje que se le quiere aplicar al número "+numeroporcentaje+" : ");
                double porcentaje = teclado.nextInt();
                resultadoPorcentaje = (numeroporcentaje*porcentaje)/100.0;
                System.out.println("El resultado de la operación es : " + resultadoPorcentaje);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();
                //terminar o no programa, sino vuelve a empezar

            }else if (operacion == 6) { //potencia

                //obtenemos base y exponente de la potencia
                System.out.print("Digame la base de la potencia: ");
                double basepotencia = teclado.nextInt();
                System.out.print("Digame el exponente de la potencia: ");
                double exponente = teclado.nextInt();
                resultadoPotencia = Math.pow(basepotencia, exponente);
                System.out.println("El resultado de la potencia es : " + resultadoPotencia);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();
                //terminar o no programa, sino vuelve a empezar

            }else if (operacion == 7) { //Raiz cuadrada

                System.out.println("Introduzca el número del que quiere obtener su raiz cuadrada: ");
                double raizcuadrada = teclado.nextDouble();
                resultadoRaizCuadrada = Math.sqrt(raizcuadrada);
                System.out.println("EL resultado de la raiz cuadrada es = "+ resultadoRaizCuadrada);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();

            }else if (operacion == 8){ // Logaritmo

                System.out.println("Introduzca la base del logaritmo: ");
                int base = teclado.nextInt();
                System.out.println("Introduzca el número del logaritmo: ");
                double numero = teclado.nextInt();
                resultadoLogaritmo = Math.log10(numero)/Math.log10(base);
                System.out.println("El resultado del logaritmo es " +resultadoLogaritmo);
                System.out.printf("¿ %s Quieres seguir operando?, si no quieres pulsa ´0´: \n",nombre);
                operacion = teclado.nextInt();

            }else { //No corresponde
                System.out.println("El valor introducido no es correcto, introduzca un valor de los indicados.");
            }

            System.out.println("\n\n\n"); //metemos intros

        }while (operacion!=0);

        System.out.print("saliendo...");
    }

}
