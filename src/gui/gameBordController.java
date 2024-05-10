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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class gameBordController extends SplitPane {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private DomeinController dc;

	// ======================================================

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

		this.dc = dc;
		initialize();

		try {
			dc.startSpel();
			aantalSpelers = dc.geefAantalSpelers();

		} catch (Exception e) {
			System.err.print(e);

			/*
			 * try { switchtoStart();
			 * 
			 * } catch (IOException e1) { System.err.println(e1); }
			 */
		}

		if (aantalSpelers < 4) {
			lbScore4.setDisable(true);
			lbScore4.setVisible(false);
			lbSpeler4.setDisable(true);
			lbSpeler4.setVisible(false);
		}

		startSpel();

	}

	// ======================================================

	private Double coorX, coorY;
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
	private dominoTegelDTO sleepBareDomino;
	private ArrayList<Image> mogelijkeImages;
	private ArrayList<Image> alleTijdelijkeImages;
	private ArrayList<ImageView> startPionImages;
	private ArrayList<ImageView> eindPionImages;

	private ArrayList<dominoTegelDTO> eindKolom;
	private ArrayList<dominoTegelDTO> startKolom;

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
	private Button btnRoteer;

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

		//btnDraai.setText(vertaal.geefWoord("TURN_RIGHT"));

		ImageView imageView = new ImageView(new Image("file:assats/arrow_clockwise.png"));
		imageView.setFitWidth(24);
		imageView.setFitHeight(24);
		btnDraai.setGraphic(imageView);

		btnRoteer.setText(vertaal.geefWoord("MIRROR"));
		lblProgressie.setText(vertaal.geefWoord("ALMOST_YOUR_TURN"));
		lblPlayingUsername.setText(vertaal.geefWoord("IS_PLAYING"));
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

// 				TODO
//				for (int j = 0; j < 5; j++) {
//					pane.add(new javafx.scene.layout.StackPane(), i, j);
//				} 
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
				if (sleepBareImage != null) {
					int rij = (int) (event.getX() / sleepBareImage.getBoundsInParent().getWidth());
					int kolom = (int) (event.getY() / sleepBareImage.getBoundsInParent().getHeight());

					pane.getChildren().remove(sleepBareImage);
					pane.add(sleepBareImage, rij, kolom);
					
					if(sleepBareDomino.horizontaal()) {
						GridPane.setColumnSpan(sleepBareImage, 2);
					} else {
						GridPane.setRowSpan(sleepBareImage, 2);
					}

					event.setDropCompleted(true);
				}

				event.consume();
			});

//			TODO
//			pane.getStyleClass().add("grid-pane");
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
				imgV.setFitHeight(grootte);
				imgV.setFitWidth(grootte);
			}
		});

		gridPanePerPersoon.put(spelerDTO, pane);
	}

	private void setDominosOpScherm() {
		lblPlayingUsername.setText(String.format("Round %d", getRonde()));
		lbAlgemeneTekst.setText("Turn dominos");

		eindKolom.clear();
		eindKolom.addAll(startKolom);
		startKolom.clear();
		mogelijkeImages.clear();
		alleTijdelijkeImages.clear();
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
			btnRoteer.setDisable(false);
			btnRoteer.setVisible(true);
			
			lbAlgemeneTekst.setText(String.format("%s Place a domino", koning.gebruikersnaam()));
			updateBorden();

			plaatsDomino();


			btnNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					volgendeSpeler();
					lbAlgemeneTekst.setText(String.format("%s Place a domino", koning.gebruikersnaam()));

					renderBord();
				}
			});
		} else {
			btnDraai.setDisable(true);
			btnDraai.setVisible(false);
			btnRoteer.setDisable(true);
			btnRoteer.setVisible(false);
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
		GridPane grid = haalGridPaneOp();
		ImageView imgV = new ImageView(new Image(String.format("file:assets/dominotegel/tegel_%02d_voorkant.png",
				gekozenTeLeggenDominos.get(koning).volgnummer())));
		imgV.setFitHeight(84);
		imgV.setFitWidth(84);
		GridPane.setColumnSpan(imgV, 2);
		GridPane.setRowSpan(imgV, 1);
		grid.add(imgV, 0, 0);
		
		// TODO HIER

		sleepBareDomino = gekozenTeLeggenDominos.get(koning);
		gekozenTeLeggenDominos.remove(koning);

		imgV.setOnDragDetected(event -> {
			sleepBareImage = (ImageView) event.getSource();
			Dragboard db = imgV.startDragAndDrop(TransferMode.ANY);

			Image snapshot = sleepBareImage.snapshot(null, null);
			sleepBareImage.setImage(snapshot);

			ClipboardContent content = new ClipboardContent();
			content.putImage(imgV.getImage());
			db.setContent(content);

			event.consume();
		});

//		TODO
//		gridPanePerPersoon.put(koning, grid);
	}

	private GridPane haalGridPaneOp() {
		for (Node node : borden[0].getChildren()) {
			if (node instanceof GridPane) {
				return (GridPane) node;
			}
		}
		return null;
	}

	private void updateScores() {
		deelnemers.clear();

		try {
			deelnemers = dc.geefDeelnemendeSpelersInSpel();
		} catch (Exception e) {
			System.err.println(e);
			// Hier
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
			// hier
		}
	}

	private void updateKoning() {
		try {
			koning = dc.geefKoning();

		} catch (Exception e) {
			// hier
		}
	}

	private int getRonde() {
		try {
			return (dc.getRonde() + 1) / 2;

		} catch (Exception e) {
			System.err.println(e);
			// Hier
		}

		return 0;
	}

	@FXML
	public void switchToSceneStart(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();

		MenuStartController controller = loader.getController();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
		
	}
	
	@FXML
	void draaiDomino(ActionEvent event) {
		
	}

	private void switchtoStart() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();

		MenuStartController controller = loader.getController();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}