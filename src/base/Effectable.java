package base;

public interface Effectable {
	//public static final int duration = 0;
	public int effect(Effectable e); //return the newly changed stat, 'this' was effected by 'e'.
	public int revertChange(Effectable e);
}
