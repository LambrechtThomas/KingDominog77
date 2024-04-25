package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domein.Speler;

public class SpelerMapper {

	private static final String INSERT_SPELER = "INSERT INTO ID430019_g77.Speler (gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld)"
			+ "VALUES (?, ?, ?, ?)";

	/**
	 * Maake een connectie aan met de databank en voer een query uit om te vragen
	 * voor de gebruikersnaam, geboortejaar, aantal gewonnen en aantal gespeeld van
	 * de speler
	 * 
	 * @param speler
	 */

	public void voegToe(Speler speler) {
		Connectie ssh = new Connectie();
		try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
				PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
			query.setString(1, speler.getGebruikersnaam());
			query.setInt(2, speler.getGeboortejaar());
			query.setInt(3, speler.getAantalGewonnen());
			query.setInt(4, speler.getAantalGespeeld());

			query.executeUpdate();

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		ssh.closeConnection();
	}

	/**
	 * Maake een connectie aan met de databank en voer een query uit om te vragen
	 * voor het geboortejaar, aantal gewonnen en aantal gespeeld van een
	 * gebruikersnaam en maak hier een speler van
	 * 
	 * @param gebruikersnaam van de gebruiker
	 * @return speler met daarin de gebruikersnaam van de speler
	 */

	public Speler geefSpeler(String gebruikersnaam) {
		Connectie ssh = new Connectie();
		Speler speler = null;

		try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
				PreparedStatement query = conn
						.prepareStatement("SELECT * FROM ID430019_g77.Speler WHERE gebruikersnaam = ?")) {
			query.setString(1, gebruikersnaam);
			try (ResultSet rs = query.executeQuery()) {
				if (rs.next()) {
					int geboortejaar = rs.getInt("geboortejaar");
					int aantalGewonnen = rs.getInt("aantalGewonnen");
					int aantalGespeeld = rs.getInt("aantalGespeeld");

					speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		ssh.closeConnection();
		return speler;
	}

	/**
	 * Haal de hele spelerslijst uit de databank
	 * 
	 * @return de lijst van spelers
	 */

	public ArrayList<Speler> geefSpelers() {

		ArrayList<Speler> spelers = new ArrayList<>();
		Connectie ssh = new Connectie();

		try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC)) {

			Statement stmt = (Statement) conn.createStatement();
			String zoek = "SELECT * FROM ID430019_g77.Speler";
			ResultSet rs = stmt.executeQuery(zoek);

			while (rs.next()) {
				String gebruikersnaam = rs.getString("gebruikersnaam");
				int geboortejaar = rs.getInt("geboortejaar");
				int aantalGewonnen = rs.getInt("aantalGewonnen");
				int aantalGespeeld = rs.getInt("aantalGespeeld");

				spelers.add(new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld));
			}

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		ssh.closeConnection();
		return spelers;
	}
}