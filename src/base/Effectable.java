package base;

public interface Effectable {
	public int effect(Castable caster);

	public void revertChange(Castable caster, int originalStat);
}
