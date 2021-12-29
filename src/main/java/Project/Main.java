package Project;


import java.util.*;

import static Project.Cinema.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema(new TimeTest(61, 3), new TimeTest(23, 19));
        System.out.println();
        Menu menu = new Menu(cinema);
        Scanner sc = new Scanner(System.in);
        while (true) {
            Menu.menuHeadMain();
            System.out.print("Введіть цифру:");
            int number = sc.nextInt();
            switch (number) {
                case 1: {
                    System.out.print("\n\n\n");
                    System.out.println("|---------------------|");
                    System.out.print(" >");
                    System.out.print("Введіть ім'я мультфільму:");
                    String name = sc.next();
                    System.out.print(" >");
                    System.out.println("Ведіть час мультфільму:");
                    System.out.print("   >");
                    System.out.print("Годин: ");
                    int hour = sc.nextInt();
                    System.out.print("   >");
                    System.out.print("Хвилин: ");
                    int min = sc.nextInt();
                    waiter();
                    menu.addMovie(new Movie(name, new TimeTest(min, hour)));
                    break;
                }
                case 2: {
                    AddSeanseForCompiler(cinema, sc);
                    break;
                }
                case 3: {
                    Menu.printAllFilmFromFewDays();
                    break;
                }
                case 4: {
                    Menu.printAllFilmFromLibrary();
                    break;
                }
                case 5: {
                    Menu.printInformationAboutFilmFromLibrary();
                    break;
                }
                case 6: {
                    menu.printInformationAboutFilmFromSeance();
                    break;
                }
                case 7: {
                    System.out.println("\n\n\n");
                    System.out.println("                                                            |------------------------------------------------------------------------|");
                    System.out.println("                                                            |           " + cinema.toString() + "           |");
                    System.out.println("                                                            |------------------------------------------------------------------------|");
                    System.out.println("\n\n\n");
                    break;
                }
                case 8: {
                    System.out.print("\n\n\n");
                    System.out.println("|---------------------|");
                    System.out.print(" >");
                    System.out.print("Введіть назву фільму : ");
                    String name = sc.next();
                    System.out.println("|---------------------|");
                    System.out.print("\n\n\n");
                    longWaiter();
                    cinema.removeMovie(name);
                    break;
                }
                case 9: {
                    System.out.print("\n\n\n");
                    System.out.println("|---------------------|");
                    System.out.print(" > Введіть день сеансу:");
                    String date = sc.next();
                    System.out.print(" >");
                    System.out.print("Введіть фільм: ");
                    String nameMovie = sc.next();
                    System.out.print("|---------------------|");
                    System.out.print("\n\n\n");
                    longWaiter();
                    cinema.removeSeance(date,nameMovie);
                    break;
                }
                case 10: {
                    while (true) {
                        System.out.print("\n\n\n");
                        System.out.println("|---------------------|");
                        System.out.print(" > Введіть ім'я фільму з бібліотеки:");
                        String name = sc.next();
                        System.out.print(" > Введіть день фільму з бібліотеки:");
                        String date = sc.next();
                        fasterWaiter();
                        System.out.println("Час початку:");
                        System.out.print("   >");
                        System.out.print("Години:");
                        int hour = sc.nextInt();
                        System.out.print("   >");
                        System.out.print("Хвилини:");
                        int min = sc.nextInt();
                        cinema.addCinemaInSeance(name, date, hour, min);
                    }

                }
                default:
                    throw new IllegalStateException("Unexpected value: " + number);
            }
        }
        /**
         * @param no input params
         * @exception WrongInputConsoleParametersException
         * @author Roma
         * @return null
         * @see java code convention
         **/
    }
}
