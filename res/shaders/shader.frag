uniform sampler2D texture;
varying vec2 texCoord;

void main() {
	vec4 texcolor = texture2D(texture, texCoord);
	gl_FragColor = texcolor;
}