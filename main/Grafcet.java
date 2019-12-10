package main;
public class Grafcet {
	private int key;
	private int x;
	private int y;
	private String text;
	private boolean Start;

	public Grafcet(int key, int x, int y, String text, boolean start) {
		super();
		this.key = key;
		this.x = x;
		this.y = y;
		this.text = text;
		this.Start = start;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (!isStart()) {
			return "{\"key\": " + key + ",\"location\": \"" + x + " " + y
					+ "\",\"step\": \"" + key + " \",\"text\": \" " + text
					+ "\"}";

		} else {
			return "{\"key\": " + key
					+ ", \"category\":\"Start\", \"location\": \"" + x + " "
					+ y + "\",\"step\": \"" + key + " \",\"text\": \" " + text
					+ "\"}";

		}
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isStart() {
		return Start;
	}

	public void setStart(boolean start) {
		Start = start;
	}

}
