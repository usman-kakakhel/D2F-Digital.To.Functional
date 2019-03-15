/**
 * @author Mian Usman Naeem Kakakhel
 * last edit: 10 May 2018
 */
package application;
import java.util.ArrayList;

//InputPSOP
public class InputPSOP
{
  //properties
  ArrayList<InputProducts> inputsSOP;
  String eq;
  
  //constructors
  public InputPSOP(String eq)
  {
    inputsSOP = new ArrayList<InputProducts>();
    this.eq = eq;
    fillList();
  }
  
  /**
   * Gives back the ArrayList of the inputsSOP.
   * @return ArrayList inputsSOP.
   */
  public ArrayList<InputProducts> getInputsSOP()
  {
    return inputsSOP;
  }
  
  /**
   * Fills Array List inputsSOP with parts of the boolean algebra statment separated by + since it is sum of products.
   */
  public void fillList()
  {
    ArrayList<Integer> times = new ArrayList<Integer>();
    
    for (int i = 0; i < eq.length(); i++)
    {
      if (eq.charAt(i) == '+')
        times.add(i);
    } 
    
    if (times.size() == 0)
      inputsSOP.add(new InputProducts(eq));
    else
    {
      inputsSOP.add(new InputProducts(eq.substring(1, times.get(0))));
      
      for (int j = 1; j < times.size(); j++)
      {
        inputsSOP.add(new InputProducts(eq.substring(times.get(j - 1) + 1, times.get(j))));
      }
      
      inputsSOP.add(new InputProducts(eq.substring(times.get(times.size() - 1), eq.length() - 1)));
    }
    
  }
  
  /**
   * Returns an arraylist of the types class which has the number of times which each input and its inverse has appeared in the SOP boolean algebra statement.
   * @return ArrayList with the number of occurences of the inputs.
   */
  public ArrayList<Types> getRepeatTimes()
  {
    ArrayList<Types> times = new ArrayList<Types>();
    BooleanAlgebra inputFinder = new BooleanAlgebra(eq);
    ArrayList<String> exactInputs = inputFinder.findInputsInExpression(eq);
    
    for (int k = 0; k < exactInputs.size(); k++)
    {
      times.add(new Types(exactInputs.get(k)));
    }
    
    for (int j = 0; j < inputsSOP.get(0).getSize(); j++)
    {
      for (int i = 0; i < inputsSOP.size(); i++)
      {
        if (inputsSOP.get(i).getInput(j).length() == 1)
          times.get(j).addLocTheNameOne(i);
        else if (inputsSOP.get(i).getInput(j).length() == 2)
          times.get(j).addLocTheNameNotOne(i);
      }
    }
    
    return times;
  }
  
  /**
   * To string of this class.
   * @return String is the representation of this class.
   */
  public String toString()
  {
    return inputsSOP + "";
  }
  
  //class types 
  public class Types
  {
    //properties
    ArrayList<Integer> theNameOne;
    ArrayList<Integer> theNameNotOne;
    String name;
    
    //constructors
    public Types(String name)
    {
      theNameOne = new ArrayList<Integer>();
      theNameNotOne = new ArrayList<Integer>();
      this.name = name;
    }
    
    /**
     * adds the locations of the simple input in question to an array list.
     * @param loc is the location at which the simple input in question is present.
     */
    public void addLocTheNameOne(int loc)
    {
      theNameOne.add(loc);
    }
    
    /**
     * adds the locations of the inverse input in question to an array list.
     * @param loc is the location at which the inverse input in question is present.
     */
    public void addLocTheNameNotOne(int loc)
    {
      theNameNotOne.add(loc);
    }
    
    /**
     * Gives the name of the input whose locations are being stored.
     * @return String is the name of the input.
     */
    public String getName()
    {
      return name;
    }
    
    /**
     * Gives back the ArrayList of the locations of the simple input in question.
     * @return ArrayList with the locations of the simple input in question.
     */
    public ArrayList<Integer> getTheNameOne()
    {
      return theNameOne;
    }
    
    /**
     * Gives back the ArrayList of the locations of the inverse input in question.
     * @return ArrayList with the locations of the inverse input in question.
     */
    public ArrayList<Integer> getTheNameNotOne()
    {
      return theNameNotOne;
    }
    
    /**
     * To string of this class.
     * @return String is the representation of this class.
     */
    public String toString()
    {
      return name + "  " + theNameOne + "  " + theNameNotOne;
    }
    
  }
  
  
  //Class InputProducts
  public class InputProducts
  {
    //properties
    ArrayList<String> inputs;
    String eq;
    
    //constructors
    public InputProducts(String eq)
    {
      inputs = new ArrayList<String>();
      this.eq = eq;
      fillArray();
    }
    
    /**
     * Gives back the ArrayList of the inputs.
     * @return ArrayList inputs.
     */
    public ArrayList<String> getInputs()
    {
      return inputs;
    }
    
    /**
     * Returns int which is the number of total distinct inputs from which the circuit is made
     * @return int which is the number of total distinct inputs from which the circuit is made.
     */
    public int getSize()
    {
      return inputs.size();
    }
    
    /**
     * In the arrayList, the Products part is stored without dots as multiply and this class is part of the bigger SOP statement so only inputs from + to + are present.
     */
    public void fillArray()
    {
      for (int i = 0; i < eq.length(); i++)
      {
        if ((eq.charAt(i) >= 'A') && (eq.charAt(i) <= 'Z'))
        {
          if (i != 0)
          {
            if (eq.charAt(i - 1) == '~')
              inputs.add(eq.substring(i - 1, i + 1));
            else
              inputs.add(eq.substring(i, i + 1));
          }
          else
            inputs.add(eq.substring(i, i + 1));
        }
      }
    }
    
    /**
     * Gives the the input in the products part of the SOP on the given location.
     * @param loc is the location at which the required input is stored.
     * @return String is the input at a certain given location.
     */
    public String getInput(int loc)
    {
      return inputs.get(loc);
    }
    
    /**
     * To string of this class.
     * @return String is the representation of this class.
     */
    public String toString()
    {
      return inputs + "";
    }
    
  }
}