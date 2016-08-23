in vec2 texCoordV;
out vec2 texCoord;
out vec4 color;

void main() {
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
	texCoord = texCoordV;
	color = gl_Color;
}