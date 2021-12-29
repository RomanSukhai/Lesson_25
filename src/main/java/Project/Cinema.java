package Project;

import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * @since java 1.8
 * @author Roma
 * @version 1.1
 * @class Cinema
 */



public class Cinema extends Schedule {

    //Param Cinema's
    public static TreeMap<Days,Schedule> scheduleTreeSet;
    private static TimeTest open;
    private static TimeTest close;

    //List library
    static ArrayList<Movie> moviesLibrary = new ArrayList();
    Scanner sc = new Scanner(System.in);




    //Loading methods between request
    public static void waiter() throws InterruptedException {
        System.out.print("Loading");
        for (int i = 0 ; i<5;i++){
            timingQuery(i,4);
        }
    }

    // Longer Loading methods between request
    public static void longWaiter() throws InterruptedException {
        System.out.print("Loading");
        for (int i = 0 ; i<8;i++){
            timingQuery(i,7);
        }
    }

    //Faster Loading methods between request
    public static void fasterWaiter() throws InterruptedException {
        System.out.print("Loading");
        for (int i = 0 ; i<2;i++){
            timingQuery(i,1);
        }
    }


    //Method which addSeance in which are Object Movie and Time add to TreeMap
    public void addSeance2(String date,int min,int hour,String name,int hourf,int minf)throws InterruptedException {
        date = date.toUpperCase(Locale.ROOT);
        if (getScheduleTreeSet().size() == 0) {
            boolean flag = getClose().getHour() > hour && getOpen().getHour()< hour ;
            if (flag) {
                scheduleTreeSet.put(Days.valueOf(date),new Schedule());
                String finalDate = date;
                scheduleTreeSet.entrySet().forEach(x-> {
                    if (x.getKey().name().equalsIgnoreCase(finalDate)) {
                        x.getValue().seances.forEach(e->{
                            boolean flag2 =hour<e.getStartTime().getHour() || hour>e.getStartTime().getHour() && hour+minf >=e.getEndTime().getHour() || hour+minf == e.getStartTime().getHour();
                            if (flag2){
                                x.getValue().addSeance(new Seance(new Movie(name, new TimeTest(minf, hourf)), new TimeTest(min + minf, hour + hourf)));
                            }else{
                                GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
                            }
                        });
                        x.getValue().addSeance(new Seance(new Movie(name, new TimeTest(minf, hourf)), new TimeTest(min + minf, hour + hourf)));
                    }
                });
            } else if (!flag) {
                GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
            }
        } else {
            boolean flag = getClose().getHour() > hour && getOpen().getHour()< hour;
            String finalDate1 = date;
            if (flag) {
                String finalDate2 = date;
                scheduleTreeSet.entrySet().forEach(x-> {
                    if (x.getKey().name().equalsIgnoreCase(finalDate1)) {
                        scheduleTreeSet.entrySet().forEach(s -> {
                            if (s.getKey().name().equalsIgnoreCase(finalDate1)) {
                                CreatedSeanse(min, x, s,name,hourf,minf,hour);
                            }
                        });
                    }else {
                        CreatedNewDateWithNewSchedule(sc, finalDate2, hour, min,name,hourf,minf);
                    }
                });
            } else {
                GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
            }
        }
    }
    public static void AddSeanseForCompiler(Cinema cinema, Scanner sc) throws InterruptedException {
        System.out.print("\n\n\n");
        System.out.println("|---------------------|");
        System.out.print("   >");
        System.out.print("Введіть день сеансу:");
        String date = sc.next();
        System.out.println("Час початку:");
        System.out.print("   >");
        System.out.print("Години:");
        int hour = sc.nextInt();
        System.out.print("   >");
        System.out.print("Хвилини:");
        int min = sc.nextInt();
        System.out.print(" >");
        System.out.print("Введіть ім'я мультфільму:");
        String name = sc.next();
        System.out.print(" >");
        System.out.println("Час початку:");
        System.out.print("   >");
        System.out.print("Години:");
        int hourf = sc.nextInt();
        System.out.print("   >");
        System.out.print("Хвилини:");
        int minf = sc.nextInt();
        cinema.addSeance2(date,min,hour,name,hourf,minf);
    }

    private void CreatedSeanse(int min, Map.Entry<Days, Schedule> x, Map.Entry<Days, Schedule> s,String name,int hourf,int minf,int hour) {
        x.getValue().seances.forEach(d-> {
            boolean flag2 =hour<d.getStartTime().getHour() || hour>d.getStartTime().getHour() && hour+minf >=d.getEndTime().getHour() || hour+minf == d.getStartTime().getHour();
            if (flag2){
                s.getValue().addSeance(new Seance(new Movie(name, new TimeTest(minf, hourf)), new TimeTest(min + minf, hour + hourf)));
            }else{
                GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");

            }
        });
    }

