package car.advisor

class Utilisateur {

	String nom
	String prenom
	String ville
	String urlAvatar
	String refPers
	
	int age
	int nbEnfants
	
	String dateDebutEffet
	
	
    static constraints = {
		nom(nullable: false,blank:false)
		prenom(nullable: false,blank:false)
		ville(nullable: false,blank:false)
		urlAvatar(nullable: true,blank:true)
    }
	
	static mapping = {
		table 'utilisateur'
	}
}
