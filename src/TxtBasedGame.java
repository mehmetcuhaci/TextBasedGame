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

        String jsonFilePath = "C:\\Users\\mehme\\Desktop\\TbT\\questions.json";  // herkes kendi bilgisayarındaki yolu girsin
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(jsonFilePath);

            QuizData quizData = gson.fromJson(reader, QuizData.class);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Lütfen zorluk seçiniz (kolay,orta,zor): ");
            String difficulty = scanner.nextLine().toLowerCase();

            List<Question> questions = null;
            if ("kolay".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getEasy();
            } else if ("orta".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getMedium();
            } else if ("zor".equals(difficulty)) {
                questions = quizData.getData().getQuestions().getDifficult();
            } else {
                System.out.println("Lütfen geçerli bir zorluk giriniz!");
                return;
            }





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
