
public class Constraint {

	private String contrainte;
	private String OrdInh;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "contrainte : " + contrainte + "\nOrdInh : " + OrdInh;
	}
	
	
	public String getContrainte() {
		return contrainte;
	}
	
	
	
	public void setContrainte(String contrainte) {
		this.contrainte = contrainte;
	}
	public String getOrdInh() {
		return OrdInh;
	}
	public void setOrdInh(String ordInh) {
		OrdInh = ordInh;
	}
	
	
	
}
