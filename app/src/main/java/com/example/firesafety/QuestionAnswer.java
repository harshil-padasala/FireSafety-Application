package com.example.firesafety;

public class QuestionAnswer {

    public static String question[] ={
            "Whenever a leakage (Water leakage) is noticed, rectify it immediately to avoid _______.",
            "What are the 3 factors that causes Fire?",
            "Which type of Fire extinguisher must not be used in case of Electric fire?",
            "What is the role of Class-A extinguisher?",
            "Which of the following is NOT the cause of Electrical fire?"
    };

    public static String choices[][] = {
            {"High voltage","Flux","Fire hazards","High current"},
            {"Heat, Nitrogen, Oxygen","Fuel, Heat, Oxygen","Oxygen, Fuel, Nitrogen","None of the above"},
            {"Halogen extinguisher","Carbon Chloride extinguisher","Foam extinguisher","Dry powder extinguisher"},
            {"Used on ordinary combustibles","Used on Flammable liquids","Used on electrically energized fire","Used on nonflammable metals"},
            {"Loose connections","Open doors","Electrical short circuit","Cooking"}
    };

    public static String correctAnswers[] = {
            "Fire hazards",
            "Fuel, Heat, Oxygen",
            "Foam extinguisher",
            "Used on ordinary combustibles",
            "Open doors"
    };

}
