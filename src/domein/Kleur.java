package domein;

// Enumeration kleuren

public enum Kleur {
	ROZE {
		@Override
		public String toString() {
			return "Roze";
		}
	},
	
	BLAUW {
		@Override
		public String toString() {
			return "Blauw";
		}
	},
	
	GEEL {
		@Override
		public String toString() {
			return "Geel";
		}
	},
	
	GROEN {
		@Override
		public String toString() {
			return "Groen";
		}

	}

}
