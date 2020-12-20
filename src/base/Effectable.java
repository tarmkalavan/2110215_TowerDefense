package base;

public interface Effectable {
	//public static final int duration = 0;
	public int effect(Castable caster); //return the newly changed stat, 'this' was effected by 'e'.
	public void revertChange(Castable caster, int originalStat); //revert  stat changes from e.
}
