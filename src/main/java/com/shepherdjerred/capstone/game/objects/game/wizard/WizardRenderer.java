package com.shepherdjerred.capstone.game.objects.game.wizard;

import com.shepherdjerred.capstone.common.player.Element;
import com.shepherdjerred.capstone.engine.graphics.RendererCoordinate;
import com.shepherdjerred.capstone.engine.graphics.matrices.ModelMatrix;
import com.shepherdjerred.capstone.engine.graphics.mesh.AnimatedTexturedMesh;
import com.shepherdjerred.capstone.engine.graphics.mesh.Mesh;
import com.shepherdjerred.capstone.engine.graphics.mesh.TexturedMesh;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgram;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgramName;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderUniform;
import com.shepherdjerred.capstone.engine.graphics.texture.Texture;
import com.shepherdjerred.capstone.engine.graphics.texture.spritesheet.Spritesheet;
import com.shepherdjerred.capstone.engine.graphics.texture.spritesheet.SpritesheetCoordinate;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.game.objects.game.wizard.Wizard.State;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WizardRenderer implements GameObjectRenderer<Wizard> {

  private final ResourceManager resourceManager;
  private final Map<State, AnimatedTexturedMesh> meshes;
  private ShaderProgram shaderProgram;

  public WizardRenderer(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
    this.meshes = new HashMap<>();
  }

  @Override
  public void initialize(Wizard wizard) throws Exception {
    var dimensions = wizard.getSceneObjectDimensions();
    shaderProgram = resourceManager.get(ShaderProgramName.DEFAULT);

    var element = wizard.getElement();

    createMeshForElementState(element, State.STILL, dimensions, false);
    createMeshForElementState(element, State.CASTING, dimensions, false);
    createMeshForElementState(element, State.WALKING_LEFT, dimensions, true);
    createMeshForElementState(element, State.WALKING_RIGHT, dimensions, false);
    createMeshForElementState(element, State.WALKING_UP, dimensions, false);
    createMeshForElementState(element, State.WALKING_DOWN, dimensions, false);
  }

  private void createMeshForElementState(Element element,
      State state,
      SceneObjectDimensions dimensions,
      boolean flip) throws Exception {
    var width = dimensions.getWidth();
    var height = dimensions.getHeight();

    var mapper = new WizardTextureMapper();
    var stateTextureName = mapper.getTexture(element, state);
    Texture stateTexture = resourceManager.get(stateTextureName);

    var walkingUpTextureSheet = new Spritesheet(stateTexture.getWidth(),
        stateTexture.getHeight(),
        32);

    var animatedMesh = new AnimatedTexturedMesh();

    for (int i = 0; i < walkingUpTextureSheet.getNumberOfVerticalTextures(); i++) {
      var vertices = new float[] {
          0, 0, 0,
          0, height, 0,
          width, 0, 0,
          width, height, 0
      };

      var textureCoordinates = walkingUpTextureSheet.getCoordinatesForSprite(new SpritesheetCoordinate(
          0,
          i));

      if (flip) {
        textureCoordinates = textureCoordinates.flipX();
      }

      var textureCoordinatesArray = textureCoordinates.asIndexedFloatArray();

      var indices = new int[] {
          0, 1, 2,
          3, 1, 2
      };

      var mesh = new Mesh(vertices, textureCoordinatesArray, indices);
      var texturedMesh = new TexturedMesh(mesh, stateTexture);
      animatedMesh.setFrame(i, texturedMesh);
    }

    meshes.put(state, animatedMesh);
  }

  @Override
  public void render(WindowSize windowSize, Wizard wizard) {
    var pos = wizard.getPosition()
        .getSceneCoordinate(windowSize, wizard.getSceneObjectDimensions());
    var model = new ModelMatrix(new RendererCoordinate(pos.getX(), pos.getY(), pos.getZ()),
        0,
        1).getMatrix();

    shaderProgram.bind();
    shaderProgram.setUniform(ShaderUniform.MODEL_MATRIX, model);

    var mesh = meshes.get(wizard.getState());
    mesh.render(wizard.getFrame());
  }

  @Override
  public void cleanup() {
    meshes.values().forEach(animatedMesh -> animatedMesh.getMeshes().forEach(mesh -> {
      resourceManager.free(mesh.getTexture().getTextureName());
      mesh.getMesh().cleanup();
    }));
    resourceManager.free(shaderProgram.getShaderProgramName());
  }
}
