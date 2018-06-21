package com.pasha.ui;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.Player;
import com.pasha.entity.analytics.ShowClass;
import com.pasha.entity.analytics.ShowClassDesc;
import com.pasha.entity.analytics.ShowFieldDesc;
import com.pasha.entity.external.stats.ExtMatchStat;
import com.pasha.entity.external.stats.ExtStats;
import com.pasha.service.Analyzer;
import com.pasha.service.Downloader;
import com.pasha.service.MatchCombinedService;
import com.pasha.service.MatchPersonService;
import com.pasha.translator.MatchPersonTranslator;
import com.pasha.translator.MatchTranslator;
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
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired private MatchCombinedService matchCombinedService;
    @Autowired private MatchPersonService matchPersonService;
    @Autowired private MatchTranslator matchTranslator;
    @Autowired private MatchPersonTranslator matchPersonTranslator;
    @Autowired private Analyzer analyzer;

    // Инъекции JavaFX
    @FXML private TableView<MatchCombined> table;
    @FXML private TableView<ShowClass> tableAnalytics;
    @FXML private Button downloadButton;
    @FXML private Button analyzeButton;
    @FXML private ProgressBar progressBar;

    // Variables
    private ObservableList<MatchCombined> data;
    private ObservableList<ShowClass> dataAnalytics;
    private boolean stopped = false;

    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
    }

    @PostConstruct
    public void init() {
        initTable();

        initAnalytics();

        // Данные таблицы
        download();
    }

    @SuppressWarnings("unchecked")
    private void initTable() {
        List<MatchCombined> contacts = matchCombinedService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Столбцы таблицы
        TableColumn<MatchCombined, String> colDate = new TableColumn<>("Date/Time");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setMinWidth(30);

        TableColumn<MatchCombined, String> colLevel = new TableColumn<>("Уровень");
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colLevel.setStyle("-fx-alignment: CENTER;");

        TableColumn<MatchCombined, String> colId = new TableColumn<>("extId");
        colId.setCellValueFactory(new PropertyValueFactory<>("extId"));
        colId.setStyle("-fx-alignment: CENTER;");

        TableColumn<MatchCombined, Integer> colPLev = new TableColumn<>("pLevel");
        colPLev.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getLevel() : null));
        colPLev.setStyle("-fx-alignment: CENTER;");

        TableColumn<MatchCombined, Integer> colDLev = new TableColumn<>("pLevel");
        colDLev.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getLevel() : null));
        colDLev.setStyle("-fx-alignment: CENTER;");

        TableColumn<MatchCombined, Integer> colPK = new TableColumn<>("pK");
        colPK.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getKills() : null));
        colPK.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn<MatchCombined, Integer> colPD = new TableColumn<>("pD");
        colPD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getDeaths() : null));
        colPD.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn<MatchCombined, Double> colPKD = new TableColumn<>("pKD");
        colPKD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getPasha() != null) ? p.getValue().getPasha().getKd() : null));
        colPKD.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn<MatchCombined, Integer> colDK = new TableColumn<>("dK");
        colDK.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getKills() : null));

        TableColumn<MatchCombined, Integer> colDD = new TableColumn<>("dD");
        colDD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getDeaths() : null));

        TableColumn<MatchCombined, Double> colDKD = new TableColumn<>("dKD");
        colDKD.setCellValueFactory(p -> new ReadOnlyObjectWrapper((p.getValue().getDaniil() != null) ? p.getValue().getDaniil().getKd() : null));

        table.getColumns().setAll(colDate, colPLev, colLevel, colDLev, colPK, colPD, colPKD, colId, colDK, colDD, colDKD);
        table.setItems(data);
    }

    @SuppressWarnings("unchecked")
    private void initAnalytics() {
        List<ShowClass> show = new ArrayList<>();
        dataAnalytics = FXCollections.observableArrayList(show);
        tableAnalytics.setItems(dataAnalytics);

        ShowClassDesc description = new ShowClassDesc();
        Analyzer.initTable(description);

        for (ShowFieldDesc field : description.getFields()) {
            TableColumn<ShowClass, String> col = new TableColumn<>(field.getTitle());
            col.setCellValueFactory(new PropertyValueFactory<>(field.getField()));
            col.setMinWidth(field.getWidth());
            col.setStyle("-fx-alignment: CENTER;");
            tableAnalytics.getColumns().add(col);
        }
        tableAnalytics.setItems(dataAnalytics);
    }

    @FXML
    public void analyze() {
        List<ShowClass> show = new ArrayList<>();
        analyzer.analyze(show);

        dataAnalytics.clear();
        dataAnalytics = FXCollections.observableArrayList(show);
        tableAnalytics.setItems(dataAnalytics);
    }

    @FXML
    public void download() {
        EventHandler<ActionEvent> action = downloadButton.getOnAction();
        downloadButton.setText("Стоп");
        new Thread(() -> {
            analyzeButton.setDisable(true);
            downloadButton.setOnAction((ActionEvent) -> { stopped = true; });
            Platform.runLater(() -> progressBar.setProgress(0));
            data.clear();
            matchCombinedService.deleteAll();

            int count = countPlayer(Player.Pasha);
            Platform.runLater(() -> progressBar.setProgress(0.5));
            count += countPlayer(Player.Daniil);
            Platform.runLater(() -> progressBar.setProgress(1));

            int skip = downloadPlayer(Player.Pasha, 0, count);
            downloadPlayer(Player.Daniil, skip, count);

            List<MatchCombined> matches = matchCombinedService.findAllByOrderByExtIdDesc();
            data = FXCollections.observableArrayList(matches);
            table.setItems(data);

            downloadButton.setOnAction(action);
            analyzeButton.setDisable(false);
            Platform.runLater(() -> downloadButton.setText("Скачать"));
            stopped = false;
        }).start();
    }

    private int countPlayer(Player player) {
        ExtStats extStats = new Downloader<>(ExtStats.class).downloadUrl(player, 1, 0);
        return extStats != null ? extStats.getTotal() : 0;
    }
    private int downloadPlayer(Player player, int skip, int total) {
        if (stopped) {
            return 0;
        }
        Platform.runLater(() -> progressBar.setProgress((double)skip / (double)total));
        int i = 0, localTotal = 100;
        for (; i < localTotal; i += 50) {
            ExtStats extStats = new Downloader<>(ExtStats.class).downloadUrl(player, 50, i);
            localTotal = extStats.getTotal();
            for (ExtMatchStat stat : extStats.getData()) {
                MatchCombined matchCombined = matchCombinedService.findByExtId(stat.getMatch().getId());
                if (matchCombined == null) {
                    matchCombined = matchTranslator.extToLocal(stat);
                }
                matchCombined.setPlayer(player, matchPersonService.save(matchPersonTranslator.extToLocal(stat)));
                matchCombinedService.save(matchCombined);
            }
            double progress = (double)(skip + i + 50) / (double)total;
            Platform.runLater(() -> progressBar.setProgress(progress));
            if (stopped) {
                break;
            }
        }
        return i;
    }
}
