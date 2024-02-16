import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class NBody {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		double big_t = Double.parseDouble(args[0]); // enter a Big t
		double delta_t = Double.parseDouble(args[1]); // enter a delta t

		String resourceFolder = "resources/";
		String fileName = resourceFolder + args[2]; // enter a text file
		FileInputStream fileInputStream = new FileInputStream(fileName);
		System.setIn(fileInputStream);

		// Use StdIn to read from the file.
		int numBodies = StdIn.readInt();
		double universeRadius = StdIn.readDouble();

		// Print out the values read in (remove all this from your final version)
		StdOut.println("big_t          = " + big_t);
		StdOut.println("delta_t        = " + delta_t);
		StdOut.println("numBodies      = " + numBodies);
		StdOut.println("universeRadius = " + universeRadius);

		// declare an array for initial X position
		double[] posX = new double[numBodies];
		// declare an array for initial Y position
		double[] posY = new double[numBodies];
		// declare an array for initial X velocity
		double[] velX = new double[numBodies];
		// declare an array for initial Y velocity
		double[] velY = new double[numBodies];
		// declare an array for mass
		double[] mass = new double[numBodies];
		// declare an array for pictures (Not double)
		String[] picture = new String[numBodies];
		
		for (int i = 0; i < numBodies; i++) {
			// put initial X position in body array
			posX[i] = StdIn.readDouble();
			// put initial Y position in body array
			posY[i] = StdIn.readDouble();
			// put X velocity in body array
			velX[i] = StdIn.readDouble();
			// put Y velocity in body array
			velY[i] = StdIn.readDouble();
			// put mass in body array
			mass[i] = StdIn.readDouble();
			// put picture in body array
			picture[i] = StdIn.readString();
		}
		StdDraw.setXscale(-universeRadius, universeRadius);
		StdDraw.setYscale(-universeRadius, universeRadius);
		StdDraw.picture(0, 0, "resources/starfield.jpg");
		// declare forceX array for each of the body
		double[] forceX = new double[numBodies];
		// declare forceY array for each of the body
		double[] forceY = new double[numBodies];
		double G = (6.67e-11); // Gravitational Constant
		double time_step = 0;// Set time at 0
		double Fx;
		double Fy;
		while (time_step < big_t) {// start while
			/* ONE ITERATION */

			for (int i = 0; i < numBodies; i++) {// start i
				Fx=0;
				Fy=0;
				for (int j = 0; j < numBodies; j++) { // start j
					if (i != j) {// start if
						// delta X
						double deltaX = posX[j] - posX[i];
						// delta Y
						double deltaY = posY[j] - posY[i];
						// calculate R
						double R = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
						// calculate Force
						double Force = ((G * (mass[i]* mass[j])) /(R * R));
						// calculate forceX, store them in single array
					Fx += Force * (deltaX / R);
						// calculate forceY, store them in single array
				    Fy += Force * (deltaY / R);
					} // end if
					forceX[i] = Fx;
					forceY[i] = Fy;
				} // end j
			} // end i
			for (int i = 0; i < numBodies; i++) {// start i
				// single body operation
				// calculate accelerationX for each of the body given forceY
				double accelX = (forceX[i] / mass[i]);
				// calculate accelerationY for each of the body given forceY
				double accelY = (forceY[i] / mass[i]);
				// calculate new velocity X for each of the body, update velX[i]
				velX[i] = (velX[i] + (delta_t * accelX));
				// calculate new velocity Y for each of the body, update velY[i]
				velY[i] = (velY[i] + (delta_t * accelY));
				// calculate new position X for each of the body, update posX[i]
				posX[i] = (posX[i] + (delta_t * velX[i]));
				// calculate new position Y for each of the body, update posY[i]
				posY[i] = (posY[i] + (delta_t * velY[i]));
			} // end i
				// we now have new position X and Y for each of the body
			StdDraw.picture(0, 0, "resources/starfield.jpg");
			for (int j = 0; j < numBodies; j++) {// start j
				// for each of the body, given posX[j], posY[j], draw that body
				StdDraw.picture(posX[j], posY[j], "resources/" + picture[j]);
				// stdDraw.picture(posX[j], posY[j], picture[j])
			} // end j
			StdDraw.show(3);

			time_step = time_step + delta_t;

			} // end while

	}// end main
}