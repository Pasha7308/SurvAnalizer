package com.pasha.ui;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchPerson;
import com.pasha.entity.Player;
import com.pasha.entity.external.stats.ExtMatchStat;
import com.pasha.entity.external.stats.ExtStats;
import com.pasha.service.DateService;
import com.pasha.service.Downloader;
import com.pasha.service.MatchCombinedService;
import com.pasha.service.MatchPersonService;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
    @Autowired private MatchCombinedService matchCombinedService;
    @Autowired private MatchPersonService matchPersonService;
    @Autowired private DateService dateService;

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
        colId.setStyle( "-fx-alignment: CENTER;");

        TableColumn<MatchCombined, Integer> colPK = new TableColumn<>("pK");
        colPK.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getKills() : null));

        TableColumn<MatchCombined, Integer> colPD = new TableColumn<>("pD");
        colPD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getDeaths() : null));

        TableColumn<MatchCombined, Double> colPKD = new TableColumn<>("pKD");
        colPKD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getKd() : null));

        TableColumn<MatchCombined, Integer> colDK = new TableColumn<>("dK");
        colDK.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getKills() : null));

        TableColumn<MatchCombined, Integer> colDD = new TableColumn<>("dD");
        colDD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getDeaths() : null));

        TableColumn<MatchCombined, Double> colDKD = new TableColumn<>("dKD");
        colDKD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getKd() : null));

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

            downloadPlayer(Player.Pasha);
            downloadPlayer(Player.Daniil);

            List<MatchCombined> matches = matchCombinedService.findAll();
            data = FXCollections.observableArrayList(matches);

            downloadButton.setOnAction(action);
            Platform.runLater(() -> downloadButton.setText("Скачать"));
            stopped = false;
        }).start();
    }

    private void downloadPlayer(Player player) {
        if (stopped) {
            return;
        }
        ExtStats extStats = new Downloader<>(ExtStats.class).downloadUrl(player);
        int i = 0;
        for (ExtMatchStat stat : extStats.getData()) {
            MatchCombined matchCombined = matchCombinedService.findByExtId(stat.getMatch().getId());
            if (matchCombined == null) {
                matchCombined = new MatchCombined(stat.getMatch().getId(), dateService.jodaDateToLocalDate(stat.getDate()));
            }
            matchCombined.setPlayer(player, matchPersonService.save(new MatchPerson(stat.getKills(), stat.getDies(), stat.getKd())));
            matchCombinedService.save(matchCombined);
            data.add(matchCombined);

            double progress = i++ / extStats.getData().size();
            Platform.runLater(() -> progressBar.setProgress(progress));
            if (stopped) {
                break;
            }
        }
    }
}
