public class Grafcet {
	private int key;
	private int x;
	private int y;
	private String text;

	
	
	public Grafcet(int key, int x, int y, String text) {
		super();
		this.key = key;
		this.x = x;
		this.y = y;
		this.text = text;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{\"key\": " + key + ",\"location\": \"" + x + " " + y
				+ "\",\"step\": \"" + key + " \",\"text\": \" " + text + "\"}";
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


	}
