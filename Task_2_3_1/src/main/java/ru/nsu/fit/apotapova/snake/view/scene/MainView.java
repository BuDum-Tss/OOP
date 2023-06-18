package ru.nsu.fit.apotapova.snake.view.scene;

import java.util.List;

/**
 * Main view.
 */
public abstract class MainView {

  private List<SceneView> sceneList;

  /**
   * Selects scene.
   *
   * @param scene chosen scene.
   */
  public void selectScene(Class<? extends SceneView> scene) {
    sceneList.forEach(sceneView -> {
      boolean value = sceneView.getClass().getSuperclass() == scene;
      if (value != sceneView.isVisible()) {
        if (sceneView.isVisible()) {
          sceneView.closeScene();
        } else {
          sceneView.openScene();
        }
        sceneView.setVisible(value);
      }
    });
  }

  protected void setSceneList(List<SceneView> sceneList) {
    this.sceneList = sceneList;
  }
}