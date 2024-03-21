package domein;

public class Koninkrijk {
<<<<<<< Updated upstream
    private static int rij = 5;
    private static int kolom = 5;
    private static Tegel[][] grid;
    
    public Koninkrijk() {
        
    }
    
    // Zet kasteel in de grid
    public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
        grid[rij][kolom] = kasteel;
    }
    
    
    public void plaatsDomino(DominoTegel domino, int x, int y) {
        
    }
    
    // Checkt of het landschap hetzelfde is
    // Juist tegel1 checken voor null want null.getLandschap() geeft fout
    public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
        if(tegel1 != null)
            return tegel1.getLandschap().equals(tegel2);
        
        return false;
    }
    
    // Checkt of er op die plaats een open spot is
    public boolean isBezet(int rij, int kolom) {
        return grid[rij][kolom] == null;
    }
    
    // Checkt of het bord volledig vol is 
    public boolean isBordVolzet() {
        for(int i = 0; i < rij; i++) {
            for(int j = 0; j < kolom; j++) {
                if(!isBezet(i,j))
                    return false;
            }
        }
            
        return true;
    }
}
=======
	private static int rij = 5;
	private static int kolom = 5;
	private static Tegel[][] grid;
	
	public Koninkrijk() {
		
	}
	
	//Zet de kasteel in het grid
	public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
		grid[rij][kolom] = kasteel;
	}
	
	
	public void plaatsDomino(DominoTegel domino, int x, int y) {
		
	}
	
	//Check of de landschap het zelfde is
	//Juist tegel1 checken voor null want null.getLandschap() geeft fout
	public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
		if(tegel1 != null)
			return tegel1.getLandschap().equals(tegel2);
		
		return false;
	}
	
	//Check of er op die plaats een open spot is
	public boolean isBezet(int rij, int kolom) {
		return grid[rij][kolom] == null;
	}
	
	//Check of het bord volledig vol is of niet
	public boolean isBordVolzet() {
		for(int i = 0; i < rij; i++) {
			for(int j = 0; j < kolom; j++) {
				if(!isBezet(i,j))
					return false;
			}
		}
			
		return true;
	}
}
>>>>>>> Stashed changes
