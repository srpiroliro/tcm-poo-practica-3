package Sessio2;

public class Prova {
    public static void main(String args[]){
        main1();
        
        
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
}
