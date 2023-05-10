module ru.nsu.fit.apotapova.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens ru.nsu.fit.apotapova.snake.controller.scenecontrollers to javafx.fxml;
    opens ru.nsu.fit.apotapova.snake.controller to javafx.fxml;
    opens ru.nsu.fit.apotapova.snake to javafx.fxml;
    exports ru.nsu.fit.apotapova.snake;
    exports ru.nsu.fit.apotapova.snake.data;
    opens ru.nsu.fit.apotapova.snake.data to javafx.fxml;
}