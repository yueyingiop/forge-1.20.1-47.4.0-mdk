#version 150 core

in vec2 texCoord;
in vec3 worldNormal;
in vec3 fragPos;

uniform sampler2D Sampler0; // 纹理
uniform vec3 GlowColor = vec3(1.0, 0.8, 0.2); // 默认金色发光
uniform float Time;  // 时间
uniform float Intensity = 1.0;  // 发光强度
uniform float GlowRadius = 0.5; // 发光半径
uniform float PulseSpeed = 3.0; // 脉动速度

out vec4 fragColor;

void main() {
    // 采样原始纹理
    vec4 texColor = texture(Sampler0, texCoord);
    
    // 丢弃透明像素
    if (texColor.a < 0.1) discard;

    // 计算边缘检测
    vec2 texelSize = 1.0 / textureSize(Sampler0, 0);
    float edgeFactor = 0.0;
    
    // 检查四个方向像素的透明度
    float leftAlpha = texture(Sampler0, texCoord + vec2(-texelSize.x, 0)).a;
    float rightAlpha = texture(Sampler0, texCoord + vec2(texelSize.x, 0)).a;
    float topAlpha = texture(Sampler0, texCoord + vec2(0, -texelSize.y)).a;
    float bottomAlpha = texture(Sampler0, texCoord + vec2(0, texelSize.y)).a;

     // 计算边缘因子
    if (texColor.a > 0.5 && (leftAlpha < 0.1 || rightAlpha < 0.1 || topAlpha < 0.1 || bottomAlpha < 0.1)) {
        edgeFactor = 1.0;
    }

    // 添加脉动效果
    float pulse = sin(Time * PulseSpeed) * 0.5 + 0.5;
    float glowIntensity = edgeFactor * pulse * Intensity;

    // 计算距离中心的发光强度
    vec2 center = vec2(0.5, 0.5);
    float distToCenter = distance(texCoord, center);
    float centerGlow = 1.0 - smoothstep(0.0, GlowRadius, distToCenter);

    // 组合发光效果
    float finalGlow = max(glowIntensity, centerGlow * 0.7);
    
    // 发光颜色
    vec3 glow = GlowColor * finalGlow;
    
    // 最终颜色 = 原始颜色 + 发光效果
    vec3 finalColor = texColor.rgb + glow;
    
    // 输出最终颜色
    fragColor = vec4(finalColor, texColor.a);
}