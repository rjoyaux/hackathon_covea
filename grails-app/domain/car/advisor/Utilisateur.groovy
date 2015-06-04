package car.advisor

class Utilisateur {

	String nom
	String prenom
	String ville
	String urlAvatar
	String refPers
	
	int age
	int nbEnfants
	
	Date dateDebutEffet
	
	static hasOne = [vehicule: Vehicule]
	
    static constraints = {
		nom(nullable: false,blank:false)
		prenom(nullable: false,blank:false)
		ville(nullable: false,blank:false)
		urlAvatar(nullable: false,blank:false)
    }
	
	static mapping = {
		table 'utilisateur'
	}
}
