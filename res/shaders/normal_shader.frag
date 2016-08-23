uniform sampler2D texture_diffuse;
uniform sampler2D texture_normal;
varying vec2 texCoord;
varying vec4 color;

void main() {
	vec4 texcolor = texture2D(texture_diffuse, texCoord);
	//vec4 normalColor = texture2D(texture_normal, texCoord);
	vec3 TextureNormal = normalize(texture2D(texture_normal, texCoord).rgb * 2.0 - 1.0);
	vec3 n = TextureNormal;
	//float cosTheta = clamp( dot( n,l ), 0,1 );
	gl_FragColor = texcolor + vec4(TextureNormal, 1);
}