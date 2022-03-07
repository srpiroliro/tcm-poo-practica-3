package Sessio1;

public class MedicamentPindoles {
	private final String nom;
	private final int unitats;
	private int preses = 0;
	
	public MedicamentPindoles(String nom, int unitats){
		this.nom = nom.substring(0,1).toUpperCase() + nom.substring(1).toLowerCase();
		this.unitats = unitats;
		
	}
	
	//GETs
	public int quantesUnitatsQueden() {return unitats-preses;}	
	public void prendrePindola() {if(preses < unitats) preses++;}
	public int getPindolesPreses() {return preses;}
	public String getNom() {return nom;}
	
	//OVERRIDES
	public boolean equals(Object o) { // esta be? problemes amb totalPindolesPreses(String nom)
		MedicamentPindoles altreMedicament;
		
		if(o instanceof MedicamentPindoles) {
			altreMedicament = (MedicamentPindoles) o;
			return this.getNom() == altreMedicament.getNom();
		}
		return false;
		
	}
	public boolean equals(String altre_nom) {
		return this.getNom().equals(altre_nom);
	}
	
	public String toString() {
		return "Nom del medicament " + nom + " amb " + quantesUnitatsQueden() + " pindoles de " + unitats;
	}
}
