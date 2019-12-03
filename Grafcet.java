public class Grafcet {
	private int key;
	private int x;
	private int y;
	private String text;
	private Link link;

	
	
	public Grafcet(int key, int x, int y, String text, Link link) {
		super();
		this.key = key;
		this.x = x;
		this.y = y;
		this.text = text;
		this.link = link = new Link(key);
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

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	private class Link {
		private int from;
		private int to;
		private String text;
		private boolean skip = false;

		
		public Link(int from) {
			this.from = from;
		}
		
		public Link(int from, int to, String text, boolean skip) {
			this.from = from;
			this.to = to;
			this.text = text;
			this.skip = skip;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			if(!skip) return "{\"from\": " + from + ",\"to\": " + to + ",\"text\": \" "
					+ text + "\"}";
			else 
			return "{\"from\": " + from + ",\"to\": " + to + ",\"text\": \" "
					+ text + "\",\"category\": \"Skip\"}";
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public boolean isSkip() {
			return skip;
		}

		public void setSkip(boolean skip) {
			skip = skip;
		}

	}
}
