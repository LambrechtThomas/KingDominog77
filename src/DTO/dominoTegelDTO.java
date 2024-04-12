package DTO;

import domein.Tegel;

public record dominoTegelDTO(int volgnummer, Tegel[] tegels, boolean horizontaal, boolean spiegeld) {
	public dominoTegelDTO(int volgnummer, Tegel[] tegels, boolean horizontaal, boolean spiegeld) {
		this.volgnummer = volgnummer;
		this.tegels = tegels;
		this.horizontaal = horizontaal;
		this.spiegeld = spiegeld;
	}

}
