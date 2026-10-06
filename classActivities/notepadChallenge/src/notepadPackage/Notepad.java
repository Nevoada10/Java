package notepadPackage;

/** This import allows us to use Swing components
 * What are Swing components? Swing is a set of Java classes that are used to create GUIs (Graphical User Interfaces).
 */
import javax.swing.*;                  // Swing Components

/**
 * This class represents a simple Notepad application. 
 * It extends the JFrame class, which is a Swing component 
 * What does extend mean exactly? It means that this class is a subclass of the JFrame class,
 * that represents a window with a border and a title.
 */
public class Notepad extends JFrame 
{
	
	public Notepad()
	{
		super();
		this.setSize(300, 300);           // Set the frame size
		this.setDefaultCloseOperation(    // Automatically disposes the
			      JFrame.DISPOSE_ON_CLOSE);       // frame when it closes
	}

	public static void main(String[] args)
	{
		Notepad notepad = new Notepad();  // Initialize and instantiate
	                                      // a Notepad object
		notepad.setVisible(true);         // Displaying the form
	}
	
}