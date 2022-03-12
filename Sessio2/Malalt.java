package Sessio2;

import java.util.Arrays;

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
	
	public int totalPindolesQueden() {
		int n=0;
		for(int i=0; i<num; i++) {
			n += medicaments[i].quantesUnitatsQueden();
		}
		return n;
	}

	public int totalPindolesPreses(String nom) {
		for(int i=0; i<num; i++) {
			if(medicaments[i].equals(nom)) return medicaments[i].quantesUnitatsQueden();
		}
		return 0;
	}

	public int maximPindoles(){
		int n=0;
		for(int i=0; i<num; i++) {
			if(medicaments[i].quantesUnitatsQueden()>n) 
				n=medicaments[i].quantesUnitatsQueden();
		}
		return n;
	}
	
	public MedicamentPindoles medicamentMenys(){
		int n=maximPindoles();
		for(int i=0; i<num; i++) {
			if(medicaments[i].quantesUnitatsQueden()<medicaments[n].quantesUnitatsQueden()) 
				n=i;
			else if(medicaments[i].quantesUnitatsQueden()==medicaments[n].quantesUnitatsQueden()) {
				if(medicaments[i].getNom().compareTo(medicaments[n].getNom())==-1)
					n=i;
			}
		}
		return medicaments[n];
	}

	public String numMedicamentsPerQueden() {
		int queden[]=new int[num]; int quants[]=new int[num];
		int quantes=0; // posicions plenes a queden[]

		for(int i=0; i<num; i++) {
			int x=troba(medicaments[i], queden, quantes); int posicio=x;
			
			if(x==-1){
				quants[quantes]=medicaments[i].quantesUnitatsQueden(); 
				posicio=quantes; quantes++;
			}
			quants[posicio]++;
		}
		
		return crear(queden,quants,quantes);
	}

	public int eliminarMedicamentPindoles() {
		int quants=0; int eliminats=0;
		for(int i=0; i<num; i++) {
			if(medicaments[i].quantesUnitatsQueden()==0){
				medicaments[i]=null; eliminats++;
			} else {
				medicaments[quants]=medicaments[i]; quants++;
			}
		}
		
		return eliminats;
	}

	public MedicamentPindoles[] donaMedicamentsBuits() {
		// podria ser mes eficient?

		MedicamentPindoles[] buits_tmp=new MedicamentPindoles[num]; int cnt=0;
		for (MedicamentPindoles i : medicaments){
			if (i==null) continue; // legal? si no ho es, negar el if i que envolti tot.
			if (i.quantesUnitatsQueden()==0){
				buits_tmp[cnt]=i; cnt++;
			}
		}

		MedicamentPindoles[] buits=new MedicamentPindoles[cnt];
		for (int x=0;x<cnt;x++) buits[x]=buits_tmp[x];	
		return buits;
	}

	public void llistatOrdenatAscendent() {
		MedicamentPindoles[] aOrdenar = new MedicamentPindoles[num];
		copiar(medicaments, aOrdenar, num);
		Bombolla(aOrdenar, num);
		for (MedicamentPindoles i: aOrdenar) {
			System.out.println(i.toString());
		}
	}

	public void llistatOrdenatDescendent() {
		MedicamentPindoles[] aOrdenar = new MedicamentPindoles[num];
		copiar(medicaments, aOrdenar, num);
		Arrays.sort(aOrdenar);
		for (MedicamentPindoles i: aOrdenar) {
			System.out.println(i.toString());
		}
	}

	// OVERWRITEs
	public String toString() {
		String msg="";
		for (int x=0;x<num;x++)
			msg+=(x+1)+". "+medicaments[x].toString()+"\n";

		return msg;
	}
	public boolean equals(Malalt malaltB){
		// si tenen la mateixa quantitat de pindoles 
		String[] llista_strMalaltA=numMedicamentsPerQueden().split("-");
		String[] llista_strMalaltB=malaltB.numMedicamentsPerQueden().split("-");

		Arrays.sort(llista_strMalaltA); Arrays.sort(llista_strMalaltB);

		String strMalaltA=Arrays.toString(llista_strMalaltA); 
		String strMalaltB=Arrays.toString(llista_strMalaltB);


		return strMalaltA.equals(strMalaltB);
	}
	public int compareTo(Object o) {
		Malalt altreMalalt;
		
		if(o instanceof Malalt) {
			altreMalalt = (Malalt) o;
			if(this.totalPindolesQueden()<altreMalalt.totalPindolesQueden()) return -1;
			else if(this.totalPindolesQueden()==altreMalalt.totalPindolesQueden()) return 0;
			else return 1;
		}
		return -2;
	}


	//GETs
	public String getNom() {return nom;}
	public int getNum() {return num;}
	public int getIncrement() {return increment;}
	public MedicamentPindoles getMedicamentPindoles(int quin) {
		if(quin < num) return medicaments[quin];
		return null;
	}
	public MedicamentPindoles getMedicamentNoBuit() {
		for(int i=0; i<num; i++) {
			if(medicaments[i].quantesUnitatsQueden()>0) return medicaments[i];
		}
		return null;
	}

	//PRIVATEs
	private void ampliar() {
		MedicamentPindoles[] nousMedicaments = new MedicamentPindoles[medicaments.length + increment];
		for(int i = 0; i<this.medicaments.length; i++) nousMedicaments[i] = medicaments[i];
		medicaments = nousMedicaments;
	}
	private void ordenar() {
		// nom (AA 1st), pindoles quan es compra(> 1st), pindoles presses (> 1st)
		for(int x=1; x<medicaments.length; x++){
			continue;
		}
	}
	private void Bombolla(MedicamentPindoles[] aOrdenar, int n) {
		MedicamentPindoles aux;
		for(int i=0; i<n-1; i++) {
			for(int j=n-1; j>=i; j--) {
				if(aOrdenar[j].quantesUnitatsQueden() < aOrdenar[j-1].quantesUnitatsQueden()) {
					aux = aOrdenar[j];
					aOrdenar[j] = aOrdenar[j-1];
					aOrdenar[j-1] = aux;
				}
			}
		}
	}
	private void copiar(MedicamentPindoles[] origen, MedicamentPindoles[] desti, int n) {
		for (int i=0; i<n; i++) {
			desti[i] = origen[i];
		}
	}


	// cal que siguin statics?
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