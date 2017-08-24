package org.enoy.klc.app.components;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.enoy.klc.app.components.utils.ExternalMonitorObject;
import org.enoy.klc.app.components.utils.ExternalMonitorUtil;
import org.enoy.klc.control.external.ExternalServer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExternalMonitor implements Initializable {

	@FXML
	private Label labelConnectedClients;

	@FXML
	private ToggleButton toggleButtonRefresh;

	@FXML
	private TableView<ExternalMonitorObject> table;

	@FXML
	private TableColumn<ExternalMonitorObject, String> tableColumnScope;

	@FXML
	private TableColumn<ExternalMonitorObject, String> tableColumnIdentifier;

	@FXML
	private TableColumn<ExternalMonitorObject, String> tableColumnParameter;

	@FXML
	private TableColumn<ExternalMonitorObject, Integer> tableColumnType;

	@FXML
	private TableColumn<ExternalMonitorObject, String> tableColumnValue;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tableColumnScope.setCellValueFactory(new PropertyValueFactory<>("scope"));
		tableColumnIdentifier.setCellValueFactory(new PropertyValueFactory<>("identifier"));
		tableColumnParameter.setCellValueFactory(new PropertyValueFactory<>("parameter"));
		tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("data"));

		PauseTransition refreshLoop = new PauseTransition();
		refreshLoop.setOnFinished(e -> {
			if (toggleButtonRefresh.isSelected())
				refresh();
			refreshLoop.playFromStart();
		});
		refreshLoop.setDuration(Duration.seconds(0.5));
		refreshLoop.playFromStart();

		refresh();
	}

	public void refresh() {
		ExternalServer server = ExternalServer.getCurrentInstance();

		if (server != null) {
			int activeClients = server.getActiveClientsCount();
			labelConnectedClients.setText(Integer.toString(activeClients));

			table.getItems().clear();
			table.getItems().setAll(ExternalMonitorUtil.getObjects());
		}

	}

	public static Stage getStage(Window owner) {

		Stage stage = new Stage();

		ResourceBundle resources = ResourceBundle.getBundle("fxml/i18n/klc");
		URL location = ExternalMonitor.class.getResource("/fxml/ExternalMonitor.fxml");
		FXMLLoader loader = new FXMLLoader(location, resources);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		stage.setTitle("Server Monitor");
		stage.initModality(Modality.NONE);
		stage.initStyle(StageStyle.UTILITY);
		stage.initOwner(owner);
		stage.setScene(new Scene(loader.getRoot()));

		return stage;
	}
}
