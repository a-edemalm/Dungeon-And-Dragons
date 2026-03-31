package consoleRendering;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class VisualEngine
{

	/*	This class is responsible of all the renders happening to the end user.
	 * 	It renders both the IMAGES, MENUS, DIALOGS and ROOMS. The importance to why I have made it,
	 * 	simply because it can handle all sorts of renders to which results in reduction in
	 * 	dupplication of code. It can also adapt to changes in settings, such as text color,
	 * 	menu color, text speed, menu speed, images speed and exct... It has gone through
	 *  many overhauls, and has become more flexible.
	 *
	 */

	// Foreground, Background
	public static enum POSITION {FG, BG}

	private static enum SIGNTYPE {FIRST, SECOUND, THIRD}


	// 	The list: listWordsDisplay is used at times of rendering to the console.
	private static List<String> listDisplay = new ArrayList<>();


	private String[] signAlt;

	private int signCount;


	private String[] color = new String[2];

	private int[] speed = new int[2];


	private boolean isFormatingList;

	private boolean isTextcentered;

	private boolean isTextWrapping;


	public VisualEngine(String[] newSignAlt, int newSignCount, String[] newColors, int[] newSpeeds,
				  boolean NewIsFormatingList, boolean newIsTextcentered, boolean newIsTextWrapping)
	{
		this.signAlt = newSignAlt;
		this.signCount = newSignCount;

		this.color = newColors;
		this.speed = newSpeeds;

		this.isFormatingList =	NewIsFormatingList;
		this.isTextcentered = newIsTextcentered;
		this.isTextWrapping = newIsTextWrapping;
	}


	public static List<String> getList() { return listDisplay; }

	public static void setList(ArrayList<String> listWordDisplay) { VisualEngine.listDisplay = listWordDisplay; }


	public static List<String> newList() { listDisplay.clear(); return listDisplay; }

	public static void clearList() { listDisplay.clear(); }


	public static String getWord(int newIndex) { return listDisplay.get(newIndex); }

	public static void setWord(int newIndex, String newString) { listDisplay.set(newIndex, newString);}

	public static void addWord(String newWord) { listDisplay.add(newWord); }


	public String getColorValue(POSITION POSITIONIN) { return color[POSITIONIN.ordinal()]; }

	// Setter checks for instance, can convert given color from,
	// either Integer or String
	public void setColorValue(POSITION POSITIONIN, Object newColor)
	{
		Optional<COLORS> colorsAlt = Optional.empty();

		if (newColor instanceof Integer)
		{
			colorsAlt = COLORS.fromInt((Integer) newColor);
		}
		else if (newColor instanceof String)
		{
			colorsAlt = COLORS.fromString((String) newColor);
		}

		colorsAlt.ifPresent(color -> { this.color[POSITIONIN.ordinal()] = color.getValue(); });
	}


	public int getSpeedValue(POSITION POSITIONIN) { return speed[POSITIONIN.ordinal()]; }

	// Setter checks for instance, can convert given speed from,
	// either Integer or String
	public void setSpeedValue(POSITION POSITIONIN, Object newSpeed)
	{
		Optional <SPEEDS> speedsAlt = Optional.empty();

		if (newSpeed instanceof Integer)
		{
			speedsAlt = SPEEDS.fromInt((Integer) newSpeed);
		}
		else if (newSpeed instanceof String)
		{
			speedsAlt = SPEEDS.fromString((String) newSpeed);
		}

		speedsAlt.ifPresent(speed -> { this.speed[POSITIONIN.ordinal()] = speed.getValue(); });
	}


	public void renderer()
	{
		/* I have choosen not to utilize consoleFlush
		 * would need to change some menus, and is highly dependent
		 * if user uses a ANSIC terminal that supports consoleFlush
		 */
//		consoleFlush();

		isFormatingList();

		for (int row = 0; row < listDisplay.size(); row++)
		{
			System.out.println("");

			if(isTextWrapping) {wrapText(row);}

			int signCalc = signCount - listDisplay.get(row).length();

			//	The indexer j in the secound for loop represent to which sign to render
			for (int sign = 0; sign < signCalc; sign++)
			{
				renderingConditions(row, sign, signCalc);
			}
		}

		listDisplay.clear();
	}

	@SuppressWarnings("unused")
	private void consoleFlush()
	{
		System.out.print("\033[H\033[2J");

		System.out.flush();
	}

	/* Formats list, seperates title from the rest of the text
	 * Makes sure to add a extra row at the back, which leaves room
	 * for the outereadge to rendered
	 */
	private void isFormatingList()
	{
		if (isFormatingList)
		{
			if (listDisplay.size() == 1) { listDisplay.add(0,""); listDisplay.add(""); }

			else { listDisplay.add(1,""); listDisplay.add(""); }
		}
	}

	/* Checks weather text is longer than max length of menu,
	 * dialog, if to long it cuts at lastIndexOf empty space
	 * seperates into two substrings, sorts them and put
	 * the longer into next row, and continues to check
	 * untill no row is exceeding characters length.
	 *
	 * Has become vital, for longer strings that would
	 * otherwise, require a lot of code to achieve similair result.
	 */
	private void wrapText(int row)
	{
		String txt = listDisplay.get(row);

		int maxLineLength =  signCount - 4;

		int line = 0;

		while(txt.length() > maxLineLength)
		{
			int splitAt = txt.lastIndexOf(' ', maxLineLength);

			if (splitAt == -1) { splitAt = maxLineLength; }

			String strOne = txt.substring(0, splitAt);
			String strTwo = txt.substring(splitAt);

			wrapTextConditions(row, maxLineLength, line, strOne, strTwo);

			txt = strTwo;

			++line;
		}
	}

	private static void wrapTextConditions(int row, int maxLineLength, int line, String strOne, String strTwo)
	{
		// strFirstCondition
		if(line == 0) { listDisplay.set(row + line, strOne); }

		else { listDisplay.add(row + line, strOne); }


		// strSecoundCondition
		if (strTwo.length() < maxLineLength && listDisplay.size() > (row + line + 2))
		{
			listDisplay.set(row + line + 1, strTwo + listDisplay.get(row + line + 1));
		}
		else if (strTwo.length() < maxLineLength)
		{
			listDisplay.add(row + (line + 1), strTwo);
		}

	}

	private void renderingConditions(int row, int sign, int signCalc)
	{
		//	Prints the words
		// 	IF tempStSigns[j] is equal to zero AND yesNoCenterText is false
		// 	OR IF tempStSigns[j] is equal to the value of tempStSigns[j] DIVIDED by 2 AND yesNoCenterText is true
		if (sign == 1 && !isTextcentered || sign == signCalc / 2 && isTextcentered)
		{
			foregroundPrint(row);
		}
		//	Prints the backgrounds upper and lower edges
		// 	IF tempStSigns[j] is greater than zero AND rows[i] is equal to zero AND tempStSigns[j] is not equal to the last character
		// 	OR IF tempStSigns[j] is greater than zero AND rows[i] is equal to the last word AND tempStSigns[j] is not equal to the last character
		else if (sign > 0 && row == 0  &&  sign != signCalc - 1 || sign > 0 && row == listDisplay.size() - 1 && sign != signCalc - 1 )
		{
			backgroundPrint(sign, false, SIGNTYPE.FIRST);
		}
		//	Prints the backgrounds left and right edges
		// 	IF tempStSigns[j] is equal to zero AND rows[i] is not equal to zero
		// 	OR IF rows[i] is not equal to zero AND tempStSigns[j] is equal to the last character
		else if (sign == 0 && row != 0 || row != 0 && sign == signCalc - 1 )
		{
			backgroundPrint(sign, true, SIGNTYPE.SECOUND);
		}
		//	Prints the background to fill out after the foreground
		else
		{
			backgroundPrint(sign, false, SIGNTYPE.THIRD);
		}
	}

	private void backgroundPrint(int sign, boolean delayOverride, SIGNTYPE SIGNTYPEIN)
	{
		System.out.print(color[POSITION.BG.ordinal()] + signAlt[SIGNTYPEIN.ordinal()]);

		if (!delayOverride) { delay(sign, speed[POSITION.BG.ordinal()]); }
	}

	// 	Method responsible for how the words are rendered, this also includes the foreground if images are to be rendered
	private void foregroundPrint(int row)
	{
		for (int c = 0; c < listDisplay.get(row).length(); c++)
		{
			char chPrint = listDisplay.get(row).charAt(c);

			System.out.print(color[POSITION.FG.ordinal()] + chPrint);

			delay(c, speed[POSITION.FG.ordinal()]);
		}
	}

	//	Method responsible for when delays are duo to be added.
	private static void delay(int sign, int speed)
	{
		// IF tempStSigns[j] is an even number % called "modulo"
		if(sign % 6 == 0)
		{
			try { TimeUnit.MILLISECONDS.sleep(speed); }

			catch (InterruptedException e) { System.out.print("\nTimeUnit at Rendering malfunctions\n\n" + "\"" + e.getMessage() + "\"\n"); }
		}
	}
}