    //Simulation error)
    static void GetError(String x, String x1, String x2) {
        System.out.println(x);
        System.out.println(x1);
        System.out.println(x2);
    }
    //Method helper for less code in which add param in TreeMap
    private void CreatedNewDateWithNewSchedule(Scanner sc, String finalDate, int hour, int min,String name,int hourf,int minf) {
        try {
            waiter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getScheduleTreeSet().put(Days.valueOf(finalDate), new Schedule());
        getScheduleTreeSet().entrySet().forEach(d -> {
            if (d.getKey().name().equalsIgnoreCase(finalDate)) {
                d.getValue().seances.forEach(s-> {
                    boolean flag2 =hour<s.getStartTime().getHour() || hour>s.getStartTime().getHour() && hour+minf >=s.getEndTime().getHour() || hour+minf == s.getStartTime().getHour();
                    if (flag2){
                        d.getValue().addSeance(new Seance(new Movie(name, new TimeTest(minf, hourf)), new TimeTest(min + minf, hour + hourf)));
                    }else{
                        GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
                    }
                });
            }
        });
    }


    //Remove Object Movie from List
    public void removeMovie(String name) throws InterruptedException {
        Iterator iterator = moviesLibrary.iterator();
        while (iterator.hasNext()) {
            Movie movie = (Movie) iterator.next();
            if (movie.getTitle().equalsIgnoreCase(name)) {
                iterator.remove();
            } else {
                GetError("                                                            |---------------------------------------------------------------------------|", "                                                            |                            Такого фільму немає                            |", "                                                            |---------------------------------------------------------------------------|");
            }
        }
    }

    //Remove Object Movie from TreeMap
    public  void removeSeance(String date,String nameMovie) throws InterruptedException{
        while (true){
            for (Map.Entry<Days, Schedule> x : getScheduleTreeSet().entrySet()) {
                if (x.getKey().name().equalsIgnoreCase(date)) {
                    for (Seance d : x.getValue().seances) {
                        if (d.getMovie().getTitle().equalsIgnoreCase(nameMovie)) {
                            x.getValue().removeSeances(d);
                            return;
                        } else {
                            GetError("\n\n\n", "                                                            |---------------------------------------------------------------------------|", "                                                            |     Такої назви фільму у розкладі не має,будьласка введіть ще раз:        |");
                        }
                    }
                } else {
                    GetError("                                                            |---------------------------------------------------------------------------|", "                                                            |                     Такого дня немає,введіть ще раз                       |", "                                                            |---------------------------------------------------------------------------|");
                }
            }
        }
    }

    //Add Object Movie from List in Seance
    public void addCinemaInSeance(String name,String date,int hour,int min) throws InterruptedException {
        while (true){
            scheduleTreeSet.entrySet().forEach(x-> {
                if (x.getKey().name().equalsIgnoreCase(date)) {
                    boolean flag = getClose().getHour() > hour && getOpen().getHour()< hour ;
                    if (flag){
                        moviesLibrary.forEach(f-> {
                            if (f.getTitle().equalsIgnoreCase(name)) {
                                x.getValue().seances.add(new Seance(f,new TimeTest(min,hour)));
                            }
                        });
                    }else {
                        GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
                    }
                }else {
                    boolean flag = getClose().getHour() > hour && getOpen().getHour()< hour ;
                    if (flag){
                        scheduleTreeSet.put(Days.valueOf(date),new Schedule());
                        scheduleTreeSet.entrySet().forEach(f-> {
                            if (f.getKey().name().equalsIgnoreCase(date)) {
                                moviesLibrary.forEach(s->x.getValue().seances.add(new Seance(s,new TimeTest(min,hour))));
                            }
                        });
                    }else {
                        GetError("                                                            |----------------------------------------------------------------------|", "                                                            |>        Значення часу часу не правильне,будь ласка змініть його     <|", "                                                            |----------------------------------------------------------------------|\n\n\n\n\n\n\n\n");
                    }
                }
            });
        }
    }

    //Construction Cinema
    public Cinema(TimeTest open, TimeTest close) {
        this.open = open;
        this.close = close;
        this.scheduleTreeSet = new TreeMap<Days,Schedule>();
    }

    @Override
    public String toString() {
        return "Кінотеатр відкривається у "+open.getHour()+":"+open.getMin()+" зачиняється у"+close.getHour()+":"+close.getMin();
    }
    private static void timingQuery(int i, int cel) throws InterruptedException {
        if (i !=cel){
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.print(".");
        }else {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(".");
        }
    }

    public static TreeMap<Days, Schedule> getScheduleTreeSet() {
        return scheduleTreeSet;
    }

    public void setScheduleTreeSet(TreeMap<Days, Schedule> scheduleTreeSet) {
        this.scheduleTreeSet = scheduleTreeSet;
    }

    public TimeTest getOpen() {
        return open;
    }

    public void setOpen(TimeTest open) {
        this.open = open;
    }

    public TimeTest getClose() {
        return close;
    }

    public void setClose(TimeTest close) {
        this.close = close;
    }
}
