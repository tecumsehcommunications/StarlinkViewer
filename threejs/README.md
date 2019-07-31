<h1>Homage to Three.js</h1>
<p>You definitely want to clone the entirety of the three.js project from github, at https://github.com/mrdoob/three.js.git, 
to keep it handy on your machine. Even though we'll just be using a fraction of the utility in that project to start, we'll likely 
use a lot more as things progress.</p>  

<p>For now, for the client side of the app, all that is needed is the three.min.js file found in the build directory of the three.js 
project.  It is small enough that I include it in this project's repository where the client app is setup to automatically load it with 
the webpage. So there really is no initial setup to do for Three.js as long as you have a cloned copy of this project into a local folder.</p>

<p>We need Three.js on the tool chain for two principal reasons, right now.  1)  It can load the .gltf scene file we export out of Blender
from its place on the Server into a webGL canvas of the Client/Broswer side of our application, and 2) It can render that scene with all 
its lights, textures, animations, .etc—just as they were built in Blender. </p> 

To better understand how all these tools we've talked about are meant to work together, you can start with the /client/src/app/core.cljs 
source file found <a href="">here</a> and just follow the code.</p>

<p>Thanks to Mr. Doob and everyone in the Three.js development community.</p>
