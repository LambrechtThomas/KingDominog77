package domein;

import java.util.ArrayList;

public class Koninkrijk {
	private static final int rij = 5;
	private static final int kolom = 5;
	private static Tegel[][] bord;

	public Koninkrijk() {
<<<<<<< Updated upstream
		bord = new Tegel[rij][kolom];
=======
>>>>>>> Stashed changes
	}

	// Zet kasteel in de grid
	public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
		getBord()[rij][kolom] = kasteel;
	}
	
	public static Tegel[][] getBord() {
		return bord;
	}

	public void plaatsDomino(DominoTegel domino, int x, int y) {
		// TODO
	}

	// Checkt of het landschap hetzelfde is
	// Juist tegel1 checken voor null want null.getLandschap() geeft fout
	public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
		if (tegel1 != null)
			return tegel1.getLandschap().equals(tegel2);

		return false;
	}

	// Checkt of er op die plaats een open spot is
	public boolean isBezet(int rij, int kolom) {
		return getBord()[rij][kolom] == null;
	}

	// Checkt of het bord volledig vol is
	public boolean isBordVolzet() {
		for (int i = 0; i < rij; i++) {
			for (int j = 0; j < kolom; j++) {
				if (!isBezet(i, j))
					return false;
			}
		}

		return true;
	}

	// geeft een raster terug met oppervlakte en kronen
	public ArrayList<ArrayList<Integer>> berekenOppvervlakte(String landschap) {
		ArrayList<ArrayList<Integer>> oppervlakte =  new ArrayList<>();
		
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

	// bereken hoeveel 1tjes in het grid zitten
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

	// Berekent de som van kroontjes van het opppervlakte
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