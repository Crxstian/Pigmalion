package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    ColorearHabitacion programa = new ColorearHabitacion();

        String planoDePrueba =
                        "##########\n" +
                        "#   #    #\n" +
                        "#   #    #\n" +
                        "## #### ##\n" +
                        "#        #\n" +
                        "#        #\n" +
                        "#  #######\n" +
                        "#  #  #  #\n" +
                        "#        #\n" +
                        "##########";
        System.out.println("Plano Original:");
        System.out.println(planoDePrueba);

        System.out.println("\nPlano Procesado:");
        programa.procesarYMostrar(planoDePrueba);


        String otroPlano =
                "##########\n" +
                "#   #    #\n" +
                "#   #    #\n" +
                "## #### ##\n" +
                "#        #\n" +
                "#        #\n" +
                "##########\n";
        System.out.println("Plano origianal de Otro Plano");
        System.out.println(otroPlano);

        System.out.println("\nPlano Procesado: ");
        programa.procesarYMostrar(otroPlano);
    }
}