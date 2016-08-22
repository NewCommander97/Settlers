varying vec2 texCoord;
varying vec4 color;

void main() {
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
	texCoord = gl_MultiTexCoord0.xy;
	color = gl_Color;
}