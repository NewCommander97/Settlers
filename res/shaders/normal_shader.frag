uniform sampler2D texture_diffuse;
uniform sampler2D texture_normal;
varying vec2 texCoord;
varying vec4 color;

void main() {
	vec4 texcolor = texture2D(texture_diffuse, texCoord);
	vec4 normalColor = texture2D(texture_normal, texCoord);
	gl_FragColor = normalColor;
}