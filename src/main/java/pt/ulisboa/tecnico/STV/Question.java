package pt.ulisboa.tecnico.STV;

import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
    private String body;
    private List<String> choices;
    private int correctChoice;
    private List<String> topics;
    private int weight;

    private final int minAmountOfChoices = 2;
    private final int maxAmountOfChoices = 8;
    private final int maxAmountOfTopics = 5;
    private final int minimumAmountOfCharactersPerTopic = 6;


    public Question(String body, List<String> choices, int correctChoice, String topic, int weight) throws
            InvalidOperationException {

        this.body = body;
        this.choices = new ArrayList<>(choices);
        this.correctChoice = correctChoice;
        this.topics = new ArrayList<>();
        topics.add(topic);
        this.weight = weight;
        
        validateComponents();
    }
    
    private void validateComponents() throws InvalidOperationException {
        validateBody(body);
        validateChoices(choices);
        validateCorrectChoice(correctChoice, choices.size());
        validateTopics(topics);
        validateWeight(weight);
    }

    private void validateWeight(int weight) throws InvalidOperationException {
        if (weight < 1 || weight > 15)
            throw new InvalidOperationException(String.format("Invalid value for weight: %d", weight));
    }

    private void validateTopics(List<String> topics) throws InvalidOperationException {
        if (topics.isEmpty())
            throw new InvalidOperationException("No topics provided.");

        if (topics.size() > maxAmountOfTopics)
            throw new InvalidOperationException(String.format("Exceeded maximum allowed topics %d.", maxAmountOfChoices));

        Set<String> uniqueTopics = new HashSet<>();
        for (String topic : topics)
            if (!uniqueTopics.add(topic))
                throw new InvalidOperationException("Duplicate topics found.");
    }

    private void validateCorrectChoice(int correctChoice, int amountOfChoices) throws InvalidOperationException {
        if (correctChoice < 0 || correctChoice >= amountOfChoices)
            throw new InvalidOperationException(String.format("Invalid value for correct choice: %d", correctChoice));
    }

    private void validateChoices(List<String> choices) throws InvalidOperationException {
        if (choices.size() < minAmountOfChoices || choices.size() > maxAmountOfChoices)
            throw new InvalidOperationException(String.format("Invalid amount of choices provided: %d", choices.size()));
    }

    private void validateBody(String body) throws InvalidOperationException {
        if (body.isEmpty())
            throw new InvalidOperationException("Body cannot be empty.");
        else if (body.length() > Integer.MAX_VALUE)
            throw new InvalidOperationException("Body length exceeds Integer.MAX_VALUE ==> Overflow.");
    }

    // Removes a topic
    public void remove(String topic) throws InvalidOperationException {
        if (!topics.contains(topic))
            throw new InvalidOperationException("Topic does not exist.");

        topics.remove(topic);
    }
    // Adds a new topic
    public void add(String topic) throws InvalidOperationException {
        if (topic.length() < minimumAmountOfCharactersPerTopic)
            throw new InvalidOperationException(String.format("A topic must have at least %d characters.",
                    minimumAmountOfCharactersPerTopic));

        if (topics.contains(topic))
            throw new InvalidOperationException("Topic already exists.");

        if (topics.size() >= maxAmountOfTopics)
            throw new InvalidOperationException(String.format("Only %d topics are allowed.", maxAmountOfTopics));


        topics.add(topic);
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

    // Returns all topics of this question
    public List<String> getTopics() {
        return new ArrayList<>(topics);
    }


    public Integer getCorrectChoice() {
        return correctChoice;
    }

    public String getBody() {
        return body;
    }

    public Integer getMaximumAmountOfTopics() {
        return maxAmountOfTopics;
    }
}