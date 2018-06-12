package com.pasha.ui;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.external.stats.ExtMatchStat;
import com.pasha.entity.external.stats.ExtStats;
import com.pasha.service.Downloader;
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
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired private MatchCombinedService matchCombinedService;

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
        List<MatchCombined> contacts = matchCombinedService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Столбцы таблицы
        TableColumn<MatchCombined, String> colDate = new TableColumn<>("Date/Time");
        colDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));

        TableColumn<MatchCombined, String> colId = new TableColumn<>("extId");
        colId.setCellValueFactory(new PropertyValueFactory<>("extId"));

        TableColumn<MatchCombined, String> colPK = new TableColumn<>("pK");
        colPK.setCellValueFactory(new PropertyValueFactory<>("pk"));

        TableColumn<MatchCombined, String> colPD = new TableColumn<>("pD");
        colPD.setCellValueFactory(new PropertyValueFactory<>("pd"));

        TableColumn<MatchCombined, String> colPKD = new TableColumn<>("pKD");
        colPKD.setCellValueFactory(new PropertyValueFactory<>("pkd"));

        TableColumn<MatchCombined, String> colDK = new TableColumn<>("dK");
        colDK.setCellValueFactory(new PropertyValueFactory<>("dk"));

        TableColumn<MatchCombined, String> colDD = new TableColumn<>("dD");
        colDD.setCellValueFactory(new PropertyValueFactory<>("dd"));

        TableColumn<MatchCombined, String> colDKD = new TableColumn<>("dKD");
        colDKD.setCellValueFactory(new PropertyValueFactory<>("dkd"));


        table.getColumns().setAll(colDate, colPK, colPD, colPKD, colId, colDK, colDD, colDKD);

        // Данные таблицы
        table.setItems(data);
    }

    @FXML
    public void download() {
        EventHandler<ActionEvent> action = downloadButton.getOnAction();
        downloadButton.setText("Стоп");
        new Thread(() -> {
            downloadButton.setOnAction((ActionEvent) -> { stopped = true; });
            Platform.runLater(() -> progressBar.setProgress(0));
            data.clear();
            matchCombinedService.deleteAll();

            ExtStats extStats = new Downloader<>(ExtStats.class).downloadUrl();
            int i = 0;
            for (ExtMatchStat stat : extStats.getData()) {
                MatchCombined matchCombined =
                        new MatchCombined(stat.getMatch().getId(),
                                LocalDateTime.of(stat.getDate().toLocalDateTime().getYear(),
                                        stat.getDate().toLocalDateTime().getMonthOfYear(),
                                        stat.getDate().toLocalDateTime().getDayOfMonth(),
                                        stat.getDate().toLocalDateTime().getHourOfDay(),
                                        stat.getDate().toLocalDateTime().getMinuteOfHour()),
                                stat.getKills(), stat.getDies(), stat.getKd(),
                                0, 0, 0.0);
                matchCombinedService.save(matchCombined);
                data.add(matchCombined);
                double progress = i++ / extStats.getData().size();
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
