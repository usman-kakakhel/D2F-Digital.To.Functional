/**
 * @author Talal Ahmad, Joel Graff, Muhammad Bilal, Mian Usman Naeem Kakakhel, Balaj Saleem
 * last edit: 10 May 2018
 */
package application;
import java.util.*;
import java.io.*;


//Truth tables
public class TruthTable
{
  //properties
  ArrayList<TruthTableColumn> myTable;
  
  //constructors
  public TruthTable()
  {
    myTable = new ArrayList<TruthTableColumn>();
  }
  
  //methods
  
  /**
   * Adds any truthtable column given to this mehod to myTable array list
   * used when filling the table to add columns. 
   * @param  a is the truth table column to be added.
   */
  private void add(TruthTableColumn a)
  {
    myTable.add(a);
  }
  
  
  /**
   * Creates truth table from a given boolean algebra expression.
   * @param equate which has the algebra expression from which a truth  table is to be generated
   */
  public void createTable(String equate)
  {
    String myEquation = equate;
    BooleanAlgebra eq = new BooleanAlgebra(myEquation);
    ArrayList<String> inputNames = new ArrayList<String>();
    
    myEquation = eq.computeSimplifiedForm();
    if ((!myEquation.equals("")) && (!myEquation.equals("1")) && (!myEquation.equals("0")))
    {
      inputNames = eq.findInputsInExpression(myEquation); 
      
      for (int i = 0; i < inputNames.size(); i++)
      {
        add(new TruthTableColumn(inputNames.get(i) , i, inputNames.size()));
      }
      
      calculateOutput(myEquation);
    }
    else 
    {
      add(new TruthTableColumn(myEquation , 0, 1));
      calculateOutput(myEquation);
    }
  }
  
  
  /**
   * Puts the truth table respective values of the inputs in the statement to fill the respective row of output slot in the truth table. 
   * @param  equation is the truth table column to be added.
   */
  private void calculateOutput(String equation)
  {
    String myEquation = equation;
    String columnName;
    this.add(new TruthTableColumn("?", myTable.get(0).getSize())); // the output column
    BooleanAlgebra eq;
    
    for (int k = 0; k < myTable.get(0).getSize(); k++)
    {
      myEquation = equation;
      for (int i = 0; i < myTable.size(); i++)
      {
        columnName = myTable.get(i).getName();
        
        if (columnName.length() == 1)
        {
          for (int j = 0; j < myEquation.length(); j++)
          {
            if (columnName.equals(myEquation.substring(j, j + 1)))
            {
              myEquation = myEquation.substring(0, j) + myTable.get(i).getValue(k) + myEquation.substring(j + 1);
            }
          }
        }
      }
      
      eq = new BooleanAlgebra(myEquation);
      myTable.get(myTable.size() - 1).addValue(Integer.parseInt(eq.computeSimplifiedForm()));
    }
    
  }
  
  
  /**
   * To string of this class
   * @return String of the class properties data.
   */
  public String toString()
  {
    String output = "";
    
    for (TruthTableColumn a : myTable)
    {
      output = output + a.toString() + "\n";
    }
    return output;
  }
  
  
  /**
   * In the truth tables there are some outputs with 1 output, the inputs of these outputs are turned into a boolean algebra statement.
   * @return String is the boolean algebra statement created by the truth tables.
   */
  public String makeStatement()
  {
    String output = "(";
    
    for (int j = 0; j < myTable.size(); j++)
    {
      if (myTable.get(j).getColumnNumber() == -1)
      {
        for (int z = 0; z < myTable.get(j).getSize(); z++)
        {
          if (myTable.get(j).getValue(z) == 1)
          {
            output = output + "(";
            for (int i = 0; i < myTable.size(); i++)
            {
              if (myTable.get(i).getColumnNumber() != -1)
              {
                
                if (myTable.get(i).getValue(z) == 0)
                  output = output + "~" +  myTable.get(i).getName();
                else if (myTable.get(i).getValue(z) == 1)
                  output = output + myTable.get(i).getName();
                
                output = output + ".";
              }
            }
            output = output.substring(0, output.length() - 1);
            output = output + ")+";
          }
        }
      }
    }
    if (output.substring(output.length() - 1).equals(".") || output.substring(output.length() - 1).equals("+") )
      output = output.substring(0, output.length() - 1);
    output = output + ")";
    return output;
  }

  public int getColumnSize() {
    return myTable.get(0).getSize() + 1;
  }

  public int getRowSize() {
    return myTable.size();
  }


  //TruthTable Column
  public class TruthTableColumn
  {
    //properties
    String name;
    ArrayList<Integer> myTableColumn;
    int columnNumber;
    int sizeOfColumn;
    
    //constructors
    //This will create a simple column and fill it with input vaues as a simple truth table column is.
    public TruthTableColumn(String name, int columnNumber, int totalColumnNumber)
    {
      this.name = name;
      this.columnNumber = columnNumber;
      myTableColumn = new ArrayList<Integer>();
      this.sizeOfColumn = (int)( Math.pow(2 , totalColumnNumber));
      enterValues();
    }
    //This is used for the output column as it does not require filling
    public TruthTableColumn(String name, int sizeOfColumn)
    {
      this.name = name;
      this.columnNumber = -1;
      this.sizeOfColumn = sizeOfColumn;
      myTableColumn = new ArrayList<Integer>();
    }
    
    //methods
    
    /**
     * Fills the respective truth table column with its own values according to its column number.
     */
    public void enterValues()
    {
      int input = 1;
      
      for (int i = 0; i < sizeOfColumn; i++) 
      {
        if (i % (int)Math.pow(2 , columnNumber) == 0) // Math.pow(2 , columnNumber) is the number on which 0 and 1 is alternate
        {
          if (input == 0)
            input = 1;
          else if (input == 1)
            input = 0;
        } 
        myTableColumn.add(input);
      }
    }
    
    /**
     * fills values in the truth table column.
     * @param value is the value of the input that has to be added to the array list of inputs.
     */
    public void addValue(int value)
    {
      myTableColumn.add(value);
    }
    
    /**
     * Returns the size of the truth table column.
     * @return int is the size of the truth table column.
     */
    public int getSize()
    {
      return sizeOfColumn;
    }
    
    /**
     * Returns the whole truth table column.
     * @return ArrayList which is the whole truth table column.
     */
    public ArrayList<Integer> getColumn()
    {
      return myTableColumn;
    }
    
    /**
     * Returns the heading of the inputs which belong to this truth table column.
     * @return String is the name of this truth table column.
     */
    public String getName()
    {
      return name;
    }
    
    /**
     * Returns the value of the input at the given location in the truth table column.
     * @param position is the position of the column at which the input value is required
     * @return int is the value of the input required.
     */
    public int getValue(int position)
    {
      return myTableColumn.get(position);
    }
    
    /**
     * The column number of this column in the whole truth table is given.
     * @return int is the required column number.
     */
    public int getColumnNumber()
    {
      return columnNumber;
    }
    
    /**
     * To string of this class.
     * @return String is the representation of this column.
     */
    public String toString()
    {
      return  "  " + name + " | " + myTableColumn;
    }

  }
  
  
}



