package Sessio1;

public class Malalt {
	private String nom;
	private MedicamentPindoles[] medicaments;
	private int num;
	private final int increment;
	
	public Malalt(String nom, int numMax, int increment) {
		this.nom = nom;
		medicaments = new MedicamentPindoles[numMax];
		this.increment = increment;
	}

	public void comprarMedicamentPindoles(MedicamentPindoles p) { //crec que es podria millorar
		if(num==medicaments.length) ampliar();
		num++;
		medicaments[num] = p;
		ordenar();
	}
	public void comprarMedicamentPindoles(String p, int pindoles) {
		MedicamentPindoles nouMedicament = new MedicamentPindoles(p, pindoles);
		comprarMedicamentPindoles(nouMedicament);
	}
	
	//GETs
	public String getNom() {return nom;}
	public int getNum() {return num;}
	public int getIncrement() {return increment;}
	public MedicamentPindoles getMedicamentPindoles(int quin) {
		if(quin <= num) return medicaments[quin];
		return null;
	}
	public MedicamentPindoles getMedicamentNoBuit() {
		for(int i=0; i<=num; i++) {
			if(medicaments[i].quantesUnitatsQueden()>0) return medicaments[i];
		}
		return null;
	}

	public int totalPindolesQueden() {
		int n=0;
		for(int i=0; i<=num; i++) {
			n += medicaments[i].quantesUnitatsQueden();
		}
		return n;
	}
	public int totalPindolesPreses(String nom) {
		// el nom es unic? si no posar int n=0; .... n+=...
		for(int i=0; i<=num; i++) {
			if(medicaments[i].equals(nom)) return medicaments[i].quantesUnitatsQueden(); //MALAMENT, ha de sumar i retornar al final

			// FALTA. equals esta be? si no esta be, aquesta es la solucio
		}
	}

	public int maximPindoles(){
		// localitzar el medicament del magatzem que té més píndoles a la caixa, en retorna quantes en té
		int n=0;
		for(int i=0; i<=num; i++) {
			if(medicaments[i].quantesUnitatsQueden()>n) n=medicaments[i].quantesUnitatsQueden();
		}
		return n;
	}

	public String numMedicamentsPerQueden() {
		int num_pild=new int[3];
		int queden[]=new int[num]; int quants[]=new int[num]; // num?


	}
	
	//PRIVATEs
	private void ampliar() {
		MedicamentPindoles[] nousMedicaments = new MedicamentPindoles[medicaments.length + increment];
		
		for(int i = 0; i<this.medicaments.length; i++) {
			nousMedicaments[i] = medicaments[i];
		}
		medicaments = nousMedicaments;
	}
	private void ordenar() { //falta fer
		
	}
}
