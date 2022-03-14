package Sessio2;

import java.util.Arrays;
import java.util.Random;
//import Keyboard.*;

public class Prova {
    public static void main(String args[]){
        main2();
    }
    public static void main1() {
    	System.out.println("Errors:");
    	
    	System.out.println("\nMalalts:");
    	try {new Malalt("",1,1);} catch (IllegalArgumentException e) {System.out.println(e);}
    	try {new Malalt("Nom",0,1);} catch (IllegalArgumentException e) {System.out.println(e);}
    	try {new Malalt("Nom",1,-1);} catch (IllegalArgumentException e) {System.out.println(e);}
    	 
    	
    	
    	Malalt malalt=new Malalt("Jon", 10, 2);
    	 
    	System.out.println("\nMedicaments:");
    	try {malalt.comprarMedicamentPindoles("Error :)");} catch (IllegalArgumentException e) {System.out.println(e);}
    	try {malalt.comprarMedicamentPindoles(null, 3);} catch (IllegalArgumentException e) {System.out.println(e);}
    	try {malalt.comprarMedicamentPindoles("NomMedicament", -1);} catch (IllegalArgumentException e) {System.out.println(e);}
    	    
    	malalt.comprarMedicamentPindoles("Ibuprufeno", 20);
    	malalt.comprarMedicamentPindoles("Paracetamol", 13);
    	
    	MedicamentPindoles med=malalt.getMedicamentNoBuit();
    	for (int x=(int)med.quantesUnitatsQueden()/2; x>0; x--) 
    		med.prendrePindola();
    	
    	System.out.println("\ntotalPindolesPreses:");
    	try { malalt.totalPindolesPreses(null); } catch (IllegalArgumentException e) {System.out.println(e);}
    	try { malalt.totalPindolesPreses("Nom inexistent"); } catch (IllegalArgumentException e) {System.out.println(e);}
    	
    	
    	System.out.println("\ncompareTo:");
    	try { malalt.compareTo((Integer) 1); } catch (ClassCastException e) {System.out.println(e);}
   
    	
    	System.out.println("\ngetMedicamentPindoles:");
    	try { malalt.getMedicamentPindoles(3); } catch (IllegalArgumentException e) {System.out.println(e);}
    }
    
    
    public static final String[] nomsMalalts={
		"Josep",
		"Josepa",
		"Joseph",
		"Josephina"
	};
    public static final String[] nomsMedicaments={
		"Ibuprufeno",
		"Paracetamol",
		"Aspirina",
		"Omeprazol",
		"Amlodipina"
	};
    
    public static void main2() {
    	System.out.print("Quants malalts: "); int numMalalts=Keyboard.readInt();
    	Malalt[] malalts=new Malalt[numMalalts]; int cntMalalts=0;
    	
    	for(int i=0;i<numMalalts;i++){
    		Random rand=new Random();
    		
    		String nomMalalt=nomsMalalts[rand.nextInt(nomsMalalts.length)];
    		int maximMedicaments=rand.nextInt(10)+1;
    		int numMedicaments=rand.nextInt(maximMedicaments)+1;
    		int increment=rand.nextInt(5)+1;
    		
    		Malalt malalt=new Malalt(nomMalalt, maximMedicaments, increment);
    		
    		for (int o=0;o<numMedicaments; o++)
    			malalt.comprarMedicamentPindoles(nomsMedicaments[rand.nextInt(nomsMedicaments.length)], rand.nextInt(50)+1);
    		
    		print(malalt);
    		
    		malalts[cntMalalts]=malalt; cntMalalts++;
    	}
    	
    	System.out.println("\n\n\n\n******************************************************************");
    	System.out.println("******************************************************************\n\n");
    	
    	Arrays.sort(malalts);
    	for(Malalt m : malalts) print(m);
    	
    }
    
    public static void print(Malalt malalt) {
    	System.out.println("\n\n*************** "+malalt.getNom()+" ***************");
    	System.out.println("Malalt "+malalt.getNom()+": "); System.out.println(malalt);
		System.out.println("Llistat Ordenat Descendent: "); malalt.llistatOrdenatDescendent();
		System.out.println("\nLlistat Ordenat Ascendent: "); malalt.llistatOrdenatAscendent();
		// System.out.println("\nnumMedicamentsPerQueden:\n"+ malalt.numMedicamentsPerQueden());
    }
}