package domein;

import java.util.Locale;
import java.util.ResourceBundle;

import taalmanager.vertaal;

public enum Kleur {
    ROZE,
    BLAUW,
    GEEL,
    GROEN;

    @Override
    public String toString() {
        return vertaal.geefWoord(this.name());
    }
}
