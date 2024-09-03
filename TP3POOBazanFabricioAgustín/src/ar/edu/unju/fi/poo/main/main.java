package ar.edu.unju.fi.poo.main;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ar.edu.unju.fi.poo.archivo.Utilidades;
import ar.edu.unju.fi.poo.model.Administrativo;
import ar.edu.unju.fi.poo.model.Categoria;
import ar.edu.unju.fi.poo.model.Empleado;
import ar.edu.unju.fi.poo.model.Profesional;
import ar.edu.unju.fi.poo.model.Titulo;


public class main {
    public static void main(String[] args) {
        String rutaTitulo = "adicional_profesional.txt";
        String rutaCategorias = "categorias_administrativo.txt";
        String rutaValoresFamiliares = "valores_familiares.txt";
        
    	Scanner scanner = new Scanner(System.in);
        List<Empleado> empleados = new ArrayList<>();

        Utilidades.guardarEnArchivo(rutaTitulo, "80000");
        Utilidades.guardarEnArchivo(rutaCategorias, "1: 10000\n2: 14000\n3: 18000\n4: 22000\n5: 26000\n6: 30000\n7: 34000\n8: 38000\n9: 42000\n10: 46000\n10: 50000\n11: 54000\n12: 58000\n13: 62000\n14: 64000\n15: 68000\n16: 72000\n17: 76000\n18: 80000\n19: 84000\n20: 88000");
        Utilidades.guardarEnArchivo(rutaValoresFamiliares, "pago_por_antiguedad: 7000\npago_por_hijo: 8000");
        
        Titulo tituloProfesional = Utilidades.leerTituloProfesional(rutaTitulo);
        Map<Integer, Categoria> categoriasAdministrativo = Utilidades.leerCategoriasAdministrativo(rutaCategorias);
        Map<String, Double> valoresFamiliares = Utilidades.leerValoresFamiliares(rutaValoresFamiliares);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Mostrar empleados con antigüedad mayor a X años");
            System.out.println("3. Mostrar empleados cuya edad sea mayor o igual a X");
            System.out.println("4. Calcular importe neto acumulado para empleados mayores o iguales a X años");
            System.out.println("5. Incrementar salario básico en un 10% a empleados con antigüedad <= 2 años");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarEmpleado(scanner, empleados, tituloProfesional, categoriasAdministrativo);
                    break;
                case 2:
                    mostrarEmpleadosAntiguedadMayor(scanner, empleados, valoresFamiliares);
                    break;
                case 3:
                    mostrarEmpleadosEdadMayor(scanner, empleados);
                    break;
                case 4:
                    calcularImporteNetoAcumuladoEdad(scanner, empleados);
                    break;
                case 5:
                    incrementarSalarioBasico(empleados);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }
    
