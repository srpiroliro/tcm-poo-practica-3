package Sessio2;

public class MedicamentPindoles {
	private final String nom;
	private final int unitats;
	private int preses = 0;
	
	public MedicamentPindoles(String nom, int unitats){
		if(nom==null || nom=="") throw new IllegalArgumentException("Argument nom illegal!");
		if(unitats<=0) throw new IllegalArgumentException("Argument unitats illegal! (ha de ser major o igual a 0)");
		
		this.nom = nom.substring(0,1).toUpperCase()+nom.substring(1).toLowerCase();
		this.unitats = unitats;
	}
	
	// GETs
	public int quantesUnitatsQueden() {return unitats-preses;}	
	public void prendrePindola() throws ExceptionMedicament {
		if(preses < unitats) preses++;
		else throw new ExceptionMedicament("No queden més píndoles!");
	}
	public int getPindolesPreses() {return preses;}
	public String getNom() {return nom;}
	
	// OVERWRITEs
	public boolean equals(Object o) { // cal aquesta? potser amb equals(String nom) ja es suficient
		MedicamentPindoles altreMedicament;
		
		if(o instanceof MedicamentPindoles) {
			altreMedicament = (MedicamentPindoles) o;
			return  this.getNom().equals(altreMedicament.getNom());
		}
		return false;
	}
	public boolean equals(String altre_nom) {
		return this.getNom().equals(altre_nom);
	}
	public String toString() {
		return "Nom del medicament " + nom + " amb " + quantesUnitatsQueden() + " pindoles de " + unitats;
	}

	
	public int compareTo(Object o){
		MedicamentPindoles altreMedicament;
		
		if(o instanceof MedicamentPindoles) {
			altreMedicament = (MedicamentPindoles) o;
			return this.quantesUnitatsQueden()-altreMedicament.quantesUnitatsQueden();
		}
		
		throw new ClassCastException("Tipus incorrecte!");
	}
}
