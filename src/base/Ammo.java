package base;

public class Ammo {	
	protected int damage;
	protected String damageType; //??
	protected String debuff; //??
	protected int splashRadius;
	
	public Ammo(int damage, int splashRadius) {
		setDamage(damage);
		setSplashRadius(splashRadius);
	}
	
	public void setDamage(int damage) {
		this.damage = Math.max(0, damage);
	}
	
	public void setSplashRadius(int splashRadius) {
		this.splashRadius = Math.max(0, splashRadius);
	}
	public int getDamage() {
		return damage;
	}
	public int getSplashRadius() {
		return splashRadius;
	}
}
