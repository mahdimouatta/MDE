package main;
public class InfosLAC extends Object {
	private Object Ord;
	private Object Inh;
	private Object CondOrd = "\\nIf : --";
	private Object CondInh = "\\nIf : --";
	boolean dc = false;

	@Override
	public String toString() {
		if (!dc)
			return "(Ord: " + Ord + "\\n....\\nInh: " + Inh + ")";
		else {
			return "(Ord: " + Ord + CondOrd + "\\n....\\nInh: " + Inh + CondInh +")";
		}
	}

	public Object getCondOrd() {
		return CondOrd;
	}

	public void setCondOrd(Object condOrd) {
		CondOrd = condOrd;
	}

	public Object getCondInh() {
		return CondInh;
	}

	public void setCondInh(Object condInh) {
		CondInh = condInh;
	}

	public boolean isDc() {
		return dc;
	}

	public void setDc(boolean dc) {
		this.dc = dc;
	}

	public InfosLAC() {
		super();
	}

	public InfosLAC(Object ord, Object inh) {
		super();
		Ord = ord;
		Inh = inh;
	}

	public String getOrd() {
		return String.valueOf(Ord);
	}

	public void setOrd(Object ord) {
		Ord = ord;
	}

	public String getInh() {
		return String.valueOf(Inh);
	}

	public void setInh(Object inh) {
		Inh = inh;
	}

}
