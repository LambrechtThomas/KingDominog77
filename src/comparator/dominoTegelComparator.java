package comparator;

import java.util.Comparator;

import DTO.dominoTegelDTO;

public class dominoTegelComparator implements Comparator<dominoTegelDTO> {

	@Override
	public int compare(dominoTegelDTO a, dominoTegelDTO b) {
		return a.volgnummer() - b.volgnummer();
	}
}
