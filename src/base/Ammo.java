package base;


// ******** UNUSED ***********//
public class Ammo {
	private int damage;
	private int splashRadius;
	protected String damageType; // ??
	private String buffStat;
	private double buffRatio;

	public Ammo(int damage, String buffStat, double buffRatio) {
		setDamage(damage);
		//setSplashRadius(splashRadius);
		setBuffStat(buffStat);
		setBuffRatio(buffRatio);
	}

	public void setDamage(int damage) {
		this.damage = Math.max(0, damage);
	}

	public void setSplashRadius(int splashRadius) {
		this.splashRadius = Math.max(0, splashRadius);
	}
	
	public void setBuffRatio(double buffRatio) {
		this.buffRatio = Math.max(buffRatio, 0);
	}

	public void setBuffStat(String buffStat) {
		this.buffStat = buffStat;
	}
	
	public int getDamage() {
		return damage;
	}

	public int getSplashRadius() {
		return splashRadius;
	}

	public String getBuffStat() {
		return buffStat;
	}

	public double getBuffRatio() {
		return buffRatio;
	}


}
