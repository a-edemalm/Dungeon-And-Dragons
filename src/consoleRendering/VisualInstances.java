package consoleRendering;

public class VisualInstances
{
	// Initiate Array with signs for Images, and menus
	private final static String[] strSignsImage = {":", ":", ":"};

	private final static String[] strColorImage = {COLORS.RESET.getValue(), COLORS.DARKGREY.getValue()};

	// VALUES FOREGROUND, BACKGROUND
	private final static int[] speedImage = {SPEEDS.MEDIUM.getValue(), SPEEDS.MEDIUM.getValue()};

		// Creates a new instance of the class Engine, EngineTypeImage responsible for the rendering of all Images
		public static VisualEngine image = new VisualEngine(strSignsImage, 81, strColorImage, speedImage, false, true, false);


	// Initiate Array with signs for menus
	private final static String[] strSignsText = {"_", "|", " "};

	private final static String[] strColorMenu = {COLORS.RESET.getValue(), COLORS.RESET.getValue()};

	// VALUES FOREGROUND, BACKGROUND
	private final static int[] speedMenu = {SPEEDS.SLOW.getValue(), SPEEDS.FASTEST.getValue()};

		// Creates a new instance of the class Engine, EngineTypeMenu responsible for the rendering of all Menus
		public static VisualEngine menu = new VisualEngine(strSignsText, 40, strColorMenu, speedMenu, true, false, true);


	// Initiate Array with signs for dialogs
	private final static String[] strSignsDialog = {"_", "|", " "};

	private final static String[] strColorDialog = {COLORS.RESET.getValue(), COLORS.RESET.getValue()};

	// VALUES FOREGROUND, BACKGROUND
	private final static int[] speedDialog = {SPEEDS.SLOW.getValue(), SPEEDS.FAST.getValue()};

		// Creates a new instance of the class Engine, EngineTypeDialog responsible for the rendering of all Dialogs
		public static VisualEngine dialog = new VisualEngine(strSignsDialog, 81, strColorDialog, speedDialog, true, false, true);


	// Initiate Array with signs for rooms
	private final static String[] strSignsRoom = {"_", "|", " "};

	private final static String[] strColorRoom = {COLORS.RESET.getValue(), COLORS.RESET.getValue()};

	// VALUES FOREGROUND, BACKGROUND
	private final static int[] speedRoom = {SPEEDS.OFF.getValue(), SPEEDS.OFF.getValue()};

		// Creates a new instance of the class Engine, EngineTypeDialog responsible for the rendering of all Dialogs
		public static VisualEngine room = new VisualEngine(strSignsRoom, 81, strColorRoom, speedRoom, true, false, true);


	// Initiate Array with signs for rooms
	private final static String[] strSignsBattle = {"_", "|", " "};

	private final static String[] strColorBattle = {COLORS.RESET.getValue(), COLORS.RESET.getValue()};

	// VALUES FOREGROUND, BACKGROUND
	private final static int[] speedBattle = {SPEEDS.OFF.getValue(), SPEEDS.OFF.getValue()};

		// Creates a new instance of the class Engine, EngineTypeDialog responsible for the rendering of all Dialogs
		public static VisualEngine battle = new VisualEngine(strSignsBattle, 81, strColorBattle, speedBattle, true, false, true);


}
