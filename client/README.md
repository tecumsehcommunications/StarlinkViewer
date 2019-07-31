<h1>Welcome to the Business End of the Client Side of the Starlink Viewer Project</h1>
<h2>File structures and Workflow</h1>
<p>We use a three-directory structure for all sides of this project (Client, Server, and Desktop).  You will see that reflected in the directory that contains this README file.  The /dev directory holds things we developers use that don't go into the final publication of the app.  The /pub directory is for the what gets picked up by the Server and published to the web. And, lastly, the src directory is for just what you would expect: its subdirectory structures determined by the naming conventions of the language (Python or Clojure) we are working with.

Inside the /dev directory is a build script.  What I do when I am building out the Client is I start a browser repl, as described <a href="">here</a> and then I find either the scratch-paper file found in /dev/scratch.cljs or a source file found in /src.  I load that into an Emacs, and I go to work.  Once I am satisfied with how the code works in the repl, I run /dev/build which compiles everything in /src into /pub/js for the browser to load with the page.

The /pub directory also has /img and /svg sub directories.  I like to keep these two file formats separate as they are handled very differently by the web browser.  Files in the /img directory tend to find their way through Gimp, which is a great tool for working with bitmap files.  Files in the /svg directory tend to come through Inkscape, which is a great tool for working with SVG (note: save your inkscape files using the "plain" svg option to create web-effecient versions).
  
Thanks to the excellent folks working in the Gimp and Inkscape development communities for their great work.  

The /pub/scenes directory is where we put the .gltf scenes we build in blender from within the Desktop side of this project.   For more detail on how we use Blender in this project look <a href="">here</a>

The Client side of the project is the end of the line for everything produced elsewhere.  This is where we will tie the camera we create
in our blender scene to a physical camera running on a smartphone.  This is where we will determine where the smartphone is located on the planet and how it is pointed at the sky.  This is where we will map all that information into the 3D scene we first made in Blender then loaded from our Server into a webGl canvas, via Three.js.  Lastly, this is where we will query satellite orbital data from our Server and animate accurate satellite orbits, using the coordinates of the celestial globe already in our scene, so the user of the app can see where the Starlink satellites are passing overhead and if they can be reached from his location.  A Starlink Viewer.

<h2>A world about Three.js</h2>

<p>You definitely want to clone the entirety of the three.js project from github, at https://github.com/mrdoob/three.js.git, 
to keep it handy on your machine. Even though we'll just be using a fraction of the utility in that project to start, we'll likely 
use a lot more as things progress.</p>  

<p>For now, for the client side of the app, all that is needed is the three.min.js file found in the build directory of the three.js 
project.  It is small enough that I include it in this project's repository where the client app is setup to automatically load it with 
the webpage. So there really is no initial setup to do for Three.js as long as you have a cloned copy of this project into a local folder.</p>

<p>We need Three.js on the tool chain for two principal reasons, right now.  1)  It can load the .gltf scene file we export out of Blender from its place on the Server into a webGL canvas of the Client/Broswer side of our application, and 2) It can render that scene with all its lights, textures, animations, .etc—just as they were built in Blender. </p> 

Now you are ready to directly see how all that we've been talking about is meant to work together.  And maybe you can help improve and build on what has so far been done.  So you are invited to apply the tools and code the source code however you like.  Better yet, join Github, if you haven't already, and contribute to this project.  We would.

To better understand how all these tools we've talked about are meant to work together, you can start with the /client/src/app/core.cljs 
source file found <a href="">here</a> and just follow the code.</p>

<p>Thanks to Mr. Doob and everyone in the Three.js development community.</p>
