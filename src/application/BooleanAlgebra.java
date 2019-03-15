/**
 * @authors Mian Usman Naaem Kakakhel
 * last edit: 10 May 2018
 */
package application;

import java.util.*;
import java.io.*;

//BooleanAlgebra
public class BooleanAlgebra {
    //properties
    String equation;
    //String originalEquation;
    //ArrayList<ArrayList<String>> substitution;

    //constructors
    public BooleanAlgebra(String equation) {
        this.equation = equation;
        //substitution = new ArrayList<ArrayList<String>>();
    }

    //methods

    /**
     * Simplifies Strings that can be simplified in any way from the given rules of boolean algebra.
     * @return String which is the simplified form.
     */
    public String getReducedStatement() {
//    String back = equation;
//    String prev = "";
//    while(!back.equals(prev))
//    {
//      prev = back;
//      originalEquation = prev;
//      back = this.takeCommon(prev);
//    }
//    return back;
        return addBrackAtNot(this.takeCommon(equation));
    }

    /**
     * Simplifies Strings that can be simplified in any way from the given rules of boolean algebra.
     * @return String which is the simplified form.
     */
    public String computeSimplifiedForm() {
        String equationPreviously = "0";

        while (equationPreviously != equation) {
            equationPreviously = equation;
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                involution();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                deMorgan();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                numericalOperation();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                removeExtraBrackets();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                identity();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                complimentation();
            //System.out.println(equation);
            if ((equation.charAt(0) == '(') && (equation.charAt(equation.length() - 1) == ')'))
                rule11();
            //System.out.println(equation);
            numericalInversion();

        }
        return equation;
    }

    /**
     * If statement is A + (~A.B.C.D.(~A.R.K).H.U.............Z) turns it to A + (~A.B.C.....Z)
     * @param eq the equation which is to be prepared for rule11
     * @return eq the equation which is prepared for rule 11
     */
    private String prepareForRule11(String eq) {
        ArrayList<Brackets> bracketLocs = new ArrayList<Brackets>();
        eq = "(" + eq + ")";
        bracketLocs = bracketsLocations(eq);
        for (int k = 0; k < bracketLocs.size(); k++) {
            if (bracketLocs.get(k).depth == 1) {
                eq = eq.substring(0, bracketLocs.get(k).openLocation) + eq.substring(bracketLocs.get(k).closeLocation + 1);
                bracketLocs = bracketsLocations(eq);
            }
        }
        return eq = eq.substring(1, eq.length() - 1);
    }


    /**
     * Checks if the given string is Only involved in straight products or not.
     * @param eq is the equation which has to be checked for nothing else than products.
     * @return boolean is the answer if true or not.
     */
    private boolean isProductsOnly(String eq) {
        boolean check = true;
        char a = 'a';
        eq = prepareForRule11(eq);
        for (int j = 0; j < eq.length(); j++) {
            a = eq.charAt(j);
            if ((eq.charAt(j) == '+') || (eq.charAt(j) == '(') || (eq.charAt(j) == ')'))
                check = false;
        }
        return check;
    }

    /**
     * Basically checks if the given string is given in the bigger given string.
     * @param eq is the bigger equation in which input is to be found.
     * @param input is the string which is the string to be found.
     * @return boolean is the answer if present or not.
     */
    private boolean inputPresent(String eq, String input) {
        boolean check = false;
        eq = prepareForRule11(eq);
        for (int j = 0; j < eq.length(); j++) {
            if (input.length() == 1) {
                if ((input.equals(eq.charAt(j) + "")) && ((j < 1) || (eq.charAt(j - 1) != '~')))
                    check = true;
            } else if (input.length() == 2) {
                if ((j < eq.length() - 1) && (input.equals(eq.substring(j, j + 2))))
                    check = true;
            }
        }
        return check;
    }

    /**
     * Basically omits the string not needed from a bigger string.
     * @param eq is the bigger equation from which cutting is to be done.
     * @param input is the string which is to be omitted
     * @return String is the cutted string.
     */
    private String omitInput(String eq, String input) {
        for (int j = 0; j < eq.length(); j++) {
            if (input.length() == 1) {
                if ((input.equals(eq.charAt(j) + "")) && ((j < 1) || (eq.charAt(j - 1) != '~'))) {
                    if (j == 0)
                        eq = eq.substring(2);
                    else
                        eq = eq.substring(0, j - 1) + eq.substring(j + 1);

                    if (j >= 2)
                        j = j - 2;
                }
            } else if (input.length() == 2) {
                if ((j < eq.length() - 1) && (input.equals(eq.substring(j, j + 2)))) {
                    if (j == 0)
                        eq = eq.substring(3);
                    else
                        eq = eq.substring(0, j - 1) + eq.substring(j + 2);

                    if (j >= 3)
                        j = j - 3;
                }
            }
        }
        return eq;
    }