    // Opción 1: Agregar empleado
    public static void agregarEmpleado(Scanner scanner, List<Empleado> empleados, Titulo tituloProfesional, Map<Integer, Categoria> categoriasAdministrativo) {
        int tipo = 0;
        do {
            try {
                System.out.print("Tipo de empleado (1- Profesional, 2- Administrativo): ");
                tipo = scanner.nextInt();
                if (tipo != 1 && tipo != 2) {
                    System.out.println("Debe ingresar 1 para Profesional o 2 para Administrativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número (1 o 2).");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        } while (tipo != 1 && tipo != 2);

        int id = 0;
        boolean idValido = false;
        while (!idValido) {
            try {
            	do {
            		System.out.print("ID: ");
                    id = scanner.nextInt();
            	}while(id<=0);
                idValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para el ID.");
                scanner.next(); 
            }
        }

        System.out.print("Legajo: ");
        String legajo = scanner.next();

        System.out.print("Nombre: ");
        String nombre = scanner.next();

        int cantidadHijos = 0;
        boolean cantidadHijosValida = false;
        while (!cantidadHijosValida) {
            try {
            	do {
                    System.out.print("Cantidad de hijos: ");
                    cantidadHijos = scanner.nextInt();	
            	}while(cantidadHijos<=0);
                cantidadHijosValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para la cantidad de hijos.");
                scanner.next(); 
            }
        }

        LocalDate fechaNacimiento = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                fechaNacimiento = LocalDate.parse(scanner.next());
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar la fecha en el formato correcto (YYYY-MM-DD).");
            }
        }

        int antiguedad = 0;
        boolean antiguedadValida = false;
        while (!antiguedadValida) {
            try {
            	do {
                    System.out.print("Antigüedad (años): ");
                    antiguedad = scanner.nextInt();
            	}while(antiguedad<0);
                antiguedadValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para la antigüedad.");
                scanner.next(); 
            }
        }

        if (tipo == 1) {
            empleados.add(new Profesional(id, legajo, nombre, cantidadHijos, fechaNacimiento, antiguedad, tituloProfesional));
        } else if (tipo == 2) {
            int categoria_aux = 0;
            boolean categoriaValida = false;
            while (!categoriaValida) {
                try {
                    System.out.print("Categoría (1-20): ");
                    categoria_aux = scanner.nextInt();
                    if (categoria_aux < 1 || categoria_aux > 20) {
                        System.out.println("La categoría debe estar entre 1 y 20.");
                    } else {
                        categoriaValida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Debe ingresar un número entero para la categoría.");
                    scanner.next(); // Limpiar la entrada incorrecta
                }
            }
        }


     }
    

    // Opción 2: Mostrar empleados con antigüedad mayor a X años
    public static void mostrarEmpleadosAntiguedadMayor(Scanner scanner, List<Empleado> empleados,  Map<String, Double> valoresFamiliares) {
        System.out.print("Ingrese el valor de antigüedad (años): ");
        int antiguedad = scanner.nextInt();
        double totalRemunerativos = 0, totalSalario = 0, totalDescuentos = 0, totalNeto = 0;

        for (Empleado empleado : empleados) {
            if (empleado.getAntiguedad() > antiguedad) {
                System.out.println(empleado);
                double remunerativos = empleado.getRemunerativosBonificables();
                double salarioFamiliar = empleado.getSalarioFamiliar();
                double descuentos = empleado.getDescuentos();
                double sueldoNeto = empleado.calcularSueldoNeto();

                totalRemunerativos += remunerativos;
                totalSalario += salarioFamiliar;
                totalDescuentos += descuentos;
                totalNeto += sueldoNeto;

                System.out.printf("Remunerativos: %.2f, Salario Familiar: %.2f, Descuentos: %.2f, Sueldo Neto: %.2f\n",
                        remunerativos, salarioFamiliar, descuentos, sueldoNeto);
            }
        }
        System.out.printf("\nTotal Remunerativos: %.2f, Total Salario Familiar: %.2f, Total Descuentos: %.2f, Total Neto: %.2f\n",
                totalRemunerativos, totalSalario, totalDescuentos, totalNeto);
    }

    // Opción 3: Mostrar empleados cuya edad sea mayor o igual a X
    public static void mostrarEmpleadosEdadMayor(Scanner scanner, List<Empleado> empleados) {
        System.out.print("Ingrese el valor de edad: ");
        int edad = scanner.nextInt();
        for (Empleado empleado : empleados) {
            if (empleado.calcularEdad() >= edad) {
                System.out.println(empleado);
            }
        }
    }

    // Opción 4: Calcular importe neto acumulado para empleados mayores o iguales a X años
    public static void calcularImporteNetoAcumuladoEdad(Scanner scanner, List<Empleado> empleados) {
        System.out.print("Ingrese el valor de edad: ");
        int edad = scanner.nextInt();
        double totalNeto = 0;
        for (Empleado empleado : empleados) {
            if (empleado.calcularEdad() >= edad) {
                totalNeto += empleado.calcularSueldoNeto();
            }
        }
        System.out.printf("Importe neto acumulado para empleados con edad >= %d años: %.2f\n", edad, totalNeto);
    }

    // Opción 5: Incrementar salario básico en un 10% a empleados con antigüedad <= 2 años
    public static void incrementarSalarioBasico(List<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            if (empleado.getAntiguedad() <= 2) {
                empleado.setSueldoBasico(empleado.getSueldoBasico() * 1.1);
                System.out.printf("Nuevo salario básico para %s: %.2f\n", empleado.getNombre(), empleado.getSueldoBasico());
            }
        }
    }
    
}