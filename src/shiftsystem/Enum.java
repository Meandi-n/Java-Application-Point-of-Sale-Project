package shiftsystem;
/**
 * This holds the enums used by the application. 
 * So far only PAYMENTTYPE is used.
 * 
 * @author Ryan
 * @Version 1.0
 */
public class Enum implements java.io.Serializable
{
	/**
	 * Holds payment type as Cash, EFTPOS, CustomerCredit.
	 * @author Ryan
	 */
	public enum PAYMENTTYPE
	{
		Cash, 
		EFTPOS, 
		CustomerCredit
	}//END PAYMENTTYPE
}//END ENUM
