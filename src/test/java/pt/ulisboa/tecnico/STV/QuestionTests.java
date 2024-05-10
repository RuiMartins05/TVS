package pt.ulisboa.tecnico.STV;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;
import pt.ulisboa.tecnico.STV.util.Utils;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class QuestionTests {
    // Mocked data
    private final String GENERIC_TOPIC = "Harry Potter Trivia";
    private final String GENERIC_BODY = "What is the name of Harry Potter's pet owl?";
    private final int GENERIC__CORRECT_CHOICE = 1;
    private final int GENERIC_WEIGHT = 10;
    private final List<String> GENERIC_CHOICES = Arrays.asList("Scabbers", "Hedwig", "Crookshanks", "Fawkes");


    // Mocked in points
    private final Integer GENERIC_WEIGHT_IN_POINT = 2;
    private final Integer GENERIC_CORRECT_CHOICE_IN_POINT = 1;
    private final String GENERIC_BODY_IN_POINT = "a";
    private final List<String> GENERIC_CHOICES_IN_POINT = Arrays.asList("Scabbers", "Hedwig", "Crookshanks", "Fawkes");


    private Question genericQuestion = null;


    /**
     * This method ensures that the data is properly re-set before initiating a test.
     */
    @BeforeMethod
    public void initializeGenericQuestion() throws InvalidOperationException {
        genericQuestion = new Question(
                GENERIC_BODY,
                GENERIC_CHOICES,
                GENERIC__CORRECT_CHOICE,
                GENERIC_TOPIC,
                GENERIC_WEIGHT
        );
    }

    /**
     * This test represents the use case number 25 of the domain matrix.
     * Unit Test to verify that adding a duplicate topic will throw an [InvalidOperationException].
     * <p>
     * The test checks that adding a duplicate topic results in an exception and that the amount of topics available
     * under the given question remains unchanged.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     * </p>
     *
     @throws InvalidOperationException when attempting to add a duplicate topic.
     */
    @Test
    public void testDuplicateTopicThrowsException() throws InvalidOperationException {
        // Act
        assertThrows(InvalidOperationException.class,
                () -> genericQuestion.add(GENERIC_TOPIC)
        );

        // Assert
        assertEquals(genericQuestion.getTopics().stream().filter(topic -> topic.equals(GENERIC_TOPIC)).count(), 1);
        assertEquals(genericQuestion.getCorrectChoice(), GENERIC__CORRECT_CHOICE);
        assertEquals(genericQuestion.getBody(), GENERIC_BODY);
        assertEquals(genericQuestion.getWeight(), GENERIC_WEIGHT);
        assertEquals(genericQuestion.getChoices(), GENERIC_CHOICES);
        assertEquals(genericQuestion.getTopics(), List.of(GENERIC_TOPIC));
    }

    /**
     * This test represents the use case number 24 of the domain matrix.
     * Unit Test to verify that adding N+1 topics (N being the maximum amount of allowed topics) is not allowed
     * resulting in an [InvalidOperationException].
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testMaximumAllowedTopicsThrowsException() throws InvalidOperationException {
        // Act - Add N non-duplicate topics (1 from the constructor). N represents [Question.getMaximumAmountOfTopics]
        // Act - Create a N+1 non-duplicate topic
        while (genericQuestion.getTopics().size() < genericQuestion.getMaximumAmountOfTopics()) {
            String newTopic = Utils.generateRandomValidTopic();
            if (!genericQuestion.getTopics().contains(newTopic)) {
                genericQuestion.add(newTopic);
            }
        }

        String newTopic;
        do {
            newTopic = Utils.generateRandomValidTopic();
        } while (genericQuestion.getTopics().contains(newTopic));

        String finalNewTopic = newTopic;
        assertThrows(InvalidOperationException.class,
                () -> genericQuestion.add(finalNewTopic) //6th topic
        );

        // Assert
        assertEquals(genericQuestion.getTopics().size(), 5);
        assertEquals(genericQuestion.getCorrectChoice(), GENERIC__CORRECT_CHOICE);
        assertEquals(genericQuestion.getBody(), GENERIC_BODY);
        assertEquals(genericQuestion.getWeight(), GENERIC_WEIGHT);
        assertEquals(genericQuestion.getChoices(), GENERIC_CHOICES);
    }


    /**
     * This test represents the use case number 14 of the domain matrix. Instead of using a fixed off point, we generated
     * random invalid off points.
     * Unit Test to verify that attempting to add a topic that does not obey to the topic's minimum length domain constraint,
     * is rejected.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testInvalidLengthForAddedTopic() {
        // Act
        String newInvalidTopic = Utils.generateRandomInvalidTopic();

        assertThrows(InvalidOperationException.class,
                () -> genericQuestion.add(newInvalidTopic)
        );

        // Assert
        assertEquals(genericQuestion.getTopics().size(), 1);
        assertEquals(genericQuestion.getCorrectChoice(), GENERIC__CORRECT_CHOICE);
        assertEquals(genericQuestion.getBody(), GENERIC_BODY);
        assertEquals(genericQuestion.getWeight(), GENERIC_WEIGHT);
        assertEquals(genericQuestion.getChoices(), GENERIC_CHOICES);
    }

    /**
     * This test consists of a suspect-based test case. Not represented in the domain matrix.
     * Unit Test to verify that attempting to remove a topic that had not been previously added, is rejected.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testAttemptToRemoveUnExistentTopic() {
        // Act
        String newInvalidTopic = Utils.generateRandomValidTopic();

        assertThrows(InvalidOperationException.class,
                () -> genericQuestion.remove(newInvalidTopic)
        );

        // Assert
        assertEquals(genericQuestion.getTopics().size(), 1);
        assertEquals(genericQuestion.getCorrectChoice(), GENERIC__CORRECT_CHOICE);
        assertEquals(genericQuestion.getBody(), GENERIC_BODY);
        assertEquals(genericQuestion.getWeight(), GENERIC_WEIGHT);
        assertEquals(genericQuestion.getChoices(), GENERIC_CHOICES);
    }

    /**
     * This test represents the use case number 1 of the domain matrix.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testValidQuestionCreation_1() throws InvalidOperationException {
        // Arrange
        List<String> GENERIC_CHOICES = Arrays.asList("Scabbers", "Hedwig", "Crookshanks");

        // Act
        Question question = new Question(
                GENERIC_BODY_IN_POINT,
                GENERIC_CHOICES,
                GENERIC_CORRECT_CHOICE_IN_POINT,
                GENERIC_TOPIC,
                GENERIC_WEIGHT_IN_POINT
        );

        // Assert
        assertEquals(question.getTopics().size(), 1);
        assertEquals(question.getCorrectChoice(), GENERIC_CORRECT_CHOICE_IN_POINT);
        assertEquals(question.getBody(), GENERIC_BODY_IN_POINT);
        assertEquals(question.getWeight(), GENERIC_WEIGHT_IN_POINT);
        assertEquals(question.getChoices(), GENERIC_CHOICES);
        assertEquals(question.getTopics(), List.of(GENERIC_TOPIC));
    }

    /**
     * This test represents the use case number 5 of the domain matrix.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testValidQuestionCreation_5() throws InvalidOperationException {
        // Arrange
        List<String> GENERIC_CHOICES_ON_POINT = Arrays.asList("Scabbers", "Hedwig");

        // Act
        Question question = new Question(
                GENERIC_BODY_IN_POINT,
                GENERIC_CHOICES_ON_POINT,
                GENERIC_CORRECT_CHOICE_IN_POINT,
                GENERIC_TOPIC,
                GENERIC_WEIGHT_IN_POINT
        );

        // Assert
        assertEquals(question.getTopics().size(), 1);
        assertEquals(question.getCorrectChoice(), GENERIC_CORRECT_CHOICE_IN_POINT);
        assertEquals(question.getBody(), GENERIC_BODY_IN_POINT);
        assertEquals(question.getWeight(), GENERIC_WEIGHT_IN_POINT);
        assertEquals(question.getChoices(), GENERIC_CHOICES_ON_POINT);
        assertEquals(question.getTopics(), List.of(GENERIC_TOPIC));
    }

    /**
     * This test represents the use case number 9 of the domain matrix.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testValidQuestionCreation_9() throws InvalidOperationException {
        // Arrange
        int GENERIC_CHOICE_ON_POINT = 0;

        // Act
        Question question = new Question(
                GENERIC_BODY_IN_POINT,
                GENERIC_CHOICES_IN_POINT,
                GENERIC_CHOICE_ON_POINT,
                GENERIC_TOPIC,
                GENERIC_WEIGHT_IN_POINT
        );

        // Assert
        assertEquals(question.getTopics().size(), 1);
        assertEquals(question.getCorrectChoice(), GENERIC_CHOICE_ON_POINT);
        assertEquals(question.getBody(), GENERIC_BODY_IN_POINT);
        assertEquals(question.getWeight(), GENERIC_WEIGHT_IN_POINT);
        assertEquals(question.getChoices(), GENERIC_CHOICES_IN_POINT);
        assertEquals(question.getTopics(), List.of(GENERIC_TOPIC));
    }

    /**
     * This test represents the use case number 17 of the domain matrix.
     * The test also ensures that no other side effects were performed by validating the correctness of all the accessor
     * methods.
     */
    @Test
    public void testValidQuestionCreation_17() throws InvalidOperationException {
        // Arrange
        int GENERIC_WEIGHT_ON_POINT = 1;

        // Act
        Question question = new Question(
                GENERIC_BODY_IN_POINT,
                GENERIC_CHOICES_IN_POINT,
                GENERIC_CORRECT_CHOICE_IN_POINT,
                GENERIC_TOPIC,
                GENERIC_WEIGHT_ON_POINT
        );

        // Assert
        assertEquals(question.getTopics().size(), 1);
        assertEquals(question.getCorrectChoice(), GENERIC_CORRECT_CHOICE_IN_POINT);
        assertEquals(question.getBody(), GENERIC_BODY_IN_POINT);
        assertEquals(question.getWeight(), GENERIC_WEIGHT_ON_POINT);
        assertEquals(question.getChoices(), GENERIC_CHOICES_IN_POINT);
        assertEquals(question.getTopics(), List.of(GENERIC_TOPIC));
    }
}