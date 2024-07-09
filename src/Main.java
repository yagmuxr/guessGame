import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static final int MAX_ATTEMPTS = 10;
    private static final int TIME_LIMIT = 60; // seconds
    private static int attempts = 0;
    private static int timeLeft = TIME_LIMIT;
    private static Timer timer;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int randomNumber = random.nextInt(100) + 1;

        startTimer();

        System.out.println("Guess the number between 1 and 100:");

        while (attempts < MAX_ATTEMPTS && timeLeft > 0) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                stopTimer();
                return;
            } else if (guess < randomNumber) {
                System.out.println("Too Low! Try again.");
            } else {
                System.out.println("Too High! Try again.");
            }

            System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
            System.out.println("Time left: " + timeLeft + " seconds.");
        }

        if (attempts >= MAX_ATTEMPTS || timeLeft <= 0) {
            System.out.println("You've used all your attempts or time is up! The correct number was: " + randomNumber);
        }

        stopTimer();
        scanner.close();
    }

    private static void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
