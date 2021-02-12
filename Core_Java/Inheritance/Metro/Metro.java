package Inheritance.Metro;

public class Metro {
    static Station[] stations;
    Metro(){

    }
}

interface Strategy{
    static int weekday = 7;
    static double weekend = 5.5;
}

class Ticket{
    String to, from, startTime;
    Ticket(){

    }

}

class Station{
    int id;
    String name;
    Station(){

    }
}

class Fare implements Strategy{
    int calculateFare(){
        return Strategy.weekday;

    }
}

