package main;

import consoleRendering.VisualEngine;
import consoleRendering.VisualInstances;
import content.MenuStorage;

public class Runtimes implements Runnable
{
	@Override
	public void run()
	{
		MenuStorage.endProgramScreen(VisualEngine.getList());

		VisualInstances.menu.renderer();
	}

	public static void setRunHook(Runtime run)
	{
		run.addShutdownHook(new Thread(new Runtimes()));
	}
}

