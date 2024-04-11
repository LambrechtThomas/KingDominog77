package domein;

import java.util.ArrayList;

public class DominoTegelRepository {
	private ArrayList<DominoTegel> alleDominos;

	public DominoTegelRepository() {
	}

	// Returnt de eerste domino op het deck
	public DominoTegel geefDomino() {
		DominoTegel eersteTegel = alleDominos.get(0);
		alleDominos.remove(0);

		return eersteTegel;
	}

	// Returnt volledige lijst van dominos
	public ArrayList<DominoTegel> geefLijstDominos() {
		// TODO
		return alleDominos;
	}

	// Geeft een lijst van domino's terug (aan de hand van de aantal spelers)
	public ArrayList<DominoTegel> geefKolom(int aantalSpelers) {
		ArrayList<DominoTegel> dominos = new ArrayList<DominoTegel>();
		for (int i = 0; i < aantalSpelers; i++) {
			dominos.add(geefDomino());
		}
		return dominos;
	}

	private void genereerDominos() {
		ArrayList<DominoTegel> dominos = new ArrayList<DominoTegel>();
		dominos.add(new DominoTegel(1, new Tegel[]{new Tegel("graan", 0), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(2, new Tegel[]{new Tegel("graan", 0), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(3, new Tegel[]{new Tegel("bos", 0), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(4, new Tegel[]{new Tegel("bos", 0), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(5, new Tegel[]{new Tegel("bos", 0), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(6, new Tegel[]{new Tegel("bos", 0), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(7, new Tegel[]{new Tegel("water", 0), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(8, new Tegel[]{new Tegel("water", 0), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(9, new Tegel[]{new Tegel("water", 0), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(10, new Tegel[]{new Tegel("gras", 0), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(11, new Tegel[]{new Tegel("gras", 0), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(12, new Tegel[]{new Tegel("moeras", 0), new Tegel("moeras", 0)}));
		dominos.add(new DominoTegel(13, new Tegel[]{new Tegel("graan", 0), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(14, new Tegel[]{new Tegel("graan", 0), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(15, new Tegel[]{new Tegel("graan", 0), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(16, new Tegel[]{new Tegel("graan", 0), new Tegel("moeras", 0)}));
		dominos.add(new DominoTegel(17, new Tegel[]{new Tegel("bos", 0), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(18, new Tegel[]{new Tegel("bos", 0), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(19, new Tegel[]{new Tegel("graan", 1), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(20, new Tegel[]{new Tegel("graan", 1), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(21, new Tegel[]{new Tegel("graan", 1), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(22, new Tegel[]{new Tegel("graan", 1), new Tegel("moeras", 0)}));
		dominos.add(new DominoTegel(23, new Tegel[]{new Tegel("graan", 1), new Tegel("mijn", 0)}));
		dominos.add(new DominoTegel(24, new Tegel[]{new Tegel("bos", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(25, new Tegel[]{new Tegel("bos", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(26, new Tegel[]{new Tegel("bos", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(27, new Tegel[]{new Tegel("bos", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(28, new Tegel[]{new Tegel("bos", 1), new Tegel("water", 0)}));
		dominos.add(new DominoTegel(29, new Tegel[]{new Tegel("bos", 1), new Tegel("gras", 0)}));
		dominos.add(new DominoTegel(30, new Tegel[]{new Tegel("water", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(31, new Tegel[]{new Tegel("water", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(32, new Tegel[]{new Tegel("water", 1), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(33, new Tegel[]{new Tegel("water", 1), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(34, new Tegel[]{new Tegel("water", 1), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(35, new Tegel[]{new Tegel("water", 1), new Tegel("bos", 0)}));
		dominos.add(new DominoTegel(36, new Tegel[]{new Tegel("graan", 0), new Tegel("gras", 1)}));
		dominos.add(new DominoTegel(37, new Tegel[]{new Tegel("water", 0), new Tegel("gras", 1)}));
		dominos.add(new DominoTegel(38, new Tegel[]{new Tegel("graan", 0), new Tegel("moeras", 1)}));
		dominos.add(new DominoTegel(39, new Tegel[]{new Tegel("gras", 0), new Tegel("moeras", 1)}));
		dominos.add(new DominoTegel(40, new Tegel[]{new Tegel("mijn", 1), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(41, new Tegel[]{new Tegel("graan", 0), new Tegel("gras", 2)}));
		dominos.add(new DominoTegel(42, new Tegel[]{new Tegel("water", 0), new Tegel("gras", 2)}));
		dominos.add(new DominoTegel(43, new Tegel[]{new Tegel("graan", 0), new Tegel("moeras", 2)}));
		dominos.add(new DominoTegel(44, new Tegel[]{new Tegel("gras", 0), new Tegel("moeras", 2)}));
		dominos.add(new DominoTegel(45, new Tegel[]{new Tegel("mijn", 2), new Tegel("graan", 0)}));
		dominos.add(new DominoTegel(46, new Tegel[]{new Tegel("moeras", 0), new Tegel("mijn", 2)}));
		dominos.add(new DominoTegel(47, new Tegel[]{new Tegel("moeras", 0), new Tegel("mijn", 2)}));
		dominos.add(new DominoTegel(48, new Tegel[]{new Tegel("graan", 0), new Tegel("mijn", 3)}));
	}
}
