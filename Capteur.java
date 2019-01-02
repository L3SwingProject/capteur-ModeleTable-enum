package classPremiere;

public class Capteur {
	private String nom;
	private String batiment;
	private int etage;
	private String lieu;
	private String localisation;
	private TypeFluide type;
	private float valeurCourante;
	private float seuilMin;
	private float seuilMax;
	private boolean connecte=true;
	
	public Capteur(String nom, String description) {
		this.nom=nom;
		String separateur= ":";
		String des[] = description.split(separateur);
		this.type=TypeFluide.valueOf(des[0]);
		this.batiment=des[1];
		this.etage= Integer.valueOf(des[2]);
		this.lieu = des[3];
		this.localisation=des[1]+"-"+des[2]+"-"+des[3];
		initSeuil();
	}
	
	public void initSeuil() {
		if(type==TypeFluide.AIR_COMPRIME) {
			seuilMin = 0;
			seuilMax = 5;
		}
		if(type==TypeFluide.EAU) {
			seuilMin = 0;
			seuilMax = 10;
		}
		if(type==TypeFluide.ELECTRICITE) {
			seuilMin = 10;
			seuilMax = 500;
		}
		if(type==TypeFluide.TEMPERATURE) {
			seuilMin = 17;
			seuilMax = 22;
		}
	}
	
    public void update(float newValue){
        this.valeurCourante = newValue;
    }

    public void deconnexion(){
        this.connecte = false;
    }
	
	public int compareTo(Capteur acomp) {
		Boolean connect = this.connecte;
		Boolean connectAcomp = acomp.connecte;
		if(connect.equals(connectAcomp)) {
			return nom.compareTo(acomp.nom);
		}
		else {
			if(connecte) {
				return -10;
			}
			else {
				return 10;
			}
		}
	}
	
	public boolean equals(Object o) {
		if (o instanceof Capteur) {
			Capteur acomp = (Capteur)o;
			return nom.equals(acomp.nom);
		}
		else {
			return false;
		}
	}

	public String getNom() {
		return nom;
	}
	
	public String getBatiment() {
		return batiment;
	}

	public int getEtage() {
		return etage;
	}

	public String getLieu() {
		return lieu;
	}

	public float getValeurCourante() {
		return valeurCourante;
	}

	public float getSeuilMin() {
		return seuilMin;
	}

	public float getSeuilMax() {
		return seuilMax;
	}
	
	public TypeFluide getType() {
		return type;
	}
	
	public String getLocalisation() {
		return localisation;
	}
	
}
