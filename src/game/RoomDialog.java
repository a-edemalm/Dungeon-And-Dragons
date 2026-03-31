package game;

public class RoomDialog extends RoomAddition
{
	private String title;

	private String dialog;

	public RoomDialog(boolean newIsPlayed, boolean newIsOnce, String newTitle, String newDialog)
	{
		this.title = newTitle;
		this.dialog = newDialog;
		this.isPlayed = newIsPlayed;
		this.isOnce = newIsOnce;
	}

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }


	public String getDialog() { return dialog; }
}
