package domein;

import javafx.scene.paint.Color;
import taalmanager.vertaal;

public enum Kleur {
	ROZE(Color.PINK.darker()), BLAUW(Color.BLUE), GEEL(Color.ORANGE), GROEN(Color.GREEN);

	private final Color color;

	Kleur(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return vertaal.geefWoord(this.name());
	}

	public String toStringForPath() {
		return this.name().toLowerCase();
	}

	public Color getColor() {
		return color;
	}
}
