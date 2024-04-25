package domein;

import java.util.ArrayList;

public class Koninkrijk {
	private static final int rij = 5;
	private static final int kolom = 5;
	private static Tegel[][] bord;

	// Een array voor de grid aanmaken
	public Koninkrijk() {
		bord = new Tegel[rij][kolom];
	}

	// Zet kasteel in de grid
	public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
		getBord()[rij][kolom] = kasteel;
	}

	public Tegel[][] getBord() {
		return bord;
	}

	// Dominotegel op de grid plaatsen volgens xy coördinaten
	public void plaatsDomino(DominoTegel domino, int x, int y) {
		
	}
	
	public boolean isPlaatsVrij(int x, int y) {
		int coordinaatX = x;
		int coordinaatY = y;
		
		if (coordinaatX < 0)
			coordinaatX = 0;
		else if (coordinaatY < 0)
			coordinaatY = 0;
		else if (coordinaatX > this.rij)
			coordinaatX = rij - 1;
		else if (coordinaatY > this.kolom)
			coordinaatY = kolom -1;
		
		return bord[coordinaatX][kolom] == null;
	}
	
	public boolean isKasteelHier(int x, int y) {
		return bord[Math.min(Math.max(x, 0), rij - 1)][Math.min(Math.max(y, 0), kolom - 1)] instanceof KasteelTegel;
	}

	/**
	 * Kijk na of tegel1 hetzelfde landschap heeft als tegel2 Gebruikt voor het
	 * plaatsen van een domino naast een andere domino
	 * 
	 * @param tegel1
	 * @param tegel2
	 * @return true als de tegels een gelijk landschap hebben
	 */
	public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
		if (tegel1 != null)
			return tegel1.getLandschap().equals(tegel2);

		return false;
	}

	// Checkt of er op die plaats een open spot is
	public boolean isBezet(int rij, int kolom) {
		return getBord()[rij][kolom] == null;
	}

	/**
	 * Kijkt na of het bord volzet is
	 * 
	 * @return false als het bord niet volzet is
	 */
	public boolean isBordVolzet() {
		for (int i = 0; i < rij; i++) {
			for (int j = 0; j < kolom; j++) {
				if (!isBezet(i, j))
					return false;
			}
		}

		return true;
	}

	/**
	 * Berekent de grootte van de oppervlakte van een bepaald landschap + bereken
	 * het aantal kroontjes Zet deze in een tijdelijke array Voeg deze tijdelijke
	 * array van grootte, aantal kroontjes toe in oppervlakte
	 * 
	 * @param landschap bevat het landschap van de tegel en aantal kroontjes
	 * @return de totale oppervlakte van elk landschap
	 */
	public ArrayList<ArrayList<Integer>> berekenOppvervlakte(String landschap) {
		ArrayList<ArrayList<Integer>> oppervlakte = new ArrayList<>();

		int[][] passeerdeTegel = new int[rij][kolom];
		int[][] passeerdeTegelVoorKroon = new int[rij][kolom];

		for (int i = 0; i < passeerdeTegel.length; i++) {
			for (int j = 0; j < passeerdeTegel.length; j++) {
				if (getBord()[i][j] != null && getBord()[i][j].getLandschap().equals(landschap)) {
					passeerdeTegel[i][j] = 1;
					passeerdeTegelVoorKroon[i][j] = 1;
				} else {
					passeerdeTegel[i][j] = 0;
					passeerdeTegelVoorKroon[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < getBord().length; i++) {
			for (int j = 0; j < getBord().length; j++) {
				ArrayList<Integer> tijdelijkeArray = new ArrayList<>();

				int grootte = berekenHoeveelheidTegels(passeerdeTegel, i, j);
				int kroontjes = berekenKroontjes(passeerdeTegelVoorKroon, getBord(), i, j, 0);

				if (grootte > 1 || grootte == 1 && kroontjes >= 1) {
					tijdelijkeArray.add(grootte);
					tijdelijkeArray.add(kroontjes);

				} else if (tijdelijkeArray.size() > 0) {
					oppervlakte.add(tijdelijkeArray);
				}
			}
		}

		return oppervlakte;
	}

	// =============================================================
	// Thomas please maak hier de comments
	// =============================================================
	/**
	 * 
	 * @param grid
	 * @param i
	 * @param j
	 * @return
	 */
	private int berekenHoeveelheidTegels(int[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid.length)
			throw new IllegalArgumentException();
		// Nakijken of input klopt

		if (grid[i][j] == 0)
			return 0;

		grid[i][j] = 0;
		int teller = 1;
		teller += berekenHoeveelheidTegels(grid, i + 1, j);
		teller += berekenHoeveelheidTegels(grid, i - 1, j);
		teller += berekenHoeveelheidTegels(grid, i, j + 1);
		teller += berekenHoeveelheidTegels(grid, i, j - 1);

		return teller;
	}

	// ===============================================================
	// Hoe wordt hier gezocht op landschap? Nergens terug te vinden
	// ===============================================================

	/**
	 * Bereken het aantal kroontjes door het bord af te gaan, zoekende naar een
	 * specifiek landschap
	 * 
	 * @param grid      de 5x5 van een specifiek landschap
	 * @param bord      de hele 5x5 van het spel
	 * @param i         de x coördinaten van het bord
	 * @param j         de y coördinaten van het bord
	 * @param kroontjes
	 * @return het aantal kroontjes
	 */
	public int berekenKroontjes(int[][] grid, Tegel[][] bord, int i, int j, int kroontjes) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid.length)
			throw new IllegalArgumentException();
		// Nakijken of input klopt

		if (grid[i][j] == 0)
			return 0;

		grid[i][j] = 0;
		kroontjes = bord[i][j].getKroontjes();
		kroontjes += berekenKroontjes(grid, bord, i + 1, j, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i - 1, j, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i, j + 1, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i, j - 1, bord[i][j].getKroontjes());

		return kroontjes;
	}

}