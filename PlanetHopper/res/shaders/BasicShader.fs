#version 330

in vec2 texCoord0;

uniform vec3 color;
uniform sampler2D sampler;

void main()
{
	vec4 baseColor = vec4(color, 1.0);
	
	vec4 texColor = texture2D(sampler, texCoord0.xy);
	if(texColor != vec4(0,0,0,0))
		baseColor *= texColor;
	
	gl_FragColor = baseColor;
}