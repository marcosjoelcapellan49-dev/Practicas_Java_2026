import java.util.Scanner;
import java.text.DecimalFormat;

public class NominaFebrero2026 {

    static final int EMPLEADOS = 10;
    static int contador = 0;

    static String[][] datos = new String[EMPLEADOS][3];
    static double[][] nomina = new double[EMPLEADOS][9];

    static Scanner sc = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("RD$ #,##0.00");

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n====================================================");
            System.out.println("EMPRESA RD$ TECNOLOGIA Y SERVICIOS S.R.L.");
            System.out.println("VALORES EXPRESADOS EN RD$");
            System.out.println("NOMINA MES FEBRERO 2026");
            System.out.println("====================================================");
            System.out.println("1. Crear Nomina");
            System.out.println("2. Modificar Registro");
            System.out.println("3. Mostrar Nomina");
            System.out.println("4. Salir");
            System.out.print("Seleccione opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: crearNomina(); break;
                case 2: modificar(); break;
                case 3: mostrar(); break;
                case 4: System.out.println("Programa finalizado."); break;
                default: System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);
    }

    static void crearNomina() {

        if (contador >= EMPLEADOS) {
            System.out.println("No se pueden agregar más empleados.");
            return;
        }

        sc.nextLine();

        System.out.println("\nEmpleado #" + (contador + 1));

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Año ingreso: ");
        String anio = sc.nextLine();

        System.out.print("Salario mensual: ");
        double salario = sc.nextDouble();

        System.out.print("Descuento Prestamo (0 si no tiene): ");
        double prestamo = sc.nextDouble();

        String codigo = (contador + 1) + "" +
                nombre.charAt(0) +
                apellido.charAt(0) +
                anio;

        double afp = salario * 0.0287;
        double sfs = salario * 0.0304;
        double afpEmp = salario * 0.0710;
        double sfsEmp = salario * 0.0709;

        double salarioAnual = (salario - afp - sfs) * 12;
        double isrMensual = calcularISR(salarioAnual) / 12;

        double totalDesc = prestamo + afp + sfs + isrMensual;
        double salarioNeto = salario - totalDesc;

        // Guardar datos
        datos[contador][0] = codigo;
        datos[contador][1] = nombre;
        datos[contador][2] = apellido;

        nomina[contador][0] = salario;
        nomina[contador][1] = prestamo;
        nomina[contador][2] = sfs;
        nomina[contador][3] = sfsEmp;
        nomina[contador][4] = afp;
        nomina[contador][5] = afpEmp;
        nomina[contador][6] = isrMensual;
        nomina[contador][7] = totalDesc;
        nomina[contador][8] = salarioNeto;

        contador++;

        System.out.println("Empleado agregado correctamente.");
    }

    static double calcularISR(double salarioAnual) {

        if (salarioAnual <= 416220.00) {
            return 0;
        } else if (salarioAnual <= 624329.00) {
            return (salarioAnual - 416220.00) * 0.15;
        } else if (salarioAnual <= 867123.00) {
            return 31216.00 + (salarioAnual - 624329.00) * 0.20;
        } else {
            return 79776.00 + (salarioAnual - 867123.00) * 0.25;
        }
    }

    static void mostrar() {

        if (contador == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\n================================================================================================================================================================");
        System.out.println("                         EMPRESA RD$ TECNOLOGIA Y SERVICIOS S.R.L.");
        System.out.println("                             VALORES EXPRESADOS EN RD$");
        System.out.println("                             NOMINA MES FEBRERO 2026");
        System.out.println("================================================================================================================================================================");

        String linea = "+----------+--------------+--------------+--------------+--------------+--------------+--------------+--------------+--------------+--------------+--------------+--------------+";
        System.out.println(linea);

        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n",
                "CODIGO","NOMBRE","APELLIDO","SALARIO","PREST",
                "SFS","SFS-E","AFP","AFP-E","ISR","T.DES","NETO");

        System.out.println(linea);

        for (int i = 0; i < contador; i++) {

            System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n",
                    datos[i][0],
                    datos[i][1],
                    datos[i][2],
                    df.format(nomina[i][0]),
                    df.format(nomina[i][1]),
                    df.format(nomina[i][2]),
                    df.format(nomina[i][3]),
                    df.format(nomina[i][4]),
                    df.format(nomina[i][5]),
                    df.format(nomina[i][6]),
                    df.format(nomina[i][7]),
                    df.format(nomina[i][8]));

            System.out.println(linea);
        }
    }

    static void modificar() {

        if (contador == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        sc.nextLine();
        System.out.print("Digite el codigo del empleado: ");
        String cod = sc.nextLine();

        for (int i = 0; i < contador; i++) {

            if (datos[i][0].equals(cod)) {

                System.out.print("Nuevo salario: ");
                double salario = sc.nextDouble();

                double afp = salario * 0.0287;
                double sfs = salario * 0.0304;
                double afpEmp = salario * 0.0710;
                double sfsEmp = salario * 0.0709;

                double salarioAnual = (salario - afp - sfs) * 12;
                double isrMensual = calcularISR(salarioAnual) / 12;

                nomina[i][0] = salario;
                nomina[i][2] = sfs;
                nomina[i][3] = sfsEmp;
                nomina[i][4] = afp;
                nomina[i][5] = afpEmp;
                nomina[i][6] = isrMensual;

                nomina[i][7] = nomina[i][1] + afp + sfs + isrMensual;
                nomina[i][8] = salario - nomina[i][7];

                System.out.println("Registro actualizado correctamente.");
                return;
            }
        }

        System.out.println("Codigo no encontrado.");
    }
}
