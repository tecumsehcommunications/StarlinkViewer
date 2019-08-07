<html>
<head></head>
<body>
<h1>Welcome to the Business End of the Project</h1>
<h2>File structures and Workflow</h1>
<p>We use a three-directory structure for all parts of this project (Client, Server, and Desktop).  You will see that reflected in the directory that contains this README file.  These directories are defined as follows:  

<h3>The /dev Directory</h3>
<p>The /dev directory holds things we developers use that don't go into the final publication of the app.  Among other things, it has a "brepl" script to run a browser repl, and a "build" script to compile the contents of the /src directory into the pub/js directory.  Run these scripts from the root directory, e.g. ./dev/build.</p>  

<p>What I usually do, when I am writing code for the Client, is I first start a browser repl, as described <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/repls#the-client-browser-repl">here</a>.  Then I open either the scratch-paper file found in /dev/scratch.cljs or a source file found in /src into an Emacs buffer and go to work.  Once I am satisfied with how the code works in the repl, I run /dev/build which compiles everything in /src into /pub/js for the browser to load with the page.</p>

<h3>The /pub Directory </h3>
<p>This directory is shared between all three branches of the project (client, server, desktop) as follows:</p>
<img src="dev/fileStructure.svg"></img>
<p>Work done in other areas of the project ultimately gets published to this directory where it is picked up by the server and sent to a browser.  </p>
<p>This client side of the project is the final staging grounds for work done elsewhere.  It also is for developing all the client side code to run in the browser.  </p>

<p>See the <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/server">server</a> and <a href="https://github.com/tecumsehcommunications/StarlinkViewer/tree/master/desktop">desktop</a> branches of this project to see how they each publish to this directory.</p>

<h4>Using LiveReload</h4>

<p>I use Emacs to edit all the HTML, CSS, and SVG files in the /pub directory.  When I am doing this, I also run a LiveReload process on the /pub directory from the Server.  The instructions for doing that are <a href="">here</a>. Then, after installing a LiveReload plugin for your browser like the one found <a href="https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei?hl=en">here</a>, all you need to do is point your web browser to the http://localhost URL on your machine and activate the plugin. Now, whenever you edit any file in the/pub directory in Emacs, when you "C-x C-s" in its Emacs buffer, that file saves and the web page automatically reloads it. This really speeds development of the HTML scaffolding of the app. </p> 

<h3>The /src Directory </h3>
<p>This is where we will tie the camera we create in our Blender scene to a physical camera running on a smartphone.  This is where we will determine where the smartphone is located on the planet and how it is pointed at the sky.  This is where we will map all that information into the 3D scene we first made in Blender then loaded from our Server into a webGl canvas.  Lastly, this is where we will query satellite orbital data from our Server and animate accurate satellite orbits, using the coordinates of the celestial globe already in the scene.</p>  
 <p>A person using this app accurately will be able to see where Starlink satellites are passing overhead and if they can be reached from his location.  A real Starlink Viewer.  Saddle up, nerds.</p>

<h2>A Word about Three.js</h2>

<p>You probably want to clone the entirety of the three.js project from github, at https://github.com/mrdoob/three.js.git, 
to keep it handy on your machine. Even though we'll just be using a fraction of the utility in that project to start, we'll likely 
use a lot more as things progress.</p>  

<p>For now, for the client side of the app, all that is needed is the three.min.js file found in the build directory of the three.js 
project.  It is small enough that I include it in this project's repository where the client app is setup to automatically load it with 
the webpage. So there really is no initial setup to do for Three.js as long as you have a cloned copy of this project into a local folder.</p>

<p>We need Three.js on the tool chain for two principal reasons, right now.  1)  It can load the .gltf scene file we export out of Blender from its place on the Server into a webGL canvas of the Client/Broswer side of our application, and 2) It can render that scene on that canvas with all its lights, textures, animations, .etc —just as they were built in Blender. </p> 

<p>Thanks to Mr. Doob and the Three.js development community.</p>  

<h2>Time to Jump</h2>

<p>Developing the Client Provides Some Iteresting Challenges.  Learn about them <a href="https://github.com/tecumsehcommunications/StarlinkViewer/blob/master/client/dev/README.md">here</a>.</p>
  
</body>
</html>
