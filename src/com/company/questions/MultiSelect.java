package com.company.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

// TOD: implement this class
public class MultiSelect extends Question {

    public MultiSelect(String description, HashMap<Integer, String> choices, int[] answer, Scanner input) {
        super(description, choices, answer, input);
    }

    public void ask() {
        System.out.println(this.getDescription() + "\n");
        int[] response = getResponse();
        evaluate(response);
    }

    int[] getResponse() {
        // array will initialize with 0
        ArrayList<Integer> ans = new ArrayList<>();
        boolean incorrect = true;
        do {
            System.out.println("Enter the numbers corresponding to your choice with a coma(,). Say if the options 1 & 2 are correct, then enter 1,2 then press enter");
            super.displayChoices();

            if (this.getInput().hasNext()) {
                String answers = this.getInput().next();
                String []ansarray = answers.split(",");
                incorrect = false;
                for(String a: ansarray) {
                    try {
                        ans.add(Integer.parseInt(a.trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Enter valid options - I mean numbers and comma alone ");
                        incorrect = true;
                        break;
                    }
                }
            }
        } while (incorrect);

        return ans.stream().mapToInt(i->i).toArray();
    }

    @Override
    void evaluate(int[] response) {

        if(response == null || response.length < 1 || response.length != getAnswer().length) return;
        boolean everythingCorrect = true;
        for(int resp:response){
            if(!IntStream.of(getAnswer()).anyMatch(x -> x == resp)){
                everythingCorrect = false;
            }
        }
        if(everythingCorrect) this.setCorrect();
    }
}
