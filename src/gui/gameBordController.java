package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import comparator.dominoTegelComparator;
import domein.DomeinController;
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
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class gameBordController extends SplitPane {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private DomeinController dc;

	private int aantalSpelers;
	private ArrayList<spelerDTO> deelnemers;
	private spelerDTO koning;
	private HashMap<spelerDTO, dominoTegelDTO> gekozenDominoSpeler;
	private HashMap<spelerDTO, dominoTegelDTO> gekozenTeLeggenDominos;
	private HashMap<spelerDTO, GridPane> gridPanePerPersoon;

	private Label[] lbSpelers;
	private Label[] lbScores;
	private Label[] lbVelden;
	private ImageView[] imgDominos;
	private ImageView[] imgEindkolom;
	private AnchorPane[] borden;

	private Image selecteerdeImage;
	private ImageView sleepBareImage;
	private ArrayList<Image> mogelijkeImages;
	private ArrayList<Image> alleTijdelijkeImages;
	private ArrayList<ImageView> startPionImages;
	private ArrayList<ImageView> eindPionImages;

	private ArrayList<dominoTegelDTO> eindKolom;
	private ArrayList<dominoTegelDTO> startKolom;
	private boolean horizontaal;
	private boolean spiegeld;

	@FXML
	private AnchorPane anchBord1;

	@FXML
	private AnchorPane anchBord2;

	@FXML
	private AnchorPane anchBord3;

	@FXML
	private AnchorPane anchBord4;

	@FXML
	private Button btnDraai;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnSpiegel;

	@FXML
	private GridPane grdDominos;

	@FXML
	private ImageView imgDomino1;

	@FXML
	private ImageView imgDomino2;

	@FXML
	private ImageView imgDomino3;

	@FXML
	private ImageView imgDomino4;

	@FXML
	private ImageView imgDomino5;

	@FXML
	private ImageView imgDomino6;

	@FXML
	private ImageView imgDomino7;

	@FXML
	private ImageView imgDomino8;

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
	private Label lbVeldId1;

	@FXML
	private Label lbVeldId2;

	@FXML
	private Label lbVeldId3;

	@FXML
	private Label lbVeldId4;

	@FXML
	private Label lblPlayingUsername;

	@FXML
	private Label lblProgressie;

	@FXML
	private ProgressBar pbProgressie;

	public void initialize() {
		btnDraai.setText(vertaal.geefWoord("TURN_RIGHT"));
		btnSpiegel.setText(vertaal.geefWoord("MIRROR"));
		lblProgressie.setText(vertaal.geefWoord("ALMOST_YOUR_TURN"));
		lblPlayingUsername.setText(vertaal.geefWoord("IS_PLAYING"));
	}

	private void loadFxmlScreen(String name) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public gameBordController(DomeinController dc2, Stage stage) {
		loadFxmlScreen("gameBord.fxml");
		this.dc = dc2;
		this.stage = stage;

		lbSpelers = new Label[] { lbSpeler1, lbSpeler2, lbSpeler3, lbSpeler4 };
		lbScores = new Label[] { lbScore1, lbScore2, lbScore3, lbScore4 };
		imgDominos = new ImageView[] { imgDomino1, imgDomino2, imgDomino3, imgDomino4 };
		imgEindkolom = new ImageView[] { imgDomino5, imgDomino6, imgDomino7, imgDomino8 };
		borden = new AnchorPane[] { anchBord1, anchBord2, anchBord3, anchBord4 };
		lbVelden = new Label[] { lbVeldId1, lbVeldId2, lbVeldId3, lbVeldId4 };
		eindKolom = new ArrayList<>();
		startKolom = new ArrayList<>();
		deelnemers = new ArrayList<>();
		mogelijkeImages = new ArrayList<>();
		alleTijdelijkeImages = new ArrayList<>();
		gekozenDominoSpeler = new HashMap<>();
		startPionImages = new ArrayList<>();
		eindPionImages = new ArrayList<>();
		gridPanePerPersoon = new HashMap<>();
		gekozenTeLeggenDominos = new HashMap<>();
		horizontaal = true;
		spiegeld = false;

		try {
			dc.startSpel();
			aantalSpelers = dc.geefAantalSpelers();
			deelnemers = dc.geefDeelnemendeSpelersInSpel();

		} catch (Exception e) {
			System.err.print(e);
		}

		if (aantalSpelers < 4) {
			lbScore4.setDisable(true);
			lbScore4.setVisible(false);
			lbSpeler4.setDisable(true);
			lbSpeler4.setVisible(false);
			anchBord4.setDisable(true);
			anchBord4.setVisible(false);
			lbVeldId4.setDisable(true);
			lbVeldId4.setVisible(false);
		}

		startSpel();

	}

	private void startSpel() {
		updateScores();
		updateKoning();
		creatieBorden();
		updateBorden();
		setDominosOpScherm();
	}

	private void creatieBorden() {
		for (spelerDTO speler : deelnemers) {
			GridPane pane = new GridPane();

			for (int i = 0; i < 5; i++) {
				pane.addRow(i);
				pane.addColumn(i);
			}

			for (int i = 0; i < 5; i++) {
				ColumnConstraints column = new ColumnConstraints(40);
				pane.getColumnConstraints().add(column);

				RowConstraints row = new RowConstraints(40);
				pane.getRowConstraints().add(row);
			}

			ImageView imgV = new ImageView(new Image(
					String.format("file:assets/starttegel/starttegel_%s.png", speler.kleur().toStringForPath())));
			imgV.setPreserveRatio(true);
			imgV.setFitWidth(40);
			imgV.setFitHeight(40);
			pane.add(imgV, 2, 2);

			pane.setOnDragOver(event -> {
				if (event.getGestureSource() != pane && event.getDragboard().hasImage()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}

				event.consume();
			});

			pane.setOnDragDropped(event -> {
				Dragboard db = event.getDragboard();

				if (db.hasImage()) {
					int kolom = (int) (event.getX() / (pane.getWidth() / pane.getColumnCount()));
					int rij = (int) (event.getY() / (pane.getHeight() / pane.getRowCount()));

					pane.getChildren().remove(sleepBareImage);

					pane.add(sleepBareImage, kolom, rij);
					GridPane.setRowIndex(sleepBareImage, rij);
					GridPane.setColumnIndex(sleepBareImage, kolom);

					event.setDropCompleted(true);
				}

				event.consume();
			});

			pane.setGridLinesVisible(true);
			gridPanePerPersoon.put(speler, pane);
		}
	}

	private void updateBorden() {
		for (AnchorPane anPane : borden) {
			anPane.getChildren().clear();
		}

		ArrayList<spelerDTO> overigeSpelers = new ArrayList<>();
		overigeSpelers.addAll(deelnemers);
		overigeSpelers.remove(koning);

		for (int i = 0; i < overigeSpelers.size(); i++) {
			lbVelden[i + 1].setText(String.format("%s", overigeSpelers.get(i)));
			lbVelden[i + 1].setTextFill(overigeSpelers.get(i).kleur().getColor());
			herSchaal(overigeSpelers.get(i), 40);
			borden[i + 1].getChildren().add(gridPanePerPersoon.get(overigeSpelers.get(i)));
		}

		lbVelden[0].setText(String.format("%s", koning.gebruikersnaam()));
		lbVelden[0].setTextFill(koning.kleur().getColor());
		herSchaal(koning, 84);
		borden[0].getChildren().add(gridPanePerPersoon.get(koning));
	}

	private void herSchaal(spelerDTO spelerDTO, int grootte) {
		GridPane pane = gridPanePerPersoon.get(spelerDTO);

		pane.getRowConstraints().clear();
		pane.getColumnConstraints().clear();

		for (int i = 0; i < 5; i++) {
			pane.getRowConstraints().add(new RowConstraints(grootte));
			pane.getColumnConstraints().add(new ColumnConstraints(grootte));
		}

		pane.getChildren().forEach(child -> {
			if (child instanceof ImageView) {
				ImageView imgV = (ImageView) child;
				if (imgV.getFitWidth() == 168 || imgV.getFitWidth() == 80) {
					imgV.setFitHeight(grootte);
					imgV.setFitWidth(grootte * 2);
				} else if (imgV.getFitHeight() == 168 || imgV.getFitWidth() == 80) {
					imgV.setFitHeight(grootte * 2);
					imgV.setFitWidth(grootte);
				} else {
					imgV.setFitHeight(grootte);
					imgV.setFitWidth(grootte);
				}
			}
		});

		gridPanePerPersoon.put(spelerDTO, pane);
	}

	private void setDominosOpScherm() {
		if (spelTenEinde()) {
			eindeGame();
		}
		updateScores();

		lblPlayingUsername.setText(String.format("Round %d", getRonde()));
		lbAlgemeneTekst.setText("Turn dominos");

		eindKolom.clear();
		eindKolom.addAll(startKolom);
		startKolom.clear();
		mogelijkeImages.clear();
		alleTijdelijkeImages.clear();
		gekozenTeLeggenDominos.clear();
		gekozenTeLeggenDominos.putAll(gekozenDominoSpeler);
		gekozenDominoSpeler.clear();

		try {
			dc.wisselKolom();
			startKolom.addAll(dc.geefStartKolom());

		} catch (Exception e) {
			System.err.print(e);
			// switch
		}

		for (ImageView imageView : startPionImages) {
			grdDominos.getChildren().remove(imageView);
			grdDominos.add(imageView, 1, GridPane.getRowIndex(imageView));
		}

		grdDominos.getChildren().removeAll(eindPionImages);
		eindPionImages.clear();
		eindPionImages.addAll(startPionImages);
		startPionImages.clear();

		for (int i = 0; i < eindKolom.size(); i++) {
			Image img = new Image(
					String.format("file:assets/dominotegel/tegel_%02d_voorkant.png", eindKolom.get(i).volgnummer()));
			imgEindkolom[i].setImage(img);
		}

		Collections.sort(startKolom, new dominoTegelComparator());

		for (int i = 0; i < startKolom.size(); i++) {
			Image img = new Image(
					String.format("file:assets/dominotegel/tegel_%02d_achterkant.png", startKolom.get(i).volgnummer()));
			imgDominos[i].setImage(img);
		}

		btnNext.setText("Turn dominos");
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				draaiDominos();
			}
		});

	}

	private void eindeGame() {
		spelerDTO winnaar = null;

		try {
			updateScores();
			winnaar = dc.geefWinnaar();
			dc.updateDataBase();
		} catch (Exception e) {
			terugNaarStart();
		}

		lbAlgemeneTekst.setText(String.format("%s has won", winnaar));

		btnNext.setText("Exit game");
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				terugNaarStart();
			}
		});

	}

	private boolean spelTenEinde() {
		try {
			return dc.isSpelTenEinde();
		} catch (Exception e) {
			terugNaarStart();
		}

		return false;
	}

	private void draaiDominos() {
		lbAlgemeneTekst.setText(String.format("%s Chose a domino", koning.gebruikersnaam()));

		for (int i = 0; i < startKolom.size(); i++) {
			Image img = new Image(
					String.format("file:assets/dominotegel/tegel_%02d_voorkant.png", startKolom.get(i).volgnummer()));
			imgDominos[i].setImage(img);
			mogelijkeImages.add(img);
			alleTijdelijkeImages.add(img);
		}

		btnNext.setText("Chose domino");
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				kiesDomino();
			}
		});
	}

	private void kiesDomino() {
		if (selecteerdeImage != null && mogelijkeImages.contains(selecteerdeImage)) {
			ImageView imgV = new ImageView(
					new Image(String.format("file:assets/speler/pawn_%s.png", koning.kleur().toStringForPath())));
			imgV.setFitHeight(64);
			imgV.setFitWidth(64);

			int index = alleTijdelijkeImages.indexOf(selecteerdeImage);
			grdDominos.add(imgV, 0, index);
			startPionImages.add(imgV);

			mogelijkeImages.remove(selecteerdeImage);
			gekozenDominoSpeler.put(koning, startKolom.get(index));

			volgendeSpeler();
			lbAlgemeneTekst.setText(String.format("%s Chose a domino", koning.gebruikersnaam()));

			if (eindKolom.isEmpty() && mogelijkeImages.isEmpty()) {
				lbAlgemeneTekst.setText(String.format("Getting new dominos"));
				btnNext.setText("New Dominos");
				btnNext.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						setDominosOpScherm();
					}
				});
			} else if (mogelijkeImages.isEmpty()) {
				lbAlgemeneTekst.setText(String.format("Now placing dominos"));

				btnNext.setText("Place Domino");
				btnNext.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						renderBord();
					}
				});
			} else {
				btnNext.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						kiesDomino();
					}
				});
			}
		}
	}

	private void renderBord() {
		if (!gekozenTeLeggenDominos.isEmpty()) {
			btnDraai.setDisable(false);
			btnDraai.setVisible(true);
			btnSpiegel.setDisable(false);
			btnSpiegel.setVisible(true);

			lbAlgemeneTekst.setText(String.format("%s Place a domino", koning.gebruikersnaam()));
			updateBorden();

			plaatsDomino();

			btnNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					boolean correct = false;
					try {
						System.out.printf("%s op %d en %d %n", gekozenTeLeggenDominos.get(koning).volgnummer(), GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
						
						dc.plaatsDomino(gekozenTeLeggenDominos.get(koning), koning,
								GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
						correct = true;
					} catch (Exception e) {
						System.out.println(e);
					}

					if (correct) {
						gekozenTeLeggenDominos.remove(koning);
						volgendeSpeler();
						lbAlgemeneTekst.setText(String.format("%s Place a domino", koning.gebruikersnaam()));
						
						horizontaal = true;
						spiegeld = false;
						
						renderBord();
					}
				}
			});
		} else {
			btnDraai.setDisable(true);
			btnDraai.setVisible(false);
			btnSpiegel.setDisable(true);
			btnSpiegel.setVisible(false);

			btnNext.setText("Next round");
			btnNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					setDominosOpScherm();
				}
			});
		}
	}

	private void plaatsDomino() {
		updateBorden();
		GridPane grid = gridPanePerPersoon.get(koning);
		borden[0].getChildren().clear();
		borden[0].getChildren().add(grid);
		sleepBareImage = new ImageView(new Image(String.format("file:assets/dominotegel/tegel_%02d_voorkant.png",
				gekozenTeLeggenDominos.get(koning).volgnummer())));
		sleepBareImage.setFitWidth(168);
		sleepBareImage.setFitHeight(84);
		
		GridPane.setColumnSpan(sleepBareImage, 2);
		GridPane.setRowSpan(sleepBareImage, 1);
		grid.add(sleepBareImage, 0, 0);
		
		gridPanePerPersoon.put(koning, grid);

		
		System.out.printf("%f row:%d column%d row:%d column%d%n", sleepBareImage.getRotate(), GridPane.getRowSpan(sleepBareImage), GridPane.getColumnSpan(sleepBareImage), GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
		System.out.printf("Met image van %f op %f %n", sleepBareImage.getFitWidth(), sleepBareImage.getFitHeight());
		
		// TODO HIER

		sleepBareImage.setOnDragDetected(event -> {
			
			System.out.printf("%f row:%d column%d row:%d column%d%n", sleepBareImage.getRotate(), GridPane.getRowSpan(sleepBareImage), GridPane.getColumnSpan(sleepBareImage), GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
			System.out.printf("Met image van %f op %f %n", sleepBareImage.getFitWidth(), sleepBareImage.getFitHeight());
			
			Dragboard db = sleepBareImage.startDragAndDrop(TransferMode.MOVE);

			ClipboardContent content = new ClipboardContent();
			content.putImage(sleepBareImage.getImage());
			db.setContent(content);

			event.consume();
		});	
	}

	private void updateScores() {
		ArrayList<spelerDTO> deelnemers = new ArrayList<>();

		try {
			dc.berekenScores();
			deelnemers = dc.geefDeelnemendeSpelersInSpel();
		} catch (Exception e) {
			System.err.println(e);
			//terugNaarStart();
		}

		for (int i = 0; i < deelnemers.size(); i++) {
			lbSpelers[i].setText(deelnemers.get(i).gebruikersnaam());
			lbSpelers[i].setTextFill(deelnemers.get(i).kleur().getColor());
			lbScores[i].setText(String.format("Score %d", deelnemers.get(i).score()));
		}

	}

	private void volgendeSpeler() {
		try {
			dc.kiesNieuweKoning();
			updateKoning();

		} catch (Exception e) {
			terugNaarStart();
		}
	}

	private void updateKoning() {
		try {
			koning = dc.geefKoning();

		} catch (Exception e) {
			terugNaarStart();
		}
	}

	private int getRonde() {
		try {
			return (dc.getRonde() + 1) / 2;

		} catch (Exception e) {
			System.err.println(e);
			terugNaarStart();
		}

		return 0;
	}

	private void terugNaarStart() {
		MenuStartController scene = new MenuStartController(new DomeinController(), stage);
		getScene().setRoot(scene);
	}

	@FXML
	void selecteerImage(javafx.scene.input.MouseEvent event) {
		ImageView imageView = (ImageView) event.getSource();
		selecteerdeImage = imageView.getImage();

		updateKoning();
		System.out.printf("%s selected %s%n", koning.gebruikersnaam(), selecteerdeImage.toString()); // RAndom
	}

	@FXML
	void speigelDomino(ActionEvent event) {
		if (sleepBareImage != null) {
			sleepBareImage.setRotate(sleepBareImage.getRotate() + 180);
			spiegeld = !spiegeld;

			try {
				dc.spiegelDomino(gekozenTeLeggenDominos.get(koning));
			} catch (Exception e) {

			}
		}
	}

	@FXML
	void draaiDomino(ActionEvent event) {
		if (sleepBareImage != null) {
			if (horizontaal) {
				System.out.printf("%f row:%d column%d row:%d column%d%n", sleepBareImage.getRotate(), GridPane.getRowSpan(sleepBareImage), GridPane.getColumnSpan(sleepBareImage), GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
				System.out.printf("Met image van %f op %f %n", sleepBareImage.getFitWidth(), sleepBareImage.getFitHeight());
				
				sleepBareImage.setRotate(90);
				
				GridPane.setRowSpan(sleepBareImage, GridPane.getRowSpan(sleepBareImage) == 1 ? 2 : 1);
				GridPane.setColumnSpan(sleepBareImage, GridPane.getColumnSpan(sleepBareImage) == 1 ? 2 : 1);			
				
			} else {
				sleepBareImage.setRotate(sleepBareImage.getRotate() - 90);
				
				GridPane.setRowSpan(sleepBareImage, GridPane.getRowSpan(sleepBareImage) == 1 ? 2 : 1);
				GridPane.setColumnSpan(sleepBareImage, GridPane.getColumnSpan(sleepBareImage) == 1 ? 2 : 1);
			}
			
			System.out.printf("%f row:%d column%d row:%d column%d%n", sleepBareImage.getRotate(), GridPane.getRowSpan(sleepBareImage), GridPane.getColumnSpan(sleepBareImage), GridPane.getRowIndex(sleepBareImage), GridPane.getColumnIndex(sleepBareImage));
			System.out.printf("Met image van %f op %f %n", sleepBareImage.getFitWidth(), sleepBareImage.getFitHeight());
			

			horizontaal = !horizontaal;
			
			//updateBorden();

			try {
				dc.draaiDomino(gekozenTeLeggenDominos.get(koning));
			} catch (Exception e) {
				terugNaarStart();
			}
		}
	}
}