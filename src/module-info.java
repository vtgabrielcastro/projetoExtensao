module projetoExtensao {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml;
	exports Control to javafx.fxml;
	opens Control to javafx.fxml;
	exports gui to javafx.fxml;
	opens gui to javafx.fxml;
	opens Model to javafx.base;
}
