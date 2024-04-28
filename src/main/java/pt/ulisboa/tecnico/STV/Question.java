package pt.ulisboa.tecnico.STV;

import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String body;
    private List<String> choices;
    private int correctChoice;
    private List<String> topics;
    private int weight;

    public Question(String body, List<String> choices, int correctChoice, String topic, int weight) throws
            InvalidOperationException {
        !body.isEmpty() && 2 <= choices.size() <= 8 && 0 <= correctChoice <= (choices.size() - 1)
                && 1 <= topics.size() <= 5 && 1 <= weight <= 15

        this.body = body;
        this.choices = new ArrayList<>(choices);
        this.correctChoice = correctChoice;
        this.topics = new ArrayList<>();
        topics.add(topic);
        this.weight = weight;
    }
    // Removes a topic
    public void remove(String topic) throws InvalidOperationException {
        if (!topics.contains(topic))
            throw new InvalidOperationException("Topic does not exist.");

        topics.remove(topic);
    }
    // Adds a new topic
    public void add(String topic) throws InvalidOperationException {
        if (topics.contains(topic))
            throw new InvalidOperationException("Topic already exists.");

        topics.add(topic);
    }
    // Returns all topics of this question
    public List<String> getTopics() {
        return new ArrayList<>(topics);
    }

    // Computes the grade considering the weight of this question and the selected choice
    public float grade(int selectedChoice) {
        return 0.0f; // For compilation purposes
    }
    // Changes the weight of this question
    public void setWeight(int weight) throws InvalidOperationException {
        if (weight <= 0 || weight >= 16) {
            throw new InvalidOperationException("Weight must be between 1 and 15, inclusive.");
        }
        this.weight = weight;
    }

    // Returns the weight of this question
    public int getWeight() {
        return weight;
    }

    // Returns the choices of this question
    public List<String> getChoices() {
        return choices;
    }
}