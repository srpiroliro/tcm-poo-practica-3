package Sessio1;

import java.util.Arrays;

public class Malalt {
	private String nom;
	private MedicamentPindoles[] medicaments;
	private int num; // num. de medicaments
	private final int increment;
	

	// CONSTRUCTORs
	public Malalt(String nom, int numMax, int increment) {
		this.nom = nom;
		medicaments = new MedicamentPindoles[numMax];
		this.increment = increment;
	}


	public void comprarMedicamentPindoles(MedicamentPindoles p) {
		if(num==medicaments.length) 
			ampliar();
		medicaments[num]=p; num++; 

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

	public String numMedicamentsPerQueden() {
		int queden[]=new int[num]; int quants[]=new int[num];
		int quantes=0; // posicions plenes a queden[]

		for(int i=0; i<num; i++) {
			int x=troba(medicaments[i], queden, quantes); int posicio=x;
			
			if(x==-1 || quantes==0){
				queden[quantes]=medicaments[i].quantesUnitatsQueden(); 
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
		MedicamentPindoles[] buits_tmp=new MedicamentPindoles[num]; int cnt=0;
		for (MedicamentPindoles i : medicaments){
			if (i==null) continue; // legal? si no ho es, negar el if i que ho envolti tot.
			if (i.quantesUnitatsQueden()==0){
				buits_tmp[cnt]=i; cnt++;
			}
		}

		MedicamentPindoles[] buits=new MedicamentPindoles[cnt];
		for (int x=0;x<cnt;x++) buits[x]=buits_tmp[x];	
		return buits;
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
		MedicamentPindoles aux;
		for(int x=0; x<num; x++){
			for(int y=num-1; y>x; y--){
				int comparacio=medicaments[y].getNom().compareTo(medicaments[y-1].getNom());
				
				aux=medicaments[y];
				int pindolesPreses1=0; int pindolesPreses2=0;
				int pindolesMax1=0; int pindolesMax2=0;
				if (comparacio==0) { 
					pindolesPreses1=medicaments[y].getPindolesPreses();
					pindolesPreses2=medicaments[y-1].getPindolesPreses();

					pindolesMax1=pindolesPreses1+medicaments[y].quantesUnitatsQueden(); 
					pindolesMax2=pindolesPreses2+medicaments[y-1].quantesUnitatsQueden();
				}

				if (comparacio<0 || (comparacio==0 && (pindolesMax1>pindolesMax2 || (pindolesMax1==pindolesMax2 && pindolesPreses1>pindolesPreses2)))){
					medicaments[y]=medicaments[y-1]; medicaments[y-1]=aux;
				}
			}
		}
	}

	private static int troba(MedicamentPindoles caixa, int[] queden, int quants) {
		int quantitat=caixa.quantesUnitatsQueden();
		for(int i=0; i<quants; i++) {
			if (queden[i]==quantitat) return i;
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
