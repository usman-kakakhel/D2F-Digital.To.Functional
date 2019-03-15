/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;


public class LogicGates
{
  
  /**
   * NOT Gate
   * @param condition boolean
   * @return boolean
   */
  public static boolean not( boolean condition )
  {
    return !condition;
  }
  
  /**
   * OR Gate
   * @param conditions boolean...
   * @return boolean
   */
  public static boolean or(boolean... conditions)
  {
    boolean output;
    output = false;
    for (boolean i : conditions)
    {
      if (i)
      {
        output = true;
      }
    }
    return output;
  }
  
  /**
   * AND Gate
   * @param conditions boolean...
   * @return boolean
   */
  public static boolean and(boolean... conditions)
  {
    boolean output;
    output = true;
    for (boolean i : conditions)
    {
      if (!i)
      {
        output = false;
      }
    }
    return output;
  }
  
  /**
   * NAND Gate
   * @param conditions boolean...
   * @return boolean
   */
  public static boolean nand(boolean... conditions)
  {
    return not ( and ( conditions ) ) ;
  }
  
  /**
   * NOR Gate
   * @param conditions boolean...
   * @return boolean
   */
  public static boolean nor(boolean... conditions)
  {
    return not ( or ( conditions ) ) ;
  }
  
  /**
   * XOR (Exclusive Or) Gate
   * @param conditions boolean...
   * @return boolean
   */
  public static boolean xor(boolean... conditions)
  {
    boolean output;
    int highVolt;
    highVolt = 0;
    for (boolean i : conditions)
    {
      if (i)
      {
        highVolt = highVolt + 1;
      }
    }
    if (highVolt % 2 > 0 )
    {
      return true;
    }
    return false;
  }
  
  /**
   * XNOR Gate
   * @param conditions boolean...
   * @return boolean
   */
  public boolean xnor(boolean... conditions)
  {
    return not ( xor ( conditions ) ) ;
  }  
}
