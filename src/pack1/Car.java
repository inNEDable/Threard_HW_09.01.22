package pack1;

import java.util.ArrayList;

public class Car {

    private ArrayList<Parts.Seat> seats = new ArrayList<>();
    private ArrayList<Parts.Tire> tires = new ArrayList<>();
    private Parts.Engine engine;
    private Parts.Frame frame;

    private Car() {
    }

    public static Car createCar (){
        Car car = new Car();
        Thread seatMaker = new Thread( () -> {
            for (int i = 1; i <= 4; i++) {
                try {
                    car.addSeat(new Parts.Seat());
                    Thread.sleep(3000);
                    System.out.println("Seat " + i + " has been created");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        Thread tireMaker = new Thread( () -> {
            for (int i = 1; i <= 4; i++) {
                try {
                    car.addTire(new Parts.Tire());
                    Thread.sleep(2000);
                    System.out.println("Tire " + i + " has been created");
                    if (i == 4){
                        car.addSeat(new Parts.Seat());
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        Thread engineAndFrameMaker = new Thread( () -> {
            try {
                car.addEngine(new Parts.Engine());
                Thread.sleep(7000);
                System.out.println("An Engine has been created");
                car.addFrame(new Parts.Frame());
                Thread.sleep(5000);
                System.out.println("A frame has been created");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        });

        seatMaker.start();
        tireMaker.start();
        engineAndFrameMaker.start();

        try {
            seatMaker.join();
            tireMaker.join();
            engineAndFrameMaker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return car;
    }

    private void addSeat(Parts.Seat currentSeat) throws Exception {
        if (this.seats.size() < 5) {
            seats.add(currentSeat);
        } else {
            throw new Exception("NoMoreSeatsNeeded", new Throwable("The car already has 5 seats"));
        }
    }

    private void addTire(Parts.Tire currentTire) throws Exception {
        if (this.tires.size() < 5) {
            tires.add(currentTire);
        } else {
            throw new Exception("NoMoreTiresNeeded", new Throwable("The car already has 4 tires"));
        }
    }

    private void addEngine(Parts.Engine engine) throws Exception {
        if (this.engine == null) {
            this.engine = engine;
        } else {
            throw new Exception("AlreadyHasAnEngine", new Throwable("The car already has an engine!"));
        }
    }
    private void addFrame (Parts.Frame frame) throws Exception {
        if (this.frame == null){
            this.frame = frame;
        } else {
            throw new Exception("AlreadyHasAFrame", new Throwable("The car already has an engine"));
        }
    }
    }
