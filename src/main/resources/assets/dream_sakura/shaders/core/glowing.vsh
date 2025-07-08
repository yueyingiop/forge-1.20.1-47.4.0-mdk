#version 150

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in vec3 Normal;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 texCoord;
out vec3 worldNormal;
out vec3 fragPos;

void main() {
    vec4 viewPos = ModelViewMat * vec4(Position, 1.0);
    gl_Position = ProjMat * viewPos;
    
    texCoord = UV0;
    worldNormal = mat3(ModelViewMat) * Normal;
    fragPos = viewPos.xyz;
}