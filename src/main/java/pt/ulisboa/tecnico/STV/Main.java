package pt.ulisboa.tecnico.STV;

import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new question
            Question question = new Question("What is the capital of France?",
                    Arrays.asList("Paris", "London", "Berlin", "Rome"),
                    0, "Geography", 5);

        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}