package gui;

import Model.Cliente;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TableCellFactory {
	@SuppressWarnings("exports")
	public static Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>> createButtonCell() {
		return new Callback<>() {
			@Override
			public TableCell<Cliente, String> call(final TableColumn<Cliente, String> param) {
				return new TableCell<>() {

					private final Button editButton = new Button("Editar");
					private final Button deleteButton = new Button("Excluir");

					{
						// Adicionar funcionalidade aos botões se necessário
						editButton.setOnAction(event -> {
							Cliente cliente = getTableView().getItems().get(getIndex());
							System.out.println("Editar: " + cliente.getNome());
						});

						deleteButton.setOnAction(event -> {
							Cliente cliente = getTableView().getItems().get(getIndex());
							System.out.println("Excluir: " + cliente.getNome());
						});
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(editButton);
							setGraphic(deleteButton);
						}
					}
				};
			}
		};
	}
}
