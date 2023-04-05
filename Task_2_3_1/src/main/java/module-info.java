module ru.nsu.fit.apotapova.snake {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ru.nsu.fit.apotapova.snake to javafx.fxml;
    exports ru.nsu.fit.apotapova.snake;
}