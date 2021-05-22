package graphicalinterface;
import graphicalinterface.customPanels.StartSessionPage;
/**
 * This is a class for holding an instance of graphical interface. It contains 
 * no graphical interface components, but simply starts a new graphical interface 
 * by initiating a start session page and starting a new session. 
 * 
 * extends Window
 * 
 * @author Ryan
 * @version 1.0
 */
public class GraphicalInterface extends Window implements java.io.Serializable
{
	public main.Session currentSession;
	/**
	 * Constructor takes input main.Session, sets current session to session, 
	 * and calls startgraphicalinterface. 
	 * @param session
	 */
	public GraphicalInterface(main.Session session)
	{
		super("SimplePOS");
		this.currentSession = session;		
		this.startGraphicalInterface();
	}//END Constructors
	/**
	 * This method sets the currentPanel a StartSessionPage, and calls updateframe (super Window method). 
	 */
	public void startGraphicalInterface()
	{
		this.currentPanel = (new StartSessionPage(currentSession, this));
		this.updateFrame();
	}//END startGraphicalInterface
}//END class
