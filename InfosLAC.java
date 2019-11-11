
public class InfosLAC extends Object {
	private Object Ord;
	private Object Inh;
	
	
	
	@Override
	public String toString() {
		return "(Ord: " + Ord + "\\n....\\nInh: " + Inh + ")";
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
