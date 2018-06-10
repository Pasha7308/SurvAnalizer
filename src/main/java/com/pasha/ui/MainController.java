package com.pasha.ui;

import com.pasha.entity.MatchCombined;
import com.pasha.service.MatchCombinedService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired private MatchCombinedService contactService;

    // Инъекции JavaFX
    @FXML private TableView<MatchCombined> table;
    @FXML private Button downloadButton;
    @FXML private ProgressBar progressBar;

    // Variables
    private ObservableList<MatchCombined> data;
    private boolean stopped = false;

    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<MatchCombined> contacts = contactService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Столбцы таблицы
        TableColumn<MatchCombined, String> colPK = new TableColumn<>("pK");
        colPK.setCellValueFactory(new PropertyValueFactory<>("pK"));

        TableColumn<MatchCombined, String> colPD = new TableColumn<>("pD");
        colPK.setCellValueFactory(new PropertyValueFactory<>("pD"));

        TableColumn<MatchCombined, String> colPKD = new TableColumn<>("pKD");
        colPK.setCellValueFactory(new PropertyValueFactory<>("pKD"));

        TableColumn<MatchCombined, String> colId = new TableColumn<>("ID");
        colPK.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<MatchCombined, String> colDK = new TableColumn<>("dK");
        colPK.setCellValueFactory(new PropertyValueFactory<>("dK"));

        TableColumn<MatchCombined, String> colDD = new TableColumn<>("dD");
        colPK.setCellValueFactory(new PropertyValueFactory<>("dD"));

        TableColumn<MatchCombined, String> colDKD = new TableColumn<>("dKD");
        colPK.setCellValueFactory(new PropertyValueFactory<>("dKD"));


        table.getColumns().setAll(colPK, colPD, colPKD, colId, colDK, colDD, colDKD);

        // Данные таблицы
        table.setItems(data);
    }

    @FXML
    public void download() {
        EventHandler<ActionEvent> action = downloadButton.getOnAction();
        downloadButton.setText("Стоп");
        new Thread(() -> {
            downloadButton.setOnAction((ActionEvent) -> { stopped = true; });
            contactService.deleteAll();

            int max = 20000;
            for (int i = 0; i < max; i++) {
                MatchCombined contact =
                    new MatchCombined(
                        (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0,
                        (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0);
                contactService.save(contact);
                data.add(contact);
                double progress = (double) i / (double) max;
                Platform.runLater(() -> progressBar.setProgress(progress));
                if (stopped) {
                    break;
                }
            }
            downloadButton.setOnAction(action);
            Platform.runLater(() -> downloadButton.setText("Скачать"));
            stopped = false;
        }).start();
    }
}
