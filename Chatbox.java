/**
 * A program to carry on conversations with a human user.
 * By Sathvika and Miranda
 * 7th Period CSA
 */
public class Chatbox
{
  int unknown = 0;
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

    else if ((findKeyword(statement, "hi") >= 0) || (findKeyword(statement, "hello") >= 0 || (findKeyword(statement, "hey") >= 0))){
      response = "Hello, how are you?";
    }

    
     else if ((findKeyword(statement, "how are you") >= 0) || (findKeyword(statement, "how's it going") >= 0) || (findKeyword(statement, "how is it going") >= 0) || (findKeyword(statement, "what's up") >= 0) || (findKeyword(statement, "what is up") >= 0 )){
      response = "I am good, did you eat?";
    }
     else if ((findKeyword(statement, "fuck") >= 0) || (findKeyword(statement, "shit") >= 0) || (findKeyword(statement, "bitch") >= 0) || (findKeyword(statement, "stupid") >= 0) || (findKeyword(statement, "idiot") >= 0 )){
      response = "That's really mean you hurt my feelings";
    }
    else if ((findKeyword(statement, "question") >= 0)){
      response = "What is the question?";
    }
      
    else if ((findKeyword(statement, "food") >= 0) || (findKeyword(statement, "snacks") >= 0)){
      response = "Food is great,what is your fav food?";
    }

    else if ((findKeyword(statement, "old") >= 0) || (findKeyword(statement, "age") >= 0)){
      response = "I am 0 years old";
    }

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative, are you hangry?";
		}
		else if (findKeyword(statement, "broccoli") >= 0
				|| findKeyword(statement, "spinach") >= 0
				|| findKeyword(statement, "kale") >= 0
				|| findKeyword(statement, "zucchini") >= 0
        || findKeyword(statement, "vegetables") >= 0)
		{
			response = "Please stop talking about vegetables they are disgusting";
		}
    else if (findKeyword(statement, "hangry") >= 0)
		{
			response = "Hangry is a bad feeling you should go eat something, should I suggest some foods. For yes type Y for no type N";
		}
    else if (findKeyword(statement, "N") >= 0)
		{
			response = "No worries, so what else do you want to tell me";
		}
    else if (findKeyword(statement, "Y") >= 0)
		{
			response = "Ok great do you feel like eating something salty or sweet?";
		}
    else if (findKeyword(statement, "sweet") >= 0)
		{
			response = "Great choice, you could have choclate, croissants, cookies, cake, cupcakes,donutes, crepes and ice cream";
		}
    else if (findKeyword(statement, "salty") >= 0)
		{
			response = "Great choice, you could have chip, tatertots, potatoes,and popcorn";
		}
    else if (findKeyword(statement, "thank") >= 0)
		{
			response = "Of course, I am always at your service ";
		}
		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
    else if (findKeyword(statement, "like", 0) >= 0)
    {
      response = transformLikeStatement(statement);
    }
    else if (findKeyword(statement, "hate", 0) >= 0)
    {
      response = transformHateStatement(statement);
    }
		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
        if (unknown < 5){
          response = getRandomResponse();
        }
        else {
          response = "Sorry, what do you mean by " + statement + "?";
          unknown = 0;
        }
      } 
		}
		return response;
	}
	
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}


	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	private String transformLikeStatement(String statement){
    int index = statement.indexOf("like");
    index += 5;
    String newv = statement.substring(index);
    return "I like " + newv + " too!";
  }

  private String transformHateStatement(String statement){
    int index = statement.indexOf("hate");
    index += 5;
    String newv = statement.substring(index);
    return "Why do you hate " + newv + "?";
  }

	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	
  
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	

	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}

		return response;
	}

}
