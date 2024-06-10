package com.shepherdjerred.capstone.engine.graphics.shader.code;

import java.io.IOException;

public interface ShaderCodeLoader {

  String getShaderCode(String shaderName) throws IOException;
}
