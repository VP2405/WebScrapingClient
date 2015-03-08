package poi;

/**
 * Classe HotelRating che estende la classe Rating.	Comprende attributin aggiuntivi in base a determinati
 * paramentri relativi ad un albergo.
 * 
 * @author Andrea
 *
 */
public class HotelRating extends Rating {
	
	
	private double cleaning;
	private double comfort;
	private double position;
	private double services;
	private double staff;
	private double priceQuality;
	private double wifi;

	/**
	 * Costruttore di default per la classe HotelRating
	 */
	public HotelRating() {
		super();
		this.cleaning = 0.0;
		this.comfort = 0.0;
		this.position = 0.0;
		this.services = 0.0;
		this.staff = 0.0;
		this.priceQuality = 0.0;
		this.wifi = 0.0;
	}
	
	/**
	 * Costruttore per la classe HotelRating
	 * @param review
	 * @param value
	 * @param cleaning
	 * @param comfort
	 * @param position
	 * @param services
	 * @param staff
	 * @param priceQuality
	 * @param wifi
	 */
	public HotelRating(int review, int value,double cleaning,double comfort,double position,
			double services,double staff,double priceQuality,double wifi) {
		super(review, value);
		this.cleaning =  cleaning;
		this.comfort = comfort;
		this.position = position;
		this.services = services;
		this.staff = staff;
		this.priceQuality = priceQuality;
		this.wifi = wifi;
	}

	/**
	 * Restituisce il valore del campo cleaning
	 * @return
	 */
	public double getCleaning() {
		return cleaning;
	}
	
	/**
	 * Impopsta il valore del campo cleaning
	 * @param cleaning
	 */
	public void setCleaning(double cleaning) {
		this.cleaning = cleaning;
	}
	
	/**
	 * Restituisce il valore del campo comfort
	 * @return
	 */
	public double getComfort() {
		return comfort;
	}
	
	/**
	 * Imposta il valore del campo comfort
	 * @param comfort
	 */
	public void setComfort(double comfort) {
		this.comfort = comfort;
	}
	
	/**
	 * Restituisce il valore di position
	 * @return
	 */
	public double getPosition() {
		return position;
	}
	
	 /**
	  * Imposta il valore di position 
	  * @param position
	  */
	public void setPosition(double position) {
		this.position = position;
	}
	
	/**
	 * Restituisce il valore di services
	 * @return
	 */
	public double getServices() {
		return services;
	}
	
	/**
	 * Imposta il valore di services
	 * @param services
	 */
	public void setServices(double services) {
		this.services = services;
	}
	
	/**
	 * Restituisce il valore di staff
	 * @return
	 */
	public double getStaff() {
		return staff;
	}
	
	/**
	 * Imposta il valore di staff
	 * @param staff
	 */
	public void setStaff(double staff) {
		this.staff = staff;
	}
	
	/**
	 * Restituisce il valore di priceQuality
	 * @return
	 */
	public double getPriceQuality() {
		return priceQuality;
	}
	
	/**
	 * Imposta il valore di priceQuality
	 * @param priceQuality
	 */
	public void setPriceQuality(double priceQuality) {
		this.priceQuality = priceQuality;
	}
	
	/**
	 * Restituisce il valore di wifi
	 * @return
	 */
	public double getWifi() {
		return wifi;
	}
	
	/**
	 * Imposta il valore di wifi
	 * @param wifi
	 */
	public void setWifi(double wifi) {
		this.wifi = wifi;
	}

	@Override
	public String toString()
	{
		return "HotelRating [cleaning=" + cleaning + ", comfort=" + comfort + ", position=" 
				+ position + ", services=" + services + ", staff="
				+ staff + ", priceQuality=" + priceQuality + ", wifi=" + wifi + "]";
	}



	
	
}
