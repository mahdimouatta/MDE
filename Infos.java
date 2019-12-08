
public class Infos {
	private InfosLAC inf1;
	private InfosLAC inf2;
	private Object node1 = "";
	private Object node2 = "";
	private String direction;
	private String liaison; // the text on the link between the two nodes
	private boolean start = false;

	@Override
	public String toString() {

		String a = inf1 == null ? node1.toString() : inf1.toString();
		String b = (inf2 == null ? node2.toString() : inf2.toString());
		return a + " --> " + b + " : " + direction + liaison;
		// + "  " + start;
	}

	public Infos(String node1, InfosLAC inf2, String direction, String liaison) {
		super();
		this.node1 = node1;
		this.inf2 = inf2;
		this.direction = direction;
		this.liaison = liaison;
	}

	public Infos() {
		super();
	}

	public Infos(InfosLAC inf1, String node2, String direction, String liaison) {
		super();
		this.node2 = node2;
		this.inf1 = inf1;
		this.direction = direction;
		this.liaison = liaison;
	}

	public Infos(InfosLAC inf1, InfosLAC inf2, String direction, String liaison) {
		super();
		this.inf2 = inf2;
		this.inf1 = inf1;
		this.direction = direction;
		this.liaison = liaison;
	}

	public Infos(String node1, String node2, String direction, String liaison) {
		super();
		this.node1 = node1;
		this.node2 = node2;
		this.direction = direction;
		this.liaison = liaison;
	}

	public Object getNode1() {
		return String.valueOf(node1);
	}

	public void setNode1(Object node1) {
		this.node1 = node1;
	}

	public Object getNode2() {
		return String.valueOf(node2);
	}

	public void setNode2(Object node2) {
		this.node2 = node2;
	}

	public String getLiaison() {
		return liaison;
	}

	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public InfosLAC getInf2() {
		return inf2;
	}

	public void setInf2(InfosLAC inf2) {
		this.inf2 = inf2;
	}

	public InfosLAC getInf1() {
		return inf1;
	}

	public void setInf1(InfosLAC inf1) {
		this.inf1 = inf1;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

}
