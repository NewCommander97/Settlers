uniform sampler2D texture_diffuse;
in vec2 texCoord;
in vec4 color;

void main() {
	vec4 texcolor = texture2D(texture_diffuse, texCoord);
	gl_FragColor = texcolor;
}