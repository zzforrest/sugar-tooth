#version 330

in vec2 texCoord0;
in vec3 normal0;

uniform vec3 baseColor;
uniform vec3 ambientLight;
uniform sampler2D sampler;

struct BaseLight
{
	vec3 color;
	float intensity;
};

struct DirectionalLight
{
	BaseLight bLight;
	vec3 direction;
};

uniform DirectionalLight dLight;

vec4 calcLight(BaseLight bLight, vec3 direction, vec3 normal)
{
	float diffuseFactor = dot(normal, -direction);
	
	vec4 diffuseColor = vec4(0, 0, 0, 0);
	
	if(diffuseFactor > 0)
	{
		diffuseColor = vec4(bLight.color, 1.0) * diffuseFactor * bLight.intensity;
	}
	
	return diffuseColor;
}

vec4 calcDirectionalLight(DirectionalLight dirLight, vec3 normal)
{
	return calcLight(dirLight.bLight, -dirLight.direction, normal);
}

void main()
{
	vec4 totalLight = vec4(ambientLight, 1);
	vec4 color = vec4(baseColor, 1.0);
	
	vec4 texColor = texture2D(sampler, texCoord0.xy);
	if(texColor != vec4(0,0,0,0))
		color *= texColor;
		
	vec3 normal = normalize(normal0);
	
	totalLight += calcDirectionalLight(dLight, normal);
	
	gl_FragColor = color * totalLight;
}