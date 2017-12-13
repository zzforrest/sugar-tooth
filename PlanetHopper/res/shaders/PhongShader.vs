#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoord;
layout (location = 2) in vec3 normal;

out vec2 texCoord0;
out vec3 normal0;

uniform mat4 transWorld;
uniform mat4 transProjected;

void main()
{
	texCoord0 = texCoord;
	normal0   = (transWorld * vec4(normal, 0.0)).xyz;
	
	gl_Position = transProjected * vec4(position, 1.0);
}