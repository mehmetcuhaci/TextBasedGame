import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TxtBasedGame {
    public static void main(String[] args) {

        String jsonFilePath = "C:\\Users\\mehme\\Desktop\\TbT\\questions.json";  // Replace with the actual file path
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(jsonFilePath);

            QuizData quizData = gson.fromJson(reader, QuizData.class);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Choose a difficulty (easy, medium, difficult): ");
            String difficulty = scanner.nextLine().toLowerCase();

            List<Question> questions = null;
            if ("easy".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getEasy();
            } else if ("medium".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getMedium();
            } else if ("difficult".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getDifficult();
            } else {
                System.out.println("Invalid difficulty level. Please choose from easy, medium, or difficult.");
                return;
            }

            int numQuestionsToAsk = 3; // You can adjust the number of questions
            List<Question> chosenQuestions = getRandomQuestions(questions, numQuestionsToAsk);

            int totalChoiceScore = 0;
            List<Choice> userChoices = new ArrayList<>();

            for (Question question : chosenQuestions) {
                System.out.println(question.getContent());
                List<Choice> choices = question.getChoices();

                for (Choice choice : choices) {
                    System.out.println(choice.getId() + ": " + choice.getContent());
                }

                System.out.print("Your choice (Enter the choice ID): ");
                int userChoiceId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                for (Choice choice : choices) {
                    if (choice.getId() == userChoiceId) {
                        totalChoiceScore += choice.getChoiceScore();
                        userChoices.add(choice);
                        break;
                    }
                }
            }

            System.out.println("Your total choice score: " + totalChoiceScore);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static List<Question> getRandomQuestions(List<Question> questions, int numQuestions) {
        List<Question> chosenQuestions = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            indexes.add(i);
        }

        Collections.shuffle(indexes);

        for (int i = 0; i < numQuestions; i++) {
            int index = indexes.get(i);
            chosenQuestions.add(questions.get(index));
        }

        return chosenQuestions;
    }


    class QuizData {
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    class Data {
        private boolean active;
        private Questions questions;

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public Questions getQuestions() {
            return questions;
        }

        public void setQuestions(Questions questions) {
            this.questions = questions;
        }
    }

    class Questions {
        private List<Question> easy;
        private List<Question> medium;
        private List<Question> difficult;

        public List<Question> getEasy() {
            return easy;
        }

        public void setEasy(List<Question> easy) {
            this.easy = easy;
        }

        public List<Question> getMedium() {
            return medium;
        }

        public void setMedium(List<Question> medium) {
            this.medium = medium;
        }

        public List<Question> getDifficult() {
            return difficult;
        }

        public void setDifficult(List<Question> difficult) {
            this.difficult = difficult;
        }
    }

    class Question {
        private String id;
        private String content;
        private List<Choice> choices;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }
    }

    class Choice {
        private int id;
        private String content;
        private boolean isSelected;
        private int choiceScore;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getChoiceScore() {
            return choiceScore;
        }

        public void setChoiceScore(int choiceScore) {
            this.choiceScore = choiceScore;
        }

    }
}
