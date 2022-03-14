package Sessio1;

public class Prova {
    public static void main(String args[]){
        int increment=2;
        Malalt malalts[] = {new Malalt("Maria Fernández", 10, increment), new Malalt("Joan Moll", 15, increment)};

        malalts[0].comprarMedicamentPindoles(new MedicamentPindoles("A",60));
        

        for (int x=0;x<2;x++) {
            malalts[1].comprarMedicamentPindoles(new MedicamentPindoles("F",30));
            malalts[1].comprarMedicamentPindoles(new MedicamentPindoles("D",35));
            malalts[1].comprarMedicamentPindoles(new MedicamentPindoles("E",20));
        }

        
        imprimirDades(malalts);
        
        for (Malalt malalt: malalts) prendreTotsElsMedicamentsComprats(malalt); System.out.println("MEDS ARE GONE\n\n");
        
        imprimirDades(malalts);
    }

    private static void prendreTotsElsMedicamentsComprats(Malalt m) {
    	MedicamentPindoles med;
        do {
        	med=m.getMedicamentNoBuit();
            if(med!=null) prendreDelMedicament(med);
        } while (med!=null);
    }

    private static void prendreDelMedicament(MedicamentPindoles m){
        for (int x=m.quantesUnitatsQueden(); x>0; x--) m.prendrePindola();
    }

    private static void imprimirDades(Malalt malalts[]){
        for (Malalt malalt: malalts){
            String msg="El malalt "+malalt.getNom()+" té:"; System.out.println(msg);
            for (int i=0; i<msg.length(); i++) System.out.print("*"); System.out.println("");
            System.out.println(malalt.toString());
            
            msg="Llista per unitats:"; System.out.println(msg);
            for (int i=0; i<msg.length(); i++) System.out.print("*"); System.out.println("");
            String[] parts=malalt.numMedicamentsPerQueden().split(" - ");
            for (int x=0; x<parts.length; x++)
                System.out.println(x+". "+parts[x]);
            
            System.out.println("\n");
        }
    }
}
