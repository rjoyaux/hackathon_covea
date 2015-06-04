package car.advisor

class Vehicule {
	
	String identifiant
	String marque
	String modele
	String generation
	String carrosserie
	String declinaison
	String urlImage

    static constraints = {
		 identifiant(nullable: false,blank:false)
		 marque(nullable: false,blank:false)
		 modele(nullable: false,blank:false)
		 generation(nullable: false,blank:false)
		 carrosserie(nullable: false,blank:false)
		 declinaison(nullable: false,blank:false)
		 urlImage(nullable: true,blank:true)
    }
}
