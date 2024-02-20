/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.EstoqueDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Modelo;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAnchorPaneProcessoEstoqueController implements Initializable {

    @FXML
    private Button btRepor;

    @FXML
    private Button btRetirar;

    @FXML
    private Button btAtualizar;

    @FXML
    private Label lbModeloDescricao;

    @FXML
    private Label lbModeloId;

    @FXML
    private Label lbModeloNome;

    @FXML
    private Label lbModeloPreco;

    @FXML
    private Label lbModeloQtdMaxima;

    @FXML
    private Label lbModeloQtdMinima;

    @FXML
    private Label lbModeloQuantidade;

    @FXML
    private Label lbModeloSituacao;

    @FXML
    private TableColumn<Modelo, String> tableColumnDescricao;

    @FXML
    private TableColumn<Modelo, Modelo> tableColumnQuantidade;

    @FXML
    private TableView<Modelo> tableView;


    private List<Modelo> listaModelos;
    private ObservableList<Modelo> observableListModelos;

    //acesso ao banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoqueDAO.setConnection(connection);

        carregarTableView();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));

    }

    public void carregarTableView() {
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        
        listaModelos = estoqueDAO.listar();
        
        observableListModelos = FXCollections.observableArrayList(listaModelos);
        tableView.setItems(observableListModelos);
    }
    
    public void selecionarItemTableView(Modelo Modelo) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (Modelo != null) {
            lbModeloId.setText(Integer.toString(Modelo.getId()));
            lbModeloNome.setText(Modelo.getNome());
            lbModeloDescricao.setText(Modelo.getDescricao());
            lbModeloPreco.setText(df.format(Modelo.getPreco().doubleValue()));
            lbModeloQuantidade.setText(Integer.toString(Modelo.getEstoque().getQuantidade()));
            lbModeloQtdMinima.setText(Integer.toString(Modelo.getEstoque().getQtdMinima()));
            lbModeloQtdMaxima.setText(Integer.toString(Modelo.getEstoque().getQtdMaxima()));
            lbModeloSituacao.setText(Modelo.getEstoque().getSituacao().getDescricao());
        } else {
            lbModeloId.setText("");
            lbModeloNome.setText("");
            lbModeloDescricao.setText("");
            lbModeloPreco.setText("");
            lbModeloQuantidade.setText("");
            lbModeloQtdMinima.setText("");
            lbModeloQtdMaxima.setText("");
            lbModeloSituacao.setText("");
        }
    }
    

    @FXML
    public void handleBtRepor() throws IOException {
        Modelo Modelo = tableView.getSelectionModel().getSelectedItem();
        if (Modelo != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(Modelo, "Repor");
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(Modelo.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Modelo na tabela ao lado.");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtRetirar() throws IOException {
        Modelo Modelo = tableView.getSelectionModel().getSelectedItem();
        if (Modelo != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(Modelo, "Retirar");
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(Modelo.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Modelo na tabela ao lado.");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtAtualizar() throws IOException {
        Modelo Modelo = tableView.getSelectionModel().getSelectedItem();
        if (Modelo != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueAtualizacaoDialog(Modelo);
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(Modelo.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Modelo na Tabela.");
            alert.show();
        }    
    }
    
    public boolean showFXMLAnchorPaneProcessoEstoqueAtualizacaoDialog(Modelo Modelo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroModeloDialogController.class.getResource( 
            "../view/FXMLAnchorPaneProcessoEstoqueAtualizacaoDialog.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Atualização do Estoque");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o Modelo ao controller
        FXMLAnchorPaneProcessoEstoqueAtualizacaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEstoque(Modelo.getEstoque());
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }

    public boolean showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(Modelo Modelo, String tipoMovimento) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneProcessoEstoqueMovimentoDialogController.class.getResource( 
            "../view/FXMLAnchorPaneProcessoEstoqueMovimentoDialog.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        if (tipoMovimento.equalsIgnoreCase("Repor")) {
            dialogStage.setTitle("Movimentação de Reposição: " + Modelo.getNome());
        } else {
            dialogStage.setTitle("Movimentação de Retirada: " + Modelo.getNome());
        }
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o Modelo ao controller
        FXMLAnchorPaneProcessoEstoqueMovimentoDialogController controller = loader.getController();
        controller.setTipoMovimento(tipoMovimento);
        controller.setDialogStage(dialogStage);
        controller.setEstoque(Modelo.getEstoque());
        
        
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
        
    }

}
