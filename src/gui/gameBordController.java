package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import comparator.dominoTegelComparator;
import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class gameBordController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private DomeinController dc;
	private int aantalSpelers;

	private Label[] lbSpelers;
	private Label[] lbScores;
	private ImageView[] imgDominos;

	private Image selecteerdeImage;
	private boolean geKlikt = false;

	@FXML
	private Button btnDraai;

	@FXML
	private Button btnRoteer;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnSpeelveldSpelerDrie;

	@FXML
	private Button btnSpeelveldSpelerEen;

	@FXML
	private Button btnSpeelveldSpelerTwee;

	@FXML
	private GridPane grdBord;

	@FXML
	private ImageView imgDomino1;

	@FXML
	private ImageView imgDomino2;

	@FXML
	private ImageView imgDomino3;

	@FXML
	private ImageView imgDomino4;

	@FXML
	private Label lbAlgemeneTekst;

	@FXML
	private Label lbScore1;

	@FXML
	private Label lbScore2;

	@FXML
	private Label lbScore3;

	@FXML
	private Label lbScore4;

	@FXML
	private Label lbSpeler1;

	@FXML
	private Label lbSpeler2;

	@FXML
	private Label lbSpeler3;

	@FXML
	private Label lbSpeler4;

	@FXML
	private Label lblPlayingUsername;

	@FXML
	private Label lblProgressie;

	@FXML
	private ProgressBar pbProgressie;

	public void initialize() {
		lbSpelers = new Label[] { lbSpeler1, lbSpeler2, lbSpeler3, lbSpeler4 };
		lbScores = new Label[] { lbScore1, lbScore2, lbScore3, lbScore4 };
		imgDominos = new ImageView[] { imgDomino1, imgDomino2, imgDomino3, imgDomino4 };

		btnDraai.setText(vertaal.geefWoord("TURN_RIGHT"));

		btnRoteer.setText(vertaal.geefWoord("MIRROR"));

		btnSpeelveldSpelerDrie.setText(vertaal.geefWoord("PLAYER_3"));

		btnSpeelveldSpelerEen.setText(vertaal.geefWoord("PLAYER_1"));

		btnSpeelveldSpelerTwee.setText(vertaal.geefWoord("PLAYER_2"));

		lblProgressie.setText(vertaal.geefWoord("ALMOST_YOUR_TURN"));

		lblPlayingUsername.setText(vertaal.geefWoord("IS_PLAYING"));
	}

	public void setDc(DomeinController dc) {
		this.dc = dc;

		try {
			dc.startSpel();
			aantalSpelers = dc.geefAantalSpelers();

			startScene();

		} catch (Exception e) {
			System.err.print(e);

			/*
			 * try { switchtoStart();
			 * 
			 * } catch (IOException e1) { System.err.println(e1); }
			 */
		}

		if (lbSpelers.length < 4) {
			lbScore4.setDisable(true);
			lbScore4.setVisible(false);
			lbSpeler4.setDisable(true);
			lbSpeler4.setVisible(false);
		}
	}

	private final Object lock = new Object();

	private void startScene() {
		ArrayList<spelerDTO> deelnemers = new ArrayList<spelerDTO>();

		try {
			deelnemers = dc.geefDeelnemendeSpelersInSpel();
		} catch (Exception e) {
			System.err.println(e);
			// switchToSceneStart();
		}

		for (int i = 0; i < deelnemers.size(); i++) {
			lbSpelers[i].setText(deelnemers.get(i).gebruikersnaam());
			lbScores[i].setText("Score 0");
		}

		Thread gameLoop = new Thread(() -> {
			while (!spelTenEinde()) {
				Platform.runLater(() -> {
					setDominosOpScherm();
				});

				wachtVoorSpeler();

				for (int i = 0; i < 4; i++) {
					spelerDTO koning = geefKoning();
					
					Platform.runLater(() -> {
						persoonAanDeBeurt(koning);
						kiesDomino(koning);
					});

					wachtVoorSpeler();

					volgendeRonde();
				}
			}

		});

		gameLoop.start();

	}

	private void wachtVoorSpeler() {
		synchronized (lock) {
			try {
				lock.wait();

			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	private void kiesDomino(spelerDTO koning) {
		btnNext.setText("Chose domino");

		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (selecteerdeImage != null)
					System.out.printf("%s selected %s%n", koning.gebruikersnaam(), selecteerdeImage.toString());
				
				notifyThread();
			}
		});
	}

	private void volgendeRonde() {
		try {
			dc.kiesNieuweKoning();

		} catch (Exception e) {
			// hier
		}
	}

	private void setDominosOpScherm() {
		lblPlayingUsername.setText(String.format("Round %d", getRonde()));
		lbAlgemeneTekst.setText("Turn dominos");

		ArrayList<dominoTegelDTO> dominos = new ArrayList<>();

		try {
			dominos.addAll(dc.geefStartKolom());

		} catch (Exception e) {
			System.err.print(e);
			// switch
		}

		Collections.sort(dominos, new dominoTegelComparator());

		for (int i = 0; i < dominos.size(); i++) {
			Image img = new Image(
					String.format("file:assets/dominotegel/tegel_%02d_achterkant.png", dominos.get(i).volgnummer()));
			imgDominos[i].setImage(img);
		}
		btnNext.setText("Turn dominos");
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				draaiDominos(dominos);
				notifyThread();
			}
		});

	}

	protected void draaiDominos(ArrayList<dominoTegelDTO> dominos) {
		lbAlgemeneTekst.setText("Chose domino");

		for (int i = 0; i < dominos.size(); i++) {
			Image img = new Image(
					String.format("file:assets/dominotegel/tegel_%02d_voorkant.png", dominos.get(i).volgnummer()));
			imgDominos[i].setImage(img);
		}
	}

	private int getRonde() {
		try {
			return dc.getRonde();

		} catch (Exception e) {
			System.err.println(e);
		}

		return 0;
	}

	private boolean spelTenEinde() {
		try {
			return dc.isSpelTenEinde();

		} catch (Exception e) {
			System.err.print(e);
			// Switch
		}

		return false;
	}

	private void persoonAanDeBeurt(spelerDTO koning) {
		lblPlayingUsername.setText(String.format("%s %s:", koning.gebruikersnaam(), "turn"));
	}

	private spelerDTO geefKoning() {
		spelerDTO koning = null;

		try {
			koning = dc.geefKoning();
		} catch (Exception e) {
			System.err.print(e);
			// Switch
		}

		return koning;
	}

	private void updateScores() {
		ArrayList<spelerDTO> deelnemers = new ArrayList<>();
		try {
			deelnemers.addAll(dc.geefDeelnemendeSpelersInSpel());
		} catch (Exception e) {
			// Switch
		}

		for (int i = 0; i < deelnemers.size(); i++) {
			lbScores[i].setText(String.format("Score %d", deelnemers.get(i).score()));
		}

	}

	@FXML
	public void switchToSceneStart(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();

		MenuStartController controller = loader.getController();
		controller.setDc(dc);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void selecteerImage(javafx.scene.input.MouseEvent event) {
		ImageView imageView = (ImageView) event.getSource();
		selecteerdeImage = imageView.getImage();
	}

	private void notifyThread() {
		synchronized (lock) {
			lock.notify();
		}
	}

	private void switchtoStart() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();

		MenuStartController controller = loader.getController();
		controller.setDc(dc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}