package pt.ulisboa.tecnico.STV;

public class ExamModel {
    public ExamModel() { /* .... */ }
    // Adds a new group and returns the number of the created group
    public int addGroup(int maxNumberOfQuestions, String topic) {
        return 1; // for compilation purposes
    }
    // Adds a question to the specified group of this exam model. groupId can range
    // between 1 and the number of groups of this exam model.
    public boolean addQuestion(Question q, int groupId) {
        return true; // for compilation purposes
    }
    // Removes the question from the specified group of this exam model
    public void removeQuestion(Question q, int groupId) { /* .... */ }
    // Validates this exam model
    public String validate() {
        return null; // for compilation purposes
    }
}