    /**
     * Rule 11 is basically A + (~A.B.C.D.H.U.............Z) = A + (B.C.D.E..........Z).
     */
    public void rule11() {
        ArrayList<Brackets> bracketLocs = new ArrayList<Brackets>();
        bracketLocs = bracketsLocations(equation);
        for (int i = 0; i < bracketLocs.size(); i++) {

            //~A + (A.B.C.D.H.U.............Z) = ~A + (B.C.D.E..........Z)
            if ((bracketLocs.get(i).openLocation >= 3) && (equation.charAt(bracketLocs.get(i).openLocation - 1) == '+') &&
                    (equation.charAt(bracketLocs.get(i).openLocation - 2) >= 'A') && (equation.charAt(bracketLocs.get(i).openLocation - 2) <= 'Z') &&
                    (equation.charAt(bracketLocs.get(i).openLocation - 3) == '~') &&
                    isProductsOnly(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation)) &&
                    inputPresent(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation), "" + equation.charAt(bracketLocs.get(i).openLocation - 2))) {
                equation = equation.substring(0, bracketLocs.get(i).openLocation + 1) +
                        omitInput(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation), "" + equation.charAt(bracketLocs.get(i).openLocation - 2))
                        + equation.substring(bracketLocs.get(i).closeLocation);
                bracketLocs = bracketsLocations(equation);
            }

            //A + (~A.B.C.D.H.U.............Z) = A + (B.C.D.E..........Z)
            if ((bracketLocs.get(i).openLocation >= 2) && (equation.charAt(bracketLocs.get(i).openLocation - 1) == '+') &&
                    (equation.charAt(bracketLocs.get(i).openLocation - 2) >= 'A') && (equation.charAt(bracketLocs.get(i).openLocation - 2) <= 'Z') &&
                    isProductsOnly(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation)) &&
                    inputPresent(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation), "~" + equation.charAt(bracketLocs.get(i).openLocation - 2))) {
                equation = equation.substring(0, bracketLocs.get(i).openLocation + 1) +
                        omitInput(equation.substring(bracketLocs.get(i).openLocation + 1, bracketLocs.get(i).closeLocation), "~" + equation.charAt(bracketLocs.get(i).openLocation - 2))
                        + equation.substring(bracketLocs.get(i).closeLocation);
                bracketLocs = bracketsLocations(equation);
            }

        }

    }

    /**
     * Takes common in the SOP statements formed from the truth table.
     * @param eq is the equation formed from the truth table.
     * @return String which is the commonTaken form.
     */
    public String takeCommon(String eq) {
        TruthTable myTable = new TruthTable();
        if (!eq.equals(""))
            myTable.createTable(eq);

        int mostInputs = 0;
        String mostInputsName = "";
        String newEq = "";
        String eqToRecur = "";
        boolean sub = false;
        int bracketPlace = 0;

        ArrayList<InputPSOP.Types> inputTimes = new ArrayList<InputPSOP.Types>();
        ArrayList<Brackets> bracketLocs = new ArrayList<Brackets>();
        InputPSOP x = new InputPSOP(myTable.makeStatement());
        inputTimes = x.getRepeatTimes();

        mostInputsName = countInput(inputTimes);
        mostInputs = countInputTimes(inputTimes);

        newEq = takeCommonSOP1(mostInputsName, mostInputs, x, myTable.makeStatement());

        if (mostInputs > 1) {
            bracketLocs = bracketsLocations(newEq);

            for (int i = 0; i < bracketLocs.size(); i++) {
                if ((mostInputsName.length() == 1) && (bracketLocs.get(i).openLocation >= 2) && ((bracketLocs.get(i).openLocation < 3) ||
                        (newEq.charAt(bracketLocs.get(i).openLocation - 3) != '~')) &&
                        (newEq.substring(bracketLocs.get(i).openLocation - 2, bracketLocs.get(i).openLocation).equals(mostInputsName + ".")))
                    bracketPlace = i;
                else if ((mostInputsName.length() == 2) && (bracketLocs.get(i).openLocation >= 3) &&
                        (newEq.substring(bracketLocs.get(i).openLocation - 3, bracketLocs.get(i).openLocation).equals(mostInputsName + ".")))
                    bracketPlace = i;
            }
            eqToRecur = newEq.substring(bracketLocs.get(bracketPlace).openLocation, bracketLocs.get(bracketPlace).closeLocation + 1);

            newEq = newEq.substring(0, bracketLocs.get(bracketPlace).openLocation) + takeCommon(eqToRecur) +
                    newEq.substring(bracketLocs.get(bracketPlace).closeLocation + 1);

            if ((x.getInputsSOP().size() - mostInputs) > 1) {
                bracketLocs = bracketsLocations(newEq);
                for (int i = 0; i < bracketLocs.size(); i++) {
                    if ((mostInputsName.length() == 2) && (bracketLocs.get(i).openLocation >= 2) && ((bracketLocs.get(i).openLocation < 3) ||
                            (newEq.charAt(bracketLocs.get(i).openLocation - 3) != '~')) &&
                            (newEq.substring(bracketLocs.get(i).openLocation - 2, bracketLocs.get(i).openLocation).equals(mostInputsName.substring(1) + ".")))
                        bracketPlace = i;
                    else if ((mostInputsName.length() == 1) && (bracketLocs.get(i).openLocation >= 3) &&
                            (newEq.substring(bracketLocs.get(i).openLocation - 3, bracketLocs.get(i).openLocation).equals("~" + mostInputsName + ".")))
                        bracketPlace = i;
                }
                eqToRecur = newEq.substring(bracketLocs.get(bracketPlace).openLocation, bracketLocs.get(bracketPlace).closeLocation + 1);

                newEq = newEq.substring(0, bracketLocs.get(bracketPlace).openLocation) + takeCommon(eqToRecur) +
                        newEq.substring(bracketLocs.get(bracketPlace).closeLocation + 1);
            }
        }
//    else 
//    {
//      for (int l = 0; l < newEq.length(); l++)
//      {
//        if ((newEq.charAt(l) >= 'A') && (newEq.charAt(l) <= 'Z') && (newEq.length() > 1) && (!newEq.equals(originalEquation)))
//          sub = true;
//      }
//      if (sub)
//      {
//        for (int y = 65; y <= 90; y++)
//        {
//          if ((originalEquation.indexOf((char)y) == -1))
//          {
//            newEq = (char)y + "";
//          }
//        }
//        
//      }
//
//    }

        equation = newEq;
        computeSimplifiedForm();
        newEq = equation;
        return newEq;
    }

    /**
     * Takes common with both inverse and input taken common using takeCommonSOP.
     * @param mostInputsName is the name of the repeated input.
     * @param mostInputs int is the most time of repetitions
     * @param x is the SOP statement.
     * @param eq is the original equation SOP statement.
     * @return String which is the new eq with common taken.
     */
    private String takeCommonSOP1(String mostInputsName, int mostInputs, InputPSOP x, String eq) {
        String newEq = "";

        if (mostInputs > 1) {
            newEq = "((" + mostInputsName + ".(" + takeCommonSOP(mostInputsName, x, false);
            if ((x.getInputsSOP().size() - mostInputs) > 1) {
                if (mostInputsName.charAt(0) == '~')
                    newEq = newEq + "(" + mostInputsName.substring(1) + ".(" + takeCommonSOP(mostInputsName.substring(1), x, false);
                else if (!(mostInputsName.charAt(0) == '~'))
                    newEq = newEq + "(" + "~" + mostInputsName + ".(" + takeCommonSOP("~" + mostInputsName, x, false);

                newEq = newEq.substring(0, newEq.length() - 1);
            } else {
                if (x.getInputsSOP().size() - mostInputs == 1) {
                    if (mostInputsName.charAt(0) == '~')
                        newEq = newEq + takeCommonSOP(mostInputsName.substring(1), x, true);
                    else if (!(mostInputsName.charAt(0) == '~'))
                        newEq = newEq + takeCommonSOP("~" + mostInputsName, x, true);

                    newEq = newEq.substring(0, newEq.length() - 1);
                } else if (x.getInputsSOP().size() - mostInputs == 0)
                    newEq = newEq.substring(0, newEq.length() - 1);
            }
            newEq = newEq + ")";
        } else {
            newEq = eq;
        }
        return newEq;
    }

    /**
     * from the given most input value and size, and SOP Statement, take common for only one instance of the input, not for its inverse.
     *
     * @param mostInputsName is the name of the repeated input.
     * @param is1 boolean which shows if the given thing is one group of products without sum or not.
     * @param x is the SOP statement.
     * @return String which is the new eq with common taken.
     */
    private String takeCommonSOP(String mostInputsName, InputPSOP x, boolean is1) {
        ArrayList<String> inputs = new ArrayList<String>();
        boolean check = false;
        String newEq = "";

        for (int i = 0; i < x.getInputsSOP().size(); i++) {
            inputs = x.getInputsSOP().get(i).getInputs();
            for (int z = 0; z < inputs.size(); z++) {
                if (inputs.get(z).equals(mostInputsName))
                    check = true;
            }
            if (check) {
                newEq = newEq + "(";
                for (int j = 0; j < inputs.size(); j++) {
                    if ((!inputs.get(j).equals(mostInputsName)) || (is1))
                        newEq = newEq + inputs.get(j) + ".";
                }
                newEq = newEq.substring(0, newEq.length() - 1) + ")+";
            }
            check = false;
        }
        if (!is1)
            newEq = newEq.substring(0, newEq.length() - 1) + "))+";

        return newEq;
    }

    /**
     * Finds the biggest repeating input in SOP statement broken in the Types ArrayList an returns the input which has most repetetions .
     * @param inputTimes is the broken SOP statment from which recurrence is to be calculated.
     * @return String which is the most repeated input.
     */
    private String countInput(ArrayList<InputPSOP.Types> inputTimes) {
        int mostInputs = 0;
        String mostInputsName = "";

        for (int i = 0; i < inputTimes.size(); i++) {
            if (inputTimes.get(i).getTheNameOne().size() > mostInputs) {
                mostInputs = inputTimes.get(i).getTheNameOne().size();
                mostInputsName = inputTimes.get(i).getName();
            } else if (inputTimes.get(i).getTheNameNotOne().size() > mostInputs) {
                mostInputs = inputTimes.get(i).getTheNameNotOne().size();
                mostInputsName = "~" + inputTimes.get(i).getName();
            }
        }

        return mostInputsName;
    }

    /**
     * Finds the biggest repeating input in SOP statement broken in the Types ArrayList an returns the most repetetions .
     * @param inputTimes is the broken SOP statment from which recurrence is to be calculated.
     * @return int which is the most repeated number.
     */
    private int countInputTimes(ArrayList<InputPSOP.Types> inputTimes) {
        int mostInputs = 0;
        String mostInputsName = "";

        for (int i = 0; i < inputTimes.size(); i++) {
            if (inputTimes.get(i).getTheNameOne().size() > mostInputs) {
                mostInputs = inputTimes.get(i).getTheNameOne().size();
                mostInputsName = inputTimes.get(i).getName();
            } else if (inputTimes.get(i).getTheNameNotOne().size() > mostInputs) {
                mostInputs = inputTimes.get(i).getTheNameNotOne().size();
                mostInputsName = "~" + inputTimes.get(i).getName();
            }
        }
        return mostInputs;
    }


    /**
     * Finds all the inputs in a boolean expression.
     * @param eq is the string which holds the inputs.
     * @return array list with the inputs.
     */
    public ArrayList<String> findInputsInExpression(String eq) {
        ArrayList<String> myLetters = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        String in = "";
        boolean check = true;

        for (int i = 0; i < eq.length(); i++) {
            if ((eq.charAt(i) >= 'A') && (eq.charAt(i) <= 'Z')) {
                in = eq.charAt(i) + "";

                if ((myLetters.indexOf(in) == -1) && (in.length() == 1)) {
                    myLetters.add(in);
                }
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            check = true;
            for (int j = 0; j < myLetters.size(); j++) {
                if (myLetters.get(j).equals(temp.get(i).substring(1))) {
                    check = false;
                }
            }
            if (check)
                myLetters.add(temp.get(i));
        }
        Collections.sort(myLetters);
        //Collections.sort(myLetters, Collections.reverseOrder());
        return myLetters;
    }

    /**
     * Gives the places in the string which have the given character.
     * @param myChar is the character which is to be found.
     * @return An arrayList of integers which have all the places of the character in that string.
     */
    public ArrayList<Integer> charAtLocations(char myChar) {
        ArrayList<Integer> locations = new ArrayList<Integer>();

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == myChar) {
                locations.add(i);
            }
        }
        return locations;
    }

    /**
     * Performs involution which is the removing of even numbers of inversions on any equation.
     */
    public void involution() {
        ArrayList<Integer> locations = new ArrayList<Integer>();
        locations = charAtLocations('~');

        for (int t = 0; t < locations.size() - 1; t++) {
            if ((locations.get(t) + 1) == locations.get(t + 1)) {
                equation = equation.substring(0, locations.get(t)) + equation.substring(locations.get(t) + 2, equation.length());
                locations = charAtLocations('~');
                if (t != 0)
                    t = t - 2;
                else
                    t--;
            }
        }
    }

    /**
     * Determines the bracket places in the given string and their opening and closing places and the depth of the brackets.
     * @param equation is the equation which has the brackets to be manipulated
     * @return an arrayList of the class brackets which have all the properties of the brackets in question.
     */
    public ArrayList<Brackets> bracketsLocations(String equation) {
        ArrayList<Brackets> bracketLocations = new ArrayList<Brackets>();
        int j = 0;
        int tmp = 0;
        int d = -1;

        for (int i = 0; i < equation.length(); i++) //loops to the whole size of the string
        {
            if (equation.charAt(i) == '(')  //if the bracket opens then
            {
                bracketLocations.add(new Brackets(i));
                j = bracketLocations.size();
                if (i == 0)
                    bracketLocations.get(j - 1).charBeyond = equation.charAt(i);
                else
                    bracketLocations.get(j - 1).charBeyond = equation.charAt(i - 1);
            } else if (equation.charAt(i) == ')') {
                j = bracketLocations.size();

                while ((j >= 0) && (!bracketLocations.get(j - 1).openStatus))
                    j--;

                for (int k = 0; k < bracketLocations.size(); k++) {
                    if (bracketLocations.get(k).openStatus)
                        d++;
                }

                bracketLocations.get(j - 1).openStatus = false;
                bracketLocations.get(j - 1).closeLocation = i;
                bracketLocations.get(j - 1).depth = d;
                //sets the depth of brackets
                if (d > bracketLocations.get(j - 1).maxDepth) {
                    for (int f = 0; f < j; f++)
                        bracketLocations.get(f).maxDepth = d;
                }

                d = -1;
            }
        }
        return bracketLocations;
    }

    /**
     * Adds and manipulates the string given for adding ~ and exchanging . for + and vice versa for deMorgan rule.
     * @param partEquation is the string in which stuff is added
     * @return the manipulated string
     */
    private String deMorganAdd(String partEquation) {
        String giveEquation = "";
        ArrayList<Brackets> bracketLocations = new ArrayList<Brackets>();
        bracketLocations = this.bracketsLocations(partEquation);
        int k = 0;
        int j = -1;
        while (k < partEquation.length()) {
            while ((k < partEquation.length()) && (partEquation.charAt(k) != '(')) {
                if (partEquation.charAt(k) == '+')
                    giveEquation = giveEquation + '.';
                else if (partEquation.charAt(k) == '.')
                    giveEquation = giveEquation + '+';
                else if ((partEquation.charAt(k) >= 'A') && (partEquation.charAt(k) <= 'Z'))
                    giveEquation = giveEquation + '~' + partEquation.charAt(k);
                else if (partEquation.charAt(k) == '~')
                    giveEquation = giveEquation + partEquation.charAt(k);
                else if (partEquation.charAt(k) == '0')
                    giveEquation = giveEquation + '1';
                else if (partEquation.charAt(k) == '1')
                    giveEquation = giveEquation + '0';

                k++;
            }

            do {
                j++;
                if ((k < partEquation.length()) && (bracketLocations.get(j).depth == 0)) {
                    giveEquation = giveEquation + '~' +
                            partEquation.substring(bracketLocations.get(j).openLocation, bracketLocations.get(j).closeLocation + 1);
                }
            } while ((k < partEquation.length()) && (bracketLocations.get(j).depth != 0));

            if (k < partEquation.length())
                k = bracketLocations.get(j).closeLocation + 1;
        }

        return giveEquation;
    }

    /**
     * Manipulates the main string for applying deMorgan rule and cuts and sends the part for manipulation to
     * deMorganAdd and adds the returned string to the main string.
     */
    public void deMorgan() {
        ArrayList<Brackets> bracketLocations = new ArrayList<Brackets>();
        bracketLocations = this.bracketsLocations(equation);

        for (int de = 0; de <= bracketLocations.get(0).maxDepth; de++) {
            for (int i = 0; i < bracketLocations.size(); i++) {
                if ((bracketLocations.get(i).charBeyond == '~') && (bracketLocations.get(i).depth == de)) {
                    equation = equation.substring(0, bracketLocations.get(i).openLocation - 1) +
                            equation.substring(bracketLocations.get(i).openLocation, bracketLocations.get(i).openLocation + 1) +
                            deMorganAdd(equation.substring(bracketLocations.get(i).openLocation + 1, bracketLocations.get(i).closeLocation)) +
                            equation.substring(bracketLocations.get(i).closeLocation, equation.length());

                    bracketLocations = this.bracketsLocations(equation);
                    de = 0;
                    i--;
                }
            }
        }

        numericalInversion();
    }

    /**
     * Checks for any ~0 or ~1 in the equation and inverts them to 1 or 0 respectively.
     */
    public void numericalInversion() {
        for (int j = 0; j < equation.length() - 1; j++) {
            if ((equation.charAt(j) == '~') && (equation.charAt(j + 1) == '0')) {
                equation = equation.substring(0, j) + '1' + equation.substring(j + 2, equation.length());
                j--;
            } else if ((equation.charAt(j) == '~') && (equation.charAt(j + 1) == '1')) {
                equation = equation.substring(0, j) + '0' + equation.substring(j + 2, equation.length());
                j--;
            }
        }
    }

    /**
     * loop for . with ~X at front.
     * loop for . with ~X at back
     */
    private void numOp1() {
        //loop for . with ~X at front
        //loop for . with ~X at back
        for (int j = 0; j < equation.length() - 3; j++) {
            //0.~X = 0;
            if ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '.') &&
                    (equation.charAt(j + 2) == '~') && (equation.charAt(j + 3) >= 'A') && (equation.charAt(j + 3) <= 'Z')) {
                equation = equation.substring(0, j) + '0' + equation.substring(j + 4, equation.length());
                if (j > 2)
                    j = j - 3;
            }
            //1.~X = ~X
            else if ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '.') &&
                    (equation.charAt(j + 2) == '~') && (equation.charAt(j + 3) >= 'A') && (equation.charAt(j + 3) <= 'Z')) {
                equation = equation.substring(0, j) + equation.substring(j + 2, equation.length());
                if (j > 1)
                    j = j - 2;
            }
            //~X.0 = 0
            else if ((equation.charAt(j) == '~') && (equation.charAt(j + 1) >= 'A') &&
                    (equation.charAt(j + 1) <= 'Z') && (equation.charAt(j + 2) == '.') && (equation.charAt(j + 3) == '0')) {
                equation = equation.substring(0, j) + '0' + equation.substring(j + 4, equation.length());
                if (j > 1)
                    j = j - 3;
            }
        }
    }

    /**
     * loop for . with X anywhere.
     * loop for . with 0,1 anywhere
     */
    private void numOp2() {
        //loop for . with X anywhere
        //loop for . with 0,1 anywhere
        for (int j = 0; j < equation.length() - 2; j++) {
            //X.0 = 0; and 0.X = 0;
            //0.0 = 0; and 0.1 = 0; and 1.0 = 0;
            if (((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z') &&
                    (equation.charAt(j + 1) == '.') && (equation.charAt(j + 2) == '0')) ||
                    ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '.') &&
                            (equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z'))
                    || ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '.') &&
                    (equation.charAt(j + 2) == '0')) || ((equation.charAt(j) == '0') &&
                    (equation.charAt(j + 1) == '.') && (equation.charAt(j + 2) == '1')) ||
                    ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '.') &&
                            (equation.charAt(j + 2) == '0'))) {
                equation = equation.substring(0, j) + '0' + equation.substring(j + 3, equation.length());
                if (j > 1)
                    j = j - 2;
            }
            //X.1 = X; and 1.X = X;
            else if (((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z') &&
                    (equation.charAt(j + 1) == '.') && (equation.charAt(j + 2) == '1')) ||
                    ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '.') &&
                            (equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z'))) {
                if ((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z')) {
                    equation = equation.substring(0, j) + equation.charAt(j) + equation.substring(j + 3, equation.length());
                } else if ((equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z')) {
                    equation = equation.substring(0, j) + equation.charAt(j + 2) + equation.substring(j + 3, equation.length());
                }
                if (j > 1)
                    j = j - 2;
            }
            //1.1 = 1;
            else if ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '.') &&
                    (equation.charAt(j + 2) == '1')) {
                equation = equation.substring(0, j) + '1' + equation.substring(j + 3, equation.length());
                if (j > 1)
                    j = j - 2;
            }
        }
    }


    /**
     * loop for + with ~x at front
     * loop for + with ~x at back
     */
    private void numOp3() {
        //loop for + with ~x at front
        //loop for + with ~x at back
        for (int j = 0; j < equation.length() - 3; j++) {
            //0+~X = ~X;
            if ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '+') &&
                    (equation.charAt(j + 2) == '~') && (equation.charAt(j + 3) >= 'A') && (equation.charAt(j + 3) <= 'Z')) {
                equation = equation.substring(0, j) + equation.substring(j + 2, equation.length());
                if (j > 1)
                    j = j - 2;
            }
            //1+~X = 1;
            else if ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '+') &&
                    (equation.charAt(j + 2) == '~') && (equation.charAt(j + 3) >= 'A') && (equation.charAt(j + 3) <= 'Z')) {
                equation = equation.substring(0, j) + '1' + equation.substring(j + 4, equation.length());
                if (j > 2)
                    j = j - 3;
            }
            //~X+1 = 1;
            else if ((equation.charAt(j) == '~') && (equation.charAt(j + 1) >= 'A') &&
                    (equation.charAt(j + 1) <= 'Z') && (equation.charAt(j + 2) >= '+') && (equation.charAt(j + 3) == '1')) {
                equation = equation.substring(0, j) + '1' + equation.substring(j + 4, equation.length());
                if (j > 2)
                    j = j - 3;
            }
        }
    }

    /**
     * loop for + with X anywhere
     * loop for + with 0,1 anywhere
     */
    private void numOp4() {
        //loop for + with X anywhere
        //loop for + with 0,1 anywhere
        for (int j = 0; j < equation.length() - 2; j++) {
            //X+0 = X; and 0+X = X;
            if (((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z') &&
                    (equation.charAt(j + 1) == '+') && (equation.charAt(j + 2) == '0')) ||
                    ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '+') &&
                            (equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z'))) {
                if ((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z')) {
                    equation = equation.substring(0, j) + equation.charAt(j) + equation.substring(j + 3, equation.length());
                } else if ((equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z')) {
                    equation = equation.substring(0, j) + equation.charAt(j + 2) + equation.substring(j + 3, equation.length());
                }
                if (j > 1)
                    j = j - 2;
            }
            //0+0 = 0;
            else if ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '+') &&
                    (equation.charAt(j + 2) == '0')) {
                equation = equation.substring(0, j) + '0' + equation.substring(j + 3, equation.length());
                if (j > 1)
                    j = j - 2;
            }
            //X+1 = 1; and 1+X = 1; and 0+1 = 1; and 1+0 = 1; 1+1 = 1;
            else if (((equation.charAt(j) >= 'A') && (equation.charAt(j) <= 'Z') &&
                    (equation.charAt(j + 1) == '+') && (equation.charAt(j + 2) == '1')) ||
                    ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '+') &&
                            (equation.charAt(j + 2) >= 'A') && (equation.charAt(j + 2) <= 'Z'))
                    || ((equation.charAt(j) == '0') && (equation.charAt(j + 1) == '+') &&
                    (equation.charAt(j + 2) == '1')) || ((equation.charAt(j) == '1') &&
                    (equation.charAt(j + 1) == '+') && (equation.charAt(j + 2) == '0'))
                    || ((equation.charAt(j) == '1') && (equation.charAt(j + 1) == '+') &&
                    (equation.charAt(j + 2) == '1'))) {
                equation = equation.substring(0, j) + '1' + equation.substring(j + 3, equation.length());
                if (j > 1)
                    j = j - 2;
            }
        }
    }


    /**
     * Checks for any 0 or 1 in the equation and solves them compared to what is present in the surroundings.
     */
    public void numericalOperation() {
        numericalInversion();
        //loop for . with ~X at front
        //loop for . with ~X at back
        numOp1();
        //loop for . with X anywhere
        //loop for . with 0,1 anywhere
        numOp2();
        //loop for + with ~x at front
        //loop for + with ~x at back
        numOp3();
        //loop for + with X anywhere
        //loop for + with 0,1 anywhere
        numOp4();


    }

    /**
     * Removes any extra brackets, the equation may have.
     */
    public void removeExtraBrackets() {
        ArrayList<Brackets> bracketLocations = new ArrayList<Brackets>();
        bracketLocations = this.bracketsLocations(equation);

        for (int i = 0; i < bracketLocations.size(); i++) {
            if (bracketLocations.get(i).openLocation + 1 == bracketLocations.get(i).closeLocation) {
                equation = equation.substring(0, bracketLocations.get(i).openLocation) +
                        equation.substring(bracketLocations.get(i).closeLocation + 1, equation.length());

                bracketLocations = this.bracketsLocations(equation);
                i--;
            } else if ((bracketLocations.get(i).openLocation + 2 == bracketLocations.get(i).closeLocation) ||
                    (bracketLocations.get(i).openLocation + 3 == bracketLocations.get(i).closeLocation)) {
                equation = equation.substring(0, bracketLocations.get(i).openLocation) +
                        equation.substring(bracketLocations.get(i).openLocation + 1, bracketLocations.get(i).closeLocation) +
                        equation.substring(bracketLocations.get(i).closeLocation + 1, equation.length());

                bracketLocations = this.bracketsLocations(equation);
                i--;
            } else if ((i < (bracketLocations.size() - 1)) && (bracketLocations.get(i).openLocation + 1 == bracketLocations.get(i + 1).openLocation) &&
                    (bracketLocations.get(i).closeLocation - 1 == bracketLocations.get(i + 1).closeLocation)) {
                equation = equation.substring(0, bracketLocations.get(i).openLocation) +
                        equation.substring(bracketLocations.get(i).openLocation + 1, bracketLocations.get(i).closeLocation) +
                        equation.substring(bracketLocations.get(i).closeLocation + 1, equation.length());

                bracketLocations = this.bracketsLocations(equation);
                i--;
            }
        }

    }

    /**
     * if any variable is written two times as + or . it is reduced to one.
     */
    public void identity() {
        for (int i = 0; i < equation.length() - 2; i++) {
            //X+X = X and X.X = X
            if ((equation.charAt(i) >= 'A') && (equation.charAt(i) <= 'Z') && ((equation.charAt(i + 1) == '.') ||
                    (equation.charAt(i + 1) == '+'))
                    && (equation.charAt(i + 2) == equation.charAt(i)) && ((i == 0) || (equation.charAt(i - 1) != '~'))) {
                equation = equation.substring(0, i + 1) + equation.substring(i + 3, equation.length());
                i = i - 2;
            }
        }

        //~X+~X = ~X and ~X.~X =~X
        for (int i = 0; i < equation.length() - 4; i++) {
            if ((equation.charAt(i) == '~') && (equation.charAt(i + 1) >= 'A') && (equation.charAt(i + 1) <= 'Z') &&
                    ((equation.charAt(i + 2) == '.') || (equation.charAt(i + 2) == '+'))
                    && (equation.charAt(i + 3) == '~') && (equation.charAt(i + 4) == equation.charAt(i + 1))) {
                equation = equation.substring(0, i + 2) + equation.substring(i + 5, equation.length());
                i = i - 3;
            }
        }

    }

    /**
     * if a compliment of a variable is + or . with its variable then answer is 1 or 0 respectively.
     */
    public void complimentation() {
        for (int i = 0; i < equation.length() - 2; i++) {
            //X.~X = 0 and ~X.X = 0
            if (((equation.charAt(i) >= 'A') && (equation.charAt(i) <= 'Z') && (equation.charAt(i + 1) == '.')
                    && (equation.charAt(i + 2) == '~') && (equation.charAt(i + 3) == equation.charAt(i)) &&
                    ((i == 0) || (equation.charAt(i - 1) != '~'))) || ((equation.charAt(i) == '~') &&
                    (equation.charAt(i + 1) >= 'A') &&
                    (equation.charAt(i + 1) <= 'Z') &&
                    (equation.charAt(i + 2) == '.') &&
                    (equation.charAt(i + 3) == equation.charAt(i + 1)))) {
                equation = equation.substring(0, i) + '0' + equation.substring(i + 4, equation.length());
                if (i > 2)
                    i = i - 3;
            }

            //X+~X = 1 and ~X+X = 1
            else if (((equation.charAt(i) >= 'A') && (equation.charAt(i) <= 'Z') && (equation.charAt(i + 1) == '+')
                    && (equation.charAt(i + 2) == '~') && (equation.charAt(i + 3) == equation.charAt(i)) &&
                    ((i == 0) || (equation.charAt(i - 1) != '~'))) || ((equation.charAt(i) == '~') &&
                    (equation.charAt(i + 1) >= 'A') &&
                    (equation.charAt(i + 1) <= 'Z') &&
                    (equation.charAt(i + 2) == '+') &&
                    (equation.charAt(i + 3) == equation.charAt(i + 1)))) {
                equation = equation.substring(0, i) + '1' + equation.substring(i + 4, equation.length());
                if (i > 2)
                    i = i - 3;
            }
        }

    }


    /**
     * method to add brackets at not gate
     * @param statement the statement in which brackets should be added
     * @return the statement with brackets
     */
    public String addBrackAtNot(String statement) {

        for (int i = 0; i < statement.length(); i++) {
            if ((statement.charAt(i) == '~') && (statement.charAt(i + 1) >= 'A') && (statement.charAt(i + 1) <= 'Z') ) {
                statement = statement.substring(0, i) + "(" + statement.charAt(i) + statement.charAt(i + 1) + ")" + statement.substring(i + 2);
                i++;
            }
        }

        ArrayList<Brackets> bracketLocations = new ArrayList<Brackets>();
        bracketLocations = this.bracketsLocations(statement);

        for (int i = 1; i < bracketLocations.size(); i++) {
            if (statement.charAt(bracketLocations.get(i).openLocation - 1) == '~' && statement.charAt(bracketLocations.get(i).openLocation - 2) != '(') {
                statement = statement.substring(0, bracketLocations.get(i).openLocation - 1) + "(" + statement.substring(bracketLocations.get(i).openLocation - 1,
                        bracketLocations.get(i).closeLocation + 1) + ")" + statement.substring(bracketLocations.get(i).closeLocation + 1);


                bracketLocations = this.bracketsLocations(statement);
                i = 1;
            }
        }


        return statement;
    }


    //Brackets
    public class Brackets {
        //properties
        int openLocation;
        boolean openStatus;
        int closeLocation;
        char charBeyond;
        int difference;
        int depth;
        int maxDepth;

        //constructors
        public Brackets(int openLocation) {
            this.openLocation = openLocation;
            closeLocation = 0;
            openStatus = true;
            charBeyond = '0';
            difference = 0;
            depth = 0;
            maxDepth = 0;
        }

        /**
         * method to convert in string
         * @return the string
         */
        public String toString() {
            return "before  " + charBeyond + " open location: " + openLocation + " close location: " + closeLocation + " open status: " + openStatus +
                    "  difference : " + difference + "  depth : " + depth;
        }

    }

}



