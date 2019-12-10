package main;

public class Link {
		private int from;
		private int to;
		private String text;
		private boolean skip = false;

		
		public Link(int from) {
			this.from = from;
		}
		
		public Link(int from, int to, String text) {
			this.from = from;
			this.to = to;
			this.text = text;
			this.skip = false;
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
			this.skip = skip;
		}

	}
