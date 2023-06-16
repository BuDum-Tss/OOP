module ru.nsu.fit.apotapova.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.logging;
  requires java.desktop;
  requires org.apache.logging.log4j;

  opens ru.nsu.fit.apotapova.snake.controller.scenecontrollers to javafx.fxml;
    opens ru.nsu.fit.apotapova.snake.controller to javafx.fxml;
    opens ru.nsu.fit.apotapova.snake to javafx.fxml;
    exports ru.nsu.fit.apotapova.snake;
    exports ru.nsu.fit.apotapova.snake.view.scene;
    exports ru.nsu.fit.apotapova.snake.view.scene.sceneview;
    exports ru.nsu.fit.apotapova.snake.model.data;
    opens ru.nsu.fit.apotapova.snake.model.data to javafx.fxml;
}