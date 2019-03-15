/**
 * @author Joel Graff
 * last edit: 10 May 2018
 */
package application;

import java.io.Serializable;

import javafx.geometry.Point2D;

public class Point2dSerial extends Point2D implements Serializable {


	/**
	 * method of x, y location
	 * @param x X co ordinate
	 * @param y Y co ordinate
	 */
	public Point2dSerial(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
}
