import java.util.Scanner;

public class ChatboxRunner
{

	/**
	 * Create a chatbox, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Chatbox chatter = new Chatbox();
    
		System.out.println (chatter.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		
		while (!statement.equals("Bye"))
		{
			System.out.println (chatter.getResponse(statement));
			statement = in.nextLine();
		}
	}

}
