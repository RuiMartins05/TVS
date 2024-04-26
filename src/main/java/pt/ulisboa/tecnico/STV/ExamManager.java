package pt.ulisboa.tecnico.STV;

public class ExamManager {

    public ExamManager(ExamModel model) { /* ... */ }
    // Returns the mode of this exam manager
    public final ManagerMode getMode() { /* ... */ return null; // for compilation purposes
    }
    // Enrolls a student for this exam
    public void enroll(Student t) { /* ... */ }
    // Cancels start and close operations
    public void cancel() { /* ... */ }
    // Returns an exam for the (enrolled) student
    public Exam getExam(Student student) { /* ... */ return null; // for compilation purposes
    }
    // Finish current state (
    public void close() { /* ... */ }
    // Publishes the results
    public void publish() { /* ... */ }
    // Returns the evaluation of the exam made by the specified student
    public float evaluate(Student student) { /* ... */ return 0; // for compilation purposes
    }
}