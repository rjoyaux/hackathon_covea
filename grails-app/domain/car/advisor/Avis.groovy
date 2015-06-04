package car.advisor

class Avis {

	int noteConsommation
	int noteQualiteFinition
	int noteSecurite
	int noteRapportQualitePrix
	int noteConfort
	int noteCoutEntretien
	
	String description
	
	Vehicule vehicule
	
    static constraints = {
		noteConsommation(nullable: false, blank:false)
		noteQualiteFinition(nullable: false, blank:false)
		noteSecurite(nullable: false, blank:false)
		noteRapportQualitePrix(nullable: false, blank:false)
		noteConfort(nullable: false, blank:false)
		noteCoutEntretien(nullable: false, blank:false)
		description(nullable: false, blank:false)
    }
	
	static mapping = {
		table 'avis'
	}
}
