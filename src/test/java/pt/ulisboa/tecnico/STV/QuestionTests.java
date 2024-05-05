package pt.ulisboa.tecnico.STV;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pt.ulisboa.tecnico.STV.exception.InvalidOperationException;
import pt.ulisboa.tecnico.STV.util.Utils;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class QuestionTests {
    // Mocked topics
    private final String GENERIC_TOPIC = "Harry Potter Trivia";
    private final Integer GENERIC_WEIGHT_IN_POINT = 2;
    private final Integer GENERIC_CORRECT_CHOICE_IN_POINT = 1;
    private final String GENERIC_BODY_IN_POINT = "a";
    private final List<String> GENERIC_CHOICES_IN_POINT = Arrays.asList("Scabbers", "Hedwig", "Crookshanks", "Fawkes");


    private Question genericQuestion = null;

    @DataProvider
    private Object[][] getQuestion() throws InvalidOperationException {
        return new Object[][] {
                { new Question(
                        "What is the name of Harry Potter's pet owl?",
                        Arrays.asList("Scabbers", "Hedwig", "Crookshanks", "Fawkes"),
                        1,
                        "Harry Potter Trivia",
                        10
                ) }
        };
    }

    @BeforeMethod
    public void initializeGenericQuestion() throws InvalidOperationException {
        genericQuestion = new Question(
                "What is the name of Harry Potter's pet owl?",
                Arrays.asList("Scabbers", "Hedwig", "Crookshanks", "Fawkes"),
                1,
                GENERIC_TOPIC,
                10
        );
    }

    /**
     * Unit Test to verify that adding a duplicate topic will throw an [InvalidOperationException].
     * <p>
     * The test checks that adding a duplicate topic results in an exception and that the amount of topics available
     * under the given question remains unchanged.
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
    }

    /**
     * Unit Test to verify that adding N+1 topics (N being the maximum amount of allowed topics) is not allowed
     * resulting in an [InvalidOperationException].
     *
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
    }


    /**
     * Unit Test to verify that attempting to add a topic that does not obey to the topic's minimum length domain constraint,
     * is rejected.
     *
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
    }

    /**
     * Unit Test to verify that attempting to remove a topic that had not been previously added, is rejected.
     *
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
    }

    /**
     * This test represents the use case number 1 of the domain matrix.
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