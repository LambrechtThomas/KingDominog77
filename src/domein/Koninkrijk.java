package domein;

import java.util.ArrayList;

public class Koninkrijk {
	private static final int rij = 5;
	private static final int kolom = 5;
	private static Tegel[][] bord;

	/**
	 * Een array voor de grid aanmaken
	 */
	public Koninkrijk() {
		bord = new Tegel[rij][kolom];
	}

	/**
	 * Zet kasteel in de grid
	 * 
	 * @param rij
	 * @param kolom
	 * @param kasteel
	 */
	public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
		bord[rij][kolom] = kasteel;
	}

	/**
	 * Get het bord
	 * 
	 * @return het bord
	 */
	public Tegel[][] getBord() {
		return bord;
	}

	/**
	 * Dominotegel op de grid plaatsen volgens x-y coördinaten
	 * 
	 * @param domino
	 * @param x
	 * @param y
	 */
	public void plaatsDomino(DominoTegel domino, int x, int y) {
		
	}
	
	/**
	 * Checkt of de plaats vrij is aan de hand van x-y coördinaten
	 * 
	 * @param x
	 * @param y
	 * @return true/false
	 */
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
	
	/**
	 * Checkt of kasteel op het geselecteerde coordinaat staat
	 * 
	 * @param x
	 * @param y
	 * @return true/false
	 */
	public boolean isKasteelHier(int x, int y) {
		return bord[Math.min(Math.max(x, 0), rij - 1)][Math.min(Math.max(y, 0), kolom - 1)] instanceof KasteelTegel;
	}

	/**
	 * Kijk na of tegel1 hetzelfde landschap heeft als tegel2 
	 * Gebruikt voor het plaatsen van een domino naast een andere domino
	 * 
	 * @param tegel1
	 * @param tegel2
	 * @return true/false
	 */
	public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
		if (tegel1 != null)
			return tegel1.getLandschap().equals(tegel2);

		return false;
	}

	/**
	 * Checkt of de plaats al dan niet bezit is
	 * 
	 * @param rij
	 * @param kolom
	 * @return
	 */
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
	 * Berekent de oppervlakte van het landschap die de speler bezit
	 * 
	 * @param landschap
	 * @return de oppervlakte
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

	/**
	 * Berekent hoeveelheid tegels
	 * 
	 * @param grid
	 * @param i
	 * @param j
	 * @return	het aantal tegels
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