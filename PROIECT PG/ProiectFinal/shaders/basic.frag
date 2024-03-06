#version 410 core

in vec3 fNormal;
in vec4 fPosEye;
in vec2 fragTexCoords;
in vec4 fragPosLightSpace;
out vec4 fColor;

//lighting
uniform	vec3 lightDir;
uniform	vec3 lightColor;

//texture
uniform sampler2D diffuseTexture;
uniform sampler2D specularTexture;
uniform sampler2D shadowMap;
float shadow=0.0f;

vec3 ambient;
float ambientStrength = 0.2f;
vec3 diffuse;
vec3 specular;
float specularStrength = 0.5f;
float shininess = 28.0f;

float constant = 1.0f;
float linear = 0.0014f;
float quadratic = 0.000007f;

void computeLightComponents()
{		
	vec3 cameraPosEye = vec3(0.0f);//in eye coordinates, the viewer is situated at the origin
	
	//transform normal
	vec3 normalEye = normalize(fNormal);	
	
	//compute light direction
	vec3 lightDirN = normalize(lightDir);
	
	//compute view direction 
	vec3 viewDirN = normalize(cameraPosEye - fPosEye.xyz);
		
	//compute ambient light
	ambient = ambientStrength * lightColor;
	
	//compute diffuse light
	diffuse = max(dot(normalEye, lightDirN), 0.0f) * lightColor;
	
	//compute specular light
	vec3 reflection = reflect(-lightDirN, normalEye);
	float specCoeff = pow(max(dot(viewDirN, reflection), 0.0f), shininess);
	specular = specularStrength * specCoeff * lightColor;
}

float computeFog()
{
 float fogDensity = 0.008f;
 float fragmentDistance = length(fPosEye);
 float fogFactor = exp(-pow(fragmentDistance * fogDensity, 2));

 return clamp(fogFactor, 0.0f, 1.0f);
}

float computeShadow()
{

// perform perspective divide
vec3 normalizedCoords = fragPosLightSpace.xyz / fragPosLightSpace.w;

// Transform to [0,1] range
normalizedCoords = normalizedCoords * 0.5 + 0.5;
if(normalizedCoords.z > 1.0f)
  return 0.0f;

// Get closest depth value from light's perspective
float closestDepth = texture(shadowMap, normalizedCoords.xy).r;

// Get depth of current fragment from light's perspective
float currentDepth = normalizedCoords.z;

// Check whether current frag pos is in shadow
float bias = 0.005f;
float shadow = currentDepth - bias > closestDepth ? 1.0 : 0.0;

    return shadow;
}
void main() 
{
	computeLightComponents();
	float fogFactor = computeFog();
	vec3 fogColor = vec3(0.5f, 0.5f, 0.5f);
	vec3 baseColor = vec3(0.9f, 0.35f, 0.0f);//orange
	
	ambient *= texture(diffuseTexture, fragTexCoords).rgb;
	diffuse *= texture(diffuseTexture, fragTexCoords).rgb;
	specular *= texture(specularTexture, fragTexCoords).rgb;

	shadow = computeShadow();
	vec3 color = min((ambient + (1.0f - shadow) * diffuse) + (1.0f - shadow) * specular, 1.0f);
	fColor=vec4(mix(fogColor,color,fogFactor),1.0f);
   

    vec4 colorFromTexture = texture(diffuseTexture, fragTexCoords);
    if(colorFromTexture.a < 0.1)
        discard;
    
    }  
