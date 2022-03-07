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
		if(num==medicaments.length) 
			ampliar();
		num++; medicaments[num] = p;

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
			if(medicaments[i].equals(nom)) return medicaments[i].quantesUnitatsQueden();

			// FALTA. equals esta be? si no esta be, aquesta es la solucio
		}
	}

	public int maximPindoles(){
		int n=0;
		for(int i=0; i<=num; i++) {
			if(medicaments[i].quantesUnitatsQueden()>n) n=medicaments[i].quantesUnitatsQueden();
		}
		return n;
	}

	public String numMedicamentsPerQueden() {
		int queden[]=new int[num]; int quants[]=new int[num];
		int quantes=0; // posicions plenes a queden[]

		for(int i=0; i<=num; i++) {
			int x=troba(medicaments[i], queden, quantes); 
			if (x>0) quants[x]++;
			} else {
				quants[i]=medicaments[i].quantesUnitatsQueden(); quants[i]++;
			}
		}
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


	private static int troba(MedicamentPindoles caixa, int[] queden, int quants) {
		int quantitat=caixa.quantesUnitatsQueden();
		for(int i=0; i<quants; i++) {
			if (queden[i]==quantitat)
				return i;
		}
		return -1;
	}

	private static String crear(int[] queden, int[] quants, int quantes) {
		String msg="";
		for(int i=0; i<quantes; i++) 
			msg+="Amb "+queden[i]+" píndoles queden "+quants[i]+" medicaments - ";

		return msg;
	}
}
