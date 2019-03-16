package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import Utils.Param;

public class MainClass {

    public static void main(String[] args) {

        for (int i = 0; i < Param.NUMBER_OF_RUNS; i++) {
            Stack<Exam> generatedExams = generateExams();
            System.out.println("========Exam Set #" + i + "===========");
            printStack(generatedExams);
            generatedExams = checkExams(generatedExams);
            generatedExams = sortExams(generatedExams);
            printStack(generatedExams);
        }
    }

    /**
     * Generate exams and students
     * Should generate a student, give him a random strength between 1 to 10
     * Generate an exam of a student without a grade yet
     *
     * @return Stack of exams
     */
    public static Stack<Exam> generateExams() {
        Stack<Exam> examStack = new Stack<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int min = 1;
        int max = 10;
        for (int i = 0; i <= Param.NUMBER_OF_EXAMS; i++) {
            Student s = new Student(rand.nextInt(min,max+1) );
            Exam e = new Exam(s);
            examStack.push(e);
        }
        return examStack;
    }

    /**
     * Checks the exams
     *
     * @param examQueue
     * @return new exam queue with exams tested
     */
    public static Stack<Exam> checkExams(Stack<Exam> examQueue) {
        Stack<Exam> toReturn = new Stack<>();
        Exam previousExam = null;
        Exam currentExam = null;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int examGrade;
        //Check the exams
        /**
         * Calculate student grade according to his strength
         * Example: Student strength is 8
         * 80% of having grade between 70-80
         */
        while (!examQueue.isEmpty()) {
            currentExam = examQueue.pop();
            int studentStrength = currentExam.getStudent().getStudentStrength();
            /*entering this loop according to the probabilities defined by the student's strength
            * for example- if the student's strength is 8, then (100 - 8 * 10) = 20
            * due to the fact that we receive a random number between 1 to 100 there's an 80% chance the the condition will be true,
            * since there are 80 numbers bigger than 20 */
            if (rand.nextInt(1,100+1) > (100 - studentStrength * 10)) {
                examGrade = rand.nextInt(studentStrength*10-10,studentStrength*10+1); //defining the range of grades according to studen't strength
            } else {
                examGrade = rand.nextInt(0,101);
            }
            if (previousExam == null) { //input check for the first iteration
                currentExam.setGrade(examGrade);
                toReturn.push(currentExam);
                previousExam = currentExam;
            } else {
                if (previousExam.getExamGrade() > 70) { //setting the student's grade according to the defined requirements
                    currentExam.setGrade((int) (examGrade * 0.88));
                    toReturn.push(currentExam);
                } else if (previousExam.getExamGrade() > 50 && previousExam.getExamGrade() < 70) {
                    currentExam.setGrade((int) (examGrade * 0.93));
                    toReturn.push(currentExam);
                } else {
                    currentExam.setGrade(examGrade);
                    toReturn.push(currentExam);
                }
                previousExam = currentExam;
            }
        }
        return toReturn;
    }

    /**
     * Prints the stack values
     *
     * @param stackGiven
     */
    public static void printStack(Stack<Exam> stackGiven) {
        if (stackGiven.isEmpty()) {
            System.out.println("Exam stack is empty.");
            return;
        }

        StringBuilder toReturn = new StringBuilder();
        Integer counter = 0;

        for (Object obj : stackGiven) {
            toReturn.append((counter++) + ". " + obj.toString() + ", ");
        }

        System.out.println(toReturn.toString());
    }

    /**
     * Sort the exams by object comparator
     *
     * @param examStack
     * @return
     */
    public static Stack<Exam> sortExams(Stack<Exam> examStack) {
        List<Exam> compareList = new ArrayList<>();
        Stack<Exam> toReturn = new Stack<>();
        compareList.addAll(examStack);
        Collections.sort(compareList);
        toReturn.addAll(compareList);
        return toReturn;
    }
}
