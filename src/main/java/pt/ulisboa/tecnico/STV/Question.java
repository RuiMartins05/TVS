package pt.ulisboa.tecnico.STV;

import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;

import java.util.Collections;
import java.util.List;

public class Question {
    public Question(String body, List<String> choices, int correctChoice, String topic, int weight) throws
            InvalidOperationException { /* .... */ }
    // Removes a topic
    public void remove(String topic) throws InvalidOperationException { /* .... */ }
    // Adds a new topic
    public void add(String topic) throws InvalidOperationException { /* .... */ }
    // Returns all topics of this question
    public List<String> getTopics() {
        return Collections.emptyList(); // For compilation purposes
    }
    // Computes the grade considering the weight of this question and the selected choice
    public float grade(int selectedChoice) {
        return 0.0f; // For compilation purposes
    }
    // Changes the weight of this question
    public void setWeight(int weight) throws InvalidOperationException { /* .... */ }
    // Returns the weight of this question
    public int getWeight() {
        return 1; // For compilation purposes
    }
    // Returns the choices of this question
    public List<String> getChoices() {
        return Collections.emptyList(); // For compilation purposes
    }
}